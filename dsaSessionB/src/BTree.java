import java.util.ArrayList;
import java.util.List;

class BTreeNode {
    // B-tree node properties
    // Each node contains a list of keys and a list of children
    List<Integer> keys;
    // List of keys in the node
    // Each key is an integer
    List<BTreeNode> children;
    // List of children nodes
    // Each child is a BTreeNode
    boolean leaf;
    int t; // Minimum degree

    // Constructor to create a new B-tree node
    // t is the minimum degree (defines the range for number of keys)
    BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    // Find the index of the first key greater than or equal to k
    // This method is used to find the position of the key in the node
    int findKey(int k) {
        int idx = 0;
        while (idx < keys.size() && keys.get(idx) < k)
            ++idx;
        return idx;
    }

    // Insert a key into this node
    // This method is used when the node is not full
    void insertNonFull(int k) {
        int i = keys.size() - 1;

        if (leaf) {
            keys.add(0); // Make space for the new key
            while (i >= 0 && keys.get(i) > k) {
                keys.set(i + 1, keys.get(i));
                i--;
            }
            keys.set(i + 1, k);
        } else {
            int temp = findKey(k);

            if (children.get(temp).keys.size() == 2 * t - 1) {
                splitChild(temp, children.get(temp));
                if (keys.get(temp) < k)
                    temp++;
            }
            // Insert the key into the child
            // This is the recursive call to insert the key into the child
            children.get(temp).insertNonFull(k);
        }
    }

    // Split the child of this node
    // This method is used when a child node is full
    void splitChild(int i, BTreeNode y) {
        // Create a new node to hold the keys of y
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        // Move the last t-1 keys from y to z
        for (int j = 0; j < y.keys.size() - y.t; j++) {
            z.keys.add(y.keys.get(y.t + j));
        }
        // Clear the keys in y after moving
        for (int j = y.t - 1; j < y.keys.size(); j++) {
            // Remove the key from y
            y.keys.remove(y.t - 1);
        }

        if (!y.leaf) {
            // Move the last t children from y to z
            for (int j = 0; j < y.children.size() - y.t; j++) {
                // Add the child to z
                z.children.add(y.children.get(y.t + j));
            }
            // Clear the children in y after moving
            for (int j = y.t; j < y.children.size(); j++) {
                // Remove the child from y
                y.children.remove(y.t);
            }
        }

        // Insert z as a child of this node
        children.add(i + 1, z);
        // Move the middle key of y to this node
        // This key will be the new parent key
        keys.add(i, y.keys.get(y.t - 1));
        // Remove the middle key from y
        // This key is now the parent key
        y.keys.remove(y.t - 1);
    }

    // Traverse the B-tree and print keys
    // This method is used to display the keys in the B-tree
    void traverseHelper() {
        int i;
        for (i = 0; i < keys.size(); i++) {
            if (!leaf) {
                children.get(i).traverseHelper();
            }
            System.out.print(keys.get(i) + " ");
        }
        if (!leaf) {
            children.get(i).traverseHelper();
        }
    }

    // Search for a key in the B-tree
    // Returns the node containing the key if found, otherwise returns null
    BTreeNode search(int k) {
        int i = findKey(k);

        if (i < keys.size() && keys.get(i) == k)
            return this;

        if (leaf)
            return null;

        return children.get(i).search(k);
    }
        
    }

public class BTree {
    BTreeNode root;
    int t; // Minimum degree

    BTree(int t) {
        this.root = null;
        this.t = t;
    }

    // Insert a key into the B-tree
    // If the root is null, create a new root node
    void insert(int k) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys.add(k);
        } else {
            if (root.keys.size() == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children.add(root);
                s.splitChild(0, root);

                int i = 0;
                if (s.keys.get(0) < k)
                    i++;
                s.children.get(i).insertNonFull(k);

                root = s;
            } else {
                root.insertNonFull(k);
            }
        }
    }

    @SuppressWarnings("unused")
    // This method is not used in the current implementation
    void traverse() {
        if (root != null) {
            root.traverseHelper();
        }
    }

    // Search for a key in the B-tree
    // Returns the node containing the key if found, otherwise returns null
    BTreeNode search(int k) {
        if (root == null) {
            return null;
        }
        return root.search(k);
    }
    
}
