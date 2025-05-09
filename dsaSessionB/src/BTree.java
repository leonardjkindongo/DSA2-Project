import java.util.ArrayList;
import java.util.List;

class BTreeNode {
        List<Integer> keys;
    List<BTreeNode> children;
    boolean leaf;
    int t; // Minimum degree

    BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    int findKey(int k) {
        int idx = 0;
        while (idx < keys.size() && keys.get(idx) < k)
            ++idx;
        return idx;
    }

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
            children.get(temp).insertNonFull(k);
        }
    }

    void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        // Move the last t-1 keys from y to z
        for (int j = 0; j < y.keys.size() - y.t; j++) {
            z.keys.add(y.keys.get(y.t + j));
        }
        // Clear the keys in y after moving
        for (int j = y.t - 1; j < y.keys.size(); j++) {
            y.keys.remove(y.t - 1);
        }

        if (!y.leaf) {
            // Move the last t children from y to z
            for (int j = 0; j < y.children.size() - y.t; j++) {
                z.children.add(y.children.get(y.t + j));
            }
            // Clear the children in y after moving
            for (int j = y.t; j < y.children.size(); j++) {
                y.children.remove(y.t);
            }
        }

        // Insert z as a child of this node
        children.add(i + 1, z);
        keys.add(i, y.keys.get(y.t - 1));
        y.keys.remove(y.t - 1);
    }

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
    void traverse() {
        if (root != null) {
            root.traverseHelper();
        }
    }

    BTreeNode search(int k) {
        if (root == null) {
            return null;
        }
        return root.search(k);
    }
    
}
