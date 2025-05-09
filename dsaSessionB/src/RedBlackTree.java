public class RedBlackTree {
    private static final int RED = 0; // Red color for nodes
    private static final int BLACK = 1; // Black color for nodes

    // Node class representing each node in the red-black tree
    // Each node has data, color, left child, right child, and parent
    private class Node {
        int data;
        int color;
        Node left, right, parent;

        Node(int data) {
            this.data = data;
            this.color = RED; // New nodes are always red
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    private Node root;
    private final Node TNULL;

    // Constructor for the RedBlackTree class
    // Initializes the tree with a sentinel node (TNULL) for leaves
    public RedBlackTree() {
        TNULL = new Node(0);
        TNULL.color = BLACK;
        root = TNULL;
    }    

    // Postorder transversal
    public void postOrder() {
        // Postorder traversal of the red-black tree
        // This method prints the values of the nodes in postorder
        postOrderHelper(root);
    }
    private void postOrderHelper(Node node) {
        // Base case: if the node is TNULL, return
        // This means we have reached a leaf node
        if (node != TNULL) {
            postOrderHelper(node.left); // Traverse the left subtree
            postOrderHelper(node.right); // Traverse the right subtree
            // Visit the current node and print its value
            System.out.print(node.data + " ");
        }
    }

    // Rotate left
    private void rightRotate(Node x) {
        // Right rotation of the tree around node x
        // This operation is used to maintain the red-black tree properties
        Node y = x.left; // Set y to the left child of x

        x.left = y.right; // Set the left child of x to the right child of y
        if (y.right != TNULL) { // If y has a right child, set its parent to x
            y.right.parent = x; // Set the parent of y's right child to x
        }
        y.parent = x.parent; // Set the parent of y to the parent of x
        if (x.parent == null) { // If x is the root, set y as the new root
            this.root = y; // Set the root of the tree to y
        } else if (x == x.parent.right) { // If x is the right child of its parent
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Rotate right
    private void leftRotate(Node x) {
        Node y = x.right; // Set y to the right child of x
        x.right = y.left; // Set the right child of x to the left child of y
        if (y.left != TNULL) { // If y has a left child, set its parent to x
            y.left.parent = x; // Set the parent of y's left child to x
        }
        y.parent = x.parent; // Set the parent of y to the parent of x
        if (x.parent == null) { // If x is the root, set y as the new root
            this.root = y; // Set the root of the tree to y
        } else if (x == x.parent.left) { // If x is the left child of its parent
            x.parent.left = y; // Set the left child of the parent to y
        } else { // If x is the right child of its parent
            x.parent.right = y; // Set the right child of the parent to y
        }
        y.left = x; // Set the left child of y to x
        x.parent = y; // Set the parent of x to y
    }

    // Fix the red-black tree after insertion of a node
    private void fixInsert(Node k) { 
        Node u; // Uncle node
        // Fix the tree by checking the color of the parent and uncle nodes
        while (k.parent.color == RED) { // While the parent of k is red
            // Check if the parent of k is a left child or a right child
            if (k.parent == k.parent.parent.left) {
                u = k.parent.parent.right; // uncle
                if (u.color == RED) { // uncle is red
                    // case where both parent and uncle are red
                    u.color = BLACK; // Set the color of the uncle to black
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        // case where k is a right child
                        k = k.parent;
                        leftRotate(k);
                    }
                    // case where k is a left child
                    // Set the color of the parent to black and the grandparent to red
                    k.parent.color = BLACK; 
                    k.parent.parent.color = RED; 
                    rightRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.left; // uncle

                if (u.color == RED) {
                    // Case where both parent and uncle are red
                    // Set the color of the uncle to black
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        // Case where k is a left child
                        // Set the color of the parent to black and the grandparent to red
                        k = k.parent;
                        rightRotate(k);
                    }
                    
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    leftRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = BLACK;
    }

    // Insert a node into the red-black tree
    public void insert(int key) {
        Node node = new Node(key); // Create a new node with the given key
        node.parent = null; // Set the parent of the new node to null
        node.data = key; // Set the data of the new node to the given key
        node.left = TNULL; // Set the left child of the new node to TNULL
        node.right = TNULL; // Set the right child of the new node to TNULL
        node.color = RED; // Set the color of the new node to red
        // Traverse the tree to find the correct position for the new node

        Node y = null; // Parent of the new node
        Node x = this.root; // Start from the root of the tree
        // Traverse the tree to find the correct position for the new node

        while (x != TNULL) { 
            y = x; // Set the parent of the new node to the current node
            if (node.data < x.data) { 
                x = x.left; // Move to the left child
            } else {
                x = x.right; // Move to the right child
            }
        }

        node.parent = y; 
        if (y == null) {
            root = node; // Tree was empty
        } else if (node.data < y.data) { 
            y.left = node; // Set the left child of the parent to the new node
        } else {
            y.right = node; // Set the right child of the parent to the new node
        }

        // If the new node is the root, set its color to black
        if (node.parent == null) {
            node.color = BLACK; 
            return;
        }

        // If the parent of the new node is null, set the color of the new node to black
        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node); // Fix the tree after insertion
    }
}
