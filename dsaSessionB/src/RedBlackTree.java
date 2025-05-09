public class RedBlackTree {
    private static final int RED = 0;
    private static final int BLACK = 1;

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

    public RedBlackTree() {
        TNULL = new Node(0);
        TNULL.color = BLACK;
        root = TNULL;
    }    

    //postorder transversal
    public void postOrder() {
        postOrderHelper(root);
    }
    private void postOrderHelper(Node node) {
        if (node != TNULL) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Rotate left
    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Rotate right
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // Fix the red-black tree after insertion of a node
    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == RED) {
            if (k.parent == k.parent.parent.left) {
                u = k.parent.parent.right; // uncle
                if (u.color == RED) {
                    // case 3.1
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        // case 3.2.2
                        k = k.parent;
                        leftRotate(k);
                    }
                    // case 3.2.1
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    rightRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.left; // uncle

                if (u.color == RED) {
                    // mirror case 3.1
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        // mirror case 3.2.2
                        k = k.parent;
                        rightRotate(k);
                    }
                    // mirror case 3.2.1
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
        Node node = new Node(key);
        node.parent = null;
        node.data = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = RED;

        Node y = null;
        Node x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node; // Tree was empty
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = BLACK;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }
}
