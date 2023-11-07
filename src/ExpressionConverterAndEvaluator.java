public class ExpressionConverterAndEvaluator {
    public String infixToPostfix(String infix) {
        StackUsingArray<Character> expressionStack = new StackUsingArray<>(infix.length());
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char currentChar = infix.charAt(i);

            if (isOperand(currentChar)) {
                postfix.append(currentChar);
            } else if (currentChar == '(') {
                expressionStack.push(currentChar);
            } else if (currentChar == ')') {
                while (!expressionStack.isEmpty() && expressionStack.peek() != '(') {
                    postfix.append(expressionStack.pop());
                }
                expressionStack.pop(); // Remove the '(' from the stack
            } else {
                while (!expressionStack.isEmpty() && getPrecedence(expressionStack.peek()) >= getPrecedence(currentChar)) {
                    postfix.append(expressionStack.pop());
                }
                expressionStack.push(currentChar);
            }
        }

        while (!expressionStack.isEmpty()) {
            postfix.append(expressionStack.pop());
        }

        return postfix.toString();
    }

    public int evaluatePostfix(String postfix) {
        StackUsingArray<Integer> expressionStack = new StackUsingArray<>(postfix.length());

        for (int i = 0; i < postfix.length(); i++) {
            char currentChar = postfix.charAt(i);

            if (isOperand(currentChar)) {
                expressionStack.push(Character.getNumericValue(currentChar));
            } else {
                int operand2 = expressionStack.pop();
                int operand1 = expressionStack.pop();

                if (currentChar == '+') {
                    expressionStack.push(operand1 + operand2);
                } else if (currentChar == '-') {
                    expressionStack.push(operand1 - operand2);
                } else if (currentChar == '*') {
                    expressionStack.push(operand1 * operand2);
                } else if (currentChar == '/') {
                    expressionStack.push(operand1 / operand2);
                }
            }
        }

        return expressionStack.pop();
    }

    private static boolean isOperand(char c) {
        return Character.isDigit(c);
    }

    private static int getPrecedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        }
        return 0;
    }
}
