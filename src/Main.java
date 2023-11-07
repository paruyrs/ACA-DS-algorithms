import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("\nStack with using Array \n");
//        StackUsingArray<Integer> stackArray = new StackUsingArray<>(5);
//
//        stackArray.push(1);
//        stackArray.push(2);
//        stackArray.push(3);
//
//        System.out.println("Top element: " + stackArray.peek());
//        System.out.println("Stack size: " + stackArray.size());
//
//        while (!stackArray.isEmpty()) {
//            System.out.println("Popped: " + stackArray.pop());
//        }
//
//        System.out.println("\nStack with using linked List \n");
//
//        StackUsingLinkedList<Integer> stackLinkedList = new StackUsingLinkedList<Integer>();
//
//        stackLinkedList.push(1);
//        stackLinkedList.push(2);
//        stackLinkedList.push(3);
//
//        System.out.println("Top element: " + stackLinkedList.peek());
//        System.out.println("Stack size: " + stackLinkedList.size());
//
//        while (!stackLinkedList.isEmpty()) {
//            System.out.println("Popped: " + stackLinkedList.pop());
//        }
//
//        System.out.println("\nexpression infix to postfix conversion and expression evaluation\n");
//
//        ExpressionConverterAndEvaluator expressionConverterAndEvaluator = new ExpressionConverterAndEvaluator();
//        Scanner scanner = new Scanner(System.in);
//
//        // Infix to Postfix Conversion
//        System.out.print("Enter an infix expression: ");
//        String infixExpression = scanner.nextLine();
//        String postfixExpression = expressionConverterAndEvaluator.infixToPostfix(infixExpression);
//        System.out.println("Postfix Expression: " + postfixExpression);
//
//
//        int result = expressionConverterAndEvaluator.evaluatePostfix(postfixExpression);
//        System.out.println("Result of Expression: " + result);
//        scanner.close();


        System.out.println("\nBinary Search Tree\n");

        BinarySearchTree bst = new BinarySearchTree();

        // Insert values into the BST
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("In-order traversal of the BST (Recursive):");
        bst.printTree();

        int searchValue = 30;
        boolean searchResult = bst.search(searchValue);
        System.out.println("Search for value " + searchValue + ": " + searchResult);

        int deleteValue = 30;
        bst.delete(deleteValue);
        System.out.println("In-order traversal after deleting " + deleteValue + " (Recursive):");
        bst.printTree();

        // Perform iterative traversals
        System.out.println("In-order traversal of the BST (Iterative):");
        bst.inorderTraversalIterativeLNR().forEach(value -> System.out.print(value + " "));
        System.out.println();

        System.out.println("Pre-order traversal of the BST (Iterative):");
        bst.preorderTraversalIterativeNLR().forEach(value -> System.out.print(value + " "));
        System.out.println();

        System.out.println("Post-order traversal of the BST (Iterative):");
        bst.postorderTraversalIterativeLRN().forEach(value -> System.out.print(value + " "));
        System.out.println();

        System.out.println("Level-order traversal of the BST (Iterative):");
        bst.levelOrderTraversalIterative().forEach(value -> System.out.print(value + " "));
        System.out.println();
    }
}