import java.util.List;
import java.util.Scanner;

public class MainB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // binary tree delartion
        BinarySearchTree bst = new BinarySearchTree(); // Binary Search Tree declaration
        AVLTree avlTree = new AVLTree(); // AVL Tree declaration
        // Red Black Tree declaration
        RedBlackTree rbt = new RedBlackTree(); // Red Black Tree declaration
        // B-tree declaration
        BTree bTree = new BTree(3); // B-tree declaration with minimum degree 3

        int[] elements = {7, 5, 9, 4, 6, 8, 13, 2};

        // Insert elements into the Binary Search Tree
        for (int element : elements){
            bst.insert(element);
        }

        // Array of nine elements to be inserted into the AVL, B-Tree and Red Black Trees
        int[] allNineElements = {7, 5, 9, 4, 6, 8, 13, 2, 3};

        while (true) {
            System.out.println("\n===================TREE IMPLEMENTATION MENU===================\n");
            System.out.println("1. Binary Search Tree (Inserting Node 3)");
            System.out.println("2. Binary Search Tree (Postorder transversal)");
            System.out.println("3. AVL (Insert the nine elements)");
            System.out.println("4. Red Black Tree (Insert the nine elements and display Postorder transversal)");
            System.out.println("5. B-trees (Implementation and search for key 8)");
            System.out.println("6: Exit");

            System.out.println("Enter your choice (a number 1-6): ");
            int choice = sc.nextInt();

            // Switch case to handle user input
            switch (choice) {
                case 1 -> {
                    System.out.println("Inserting node 3 into the Binary Search Tree...");
                    bst.insert(3);
                    System.out.println("Node 3 inserted successfully.");
                }
                case 2 -> {
                    System.out.println("Postorder traversal of the Binary Search Tree:");
                    List<Integer> postOrderResult = bst.postOrderTraversal(bst.root);
                    // Print the postorder traversal result
                    for (int value : postOrderResult) {
                        System.out.print(value + " ");
                    }
                    // Print the insertion order
                    System.out.println("\nPostorder traversal completed.");
                }
                case 3 -> {
                    System.out.println("Inserting the nine elements into the AVL Tree...");
                    // Insert the nine elements into the AVL tree
                    for (int element : allNineElements) {
                        avlTree.insert(element);
                    }
                    // Display the AVL tree
                    avlTree.display();
                }
                case 4 -> {
                    System.out.println("Inserting the nine elements into the Red Black Tree...");
                    // Insert the nine elements into the Red Black tree
                    for (int element : allNineElements) {
                        rbt.insert(element);
                    }
                    System.out.println("Postorder traversal of the Red Black Tree:");
                    rbt.postOrder();
                    System.out.println("\nPostorder traversal completed.");
                }
                case 5 -> {
                    System.out.println("Inserting the nine elements into the B-tree...");
                    // Insert the nine elements into the B-tree
                    for (int element : allNineElements) {
                        bTree.insert(element);
                    }
                    System.out.println("Searching for key 8 in the B-tree...");
                    if (bTree.search(8) != null)
                        System.out.println("Key 8 found in the B-tree.");
                    else
                        System.out.println("Key 8 not found in the B-tree.");
                }
                case 6 -> {
                    // Exit the program when the user chooses option 6
                    System.out.println("Thank you for using the Tree Implementation Program!");
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                // Default case for invalid input
                // This case will be executed if the user enters a number outside the range of 1-6
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}