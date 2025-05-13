import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    // Binary Search Tree implementation
    // Each node contains a value, a left child, and a right child
    TreeNode root; // Root of the tree
    // TreeNode class representing each node in the tree
    List<Integer> insertionOrder = new ArrayList<>(); // List to keep track of the insertion order

    // Insert a new value into the tree
    // This method checks if the value already exists in the tree before inserting
    public void insert(int value) {
        if (!search(value)) { // Check if the value already exists
            // If the value does not exist, insert it into the tree
            root = insertHelper(root, new TreeNode(value));
            insertionOrder.add(value); // Add the value to the insertion order list
        }
    }

    // Helper method to insert a new node into the tree
    private TreeNode insertHelper(TreeNode root, TreeNode node) {
        // Base case: if the current node is null, return the new node
        // This means we have found the correct position to insert the new node
        if (root == null) {
            return node; // Return the new node
        }
        // If the value of the new node is less than the current node's value,
        // recursively insert it into the left subtree
        if (node.value < root.value) {
            // If the left child is null, insert the new node here
            // Otherwise, continue searching in the left subtree
            root.left = insertHelper(root.left, node);
        } else {
            // If the value of the new node is greater than or equal to the current node's value,
            // recursively insert it into the right subtree
            root.right = insertHelper(root.right, node);
        }
        return root; // Return the current node after insertion
    }

    public boolean search(int value) {
        // Search for a value in the tree
        // This method returns true if the value is found, false otherwise
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
        postOrderHelper(root, result); // Call the helper method to perform postorder traversal
        // The postOrderHelper method will populate the result list with the values in postorder
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

    //Main method to demonstrate insertion of node 3
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(3); // Inserting value 3
        System.out.println("Inserted 3? " + bst.search(3)); // Should print true
    }
}
