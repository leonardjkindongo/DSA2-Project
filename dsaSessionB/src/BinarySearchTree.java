import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    TreeNode root;
    List<Integer> insertionOrder = new ArrayList<>();

    public void insert(int value) {
        if (!search(value)) { // Check if the value already exists
            // If the value does not exist, insert it into the tree
            root = insertHelper(root, new TreeNode(value));
            insertionOrder.add(value);
        }
    }
    // Helper method to insert a new node into the tree
    private TreeNode insertHelper(TreeNode root, TreeNode node) {
        if (root == null) {
            return node;
        }
        if (node.value < root.value) {
            root.left = insertHelper(root.left, node);
        } else {
            root.right = insertHelper(root.right, node);
        }
        return root;
    }

    public boolean search(int value) {
        return searchHelper(root, value);
    }

    private boolean searchHelper(TreeNode root, int value) {
        if (root == null) {
            return false;
        }
        if (root.value == value) {
            return true;
        }
        return value < root.value ? searchHelper(root.left, value) : searchHelper(root.right, value);
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrderHelper(root, result);
        return result;
    }

    private void postOrderHelper(TreeNode root, List<Integer> result) {
        // Base case: if the current node is null, return
        if (root == null) {
            return;
        }
        // Traverse the left subtree, then the right subtree, and finally visit the current node
        // Traverse the left subtree
        postOrderHelper(root.left, result);
        // Traverse the right subtrees
        postOrderHelper(root.right, result);
        result.add(root.value);
    }
}
