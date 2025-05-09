import java.util.List;
import java.util.Scanner;

public class MainB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // binary tree delartion
        BinarySearchTree bst = new BinarySearchTree(); // Binary Search Tree declaration
        // AVL tree declaration

        int[] elements = {7, 5, 9, 4, 6, 8, 13, 2};

        for (int element : elements){
            bst.insert(element);
        }

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

            // switch (choice)
            switch (choice) {
                case 1:
                    System.out.println("Inserting node 3 into the Binary Search Tree...");
                    bst.insert(3);
                    System.out.println("Node 3 inserted successfully.");
                    break;
                case 2:
                    System.out.println("Postorder traversal of the Binary Search Tree:");
                    List<Integer> postOrderResult = bst.postOrderTraversal(bst.root);
                    for (int value : postOrderResult) {
                        System.out.print(value + " ");
                    }
                    // Print the insertion order
                    System.out.println("\nPostorder traversal completed.");
                    break;
                case 3:
                    // TODO AVL tree insert elements
                    break;
                case 4:
                    // TODO Red Black Tree insert elements and postorder transversal
                    break;
                case 5:
                    // TODO B-trees implementation and search for key 8
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}