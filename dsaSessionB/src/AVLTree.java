public class AVLTree {
    private class AVLNode {
        int key;
        AVLNode left, right;
        int height;

        AVLNode(int d) {
            key = d;
            height = 1;
        }
    }

    private AVLNode root;

    public void insert(int key){
        root = insertHelper(root, key);
    }

    private AVLNode insertHelper(AVLNode node, int key) {
        if (node == null) {
            return new AVLNode(key);
        }

        if (key < node.key) {
            node.left = insertHelper(node.left, key);
        } else if (key > node.key) {
            node.right = insertHelper(node.right, key);
        } else {
            return node; // Duplicate keys are not allowed
        }

        // Update the height of this ancestor node
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // Get the balance factor
        int balance = getBalance(node);

        // If the node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int getHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        // Return the new root
        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        // Return the new root
        return y;
    }

    // Preorder traversal
    public void preOrder() {
        preOrderHelper(root);
    }
    private void preOrderHelper(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    // Inorder traversal
    public void inOrder() {
        inOrderHelper(root);
    }
    private void inOrderHelper(AVLNode node) {
        if (node != null) {
            inOrderHelper(node.left);
            System.out.print(node.key + " ");
            inOrderHelper(node.right);
        }
    }

    // Postorder traversal
    public void postOrder() {
        postOrderHelper(root);
    }
    private void postOrderHelper(AVLNode node) {
        if (node != null) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.key + " ");
        }
    }

    // AVL Tree Display
    public void display() {
        System.out.println("AVL Tree (Inorder Traversal):");
        inOrder();
        System.out.println();
        System.out.println("AVL Tree (Preorder Traversal):");
        preOrder();
        System.out.println();
        System.out.println("AVL Tree (Postorder Traversal):");
        postOrder();
        System.out.println();
    }
    
}
