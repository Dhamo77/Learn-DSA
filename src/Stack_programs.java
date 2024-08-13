import java.util.Scanner;
public class Stack_programs {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter the string : ");
        String input= scan.next();
        System.out.println(infixToPostfix(input));
    }

    // function for  Infix expression to Postfix expression
    public static String infixToPostfix(String input) {
        Stack<Character> stack = new Stack<>();
        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || (c >= '0' && c <= '9')) {
                ans.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    ans.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && (priority(c) < priority(stack.peek()) ||
                        priority(c) == priority(stack.peek()) && associativity(c) == 'L'))
                {
                    ans.append(stack.pop());
                }
                stack.push(c);

            }
        }
        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        return ans.toString();
    }
    private static int priority(char c) {
        if (c == '^')
            return 3;
        else if (c == '/' || c == '*')
            return 2;
        else if (c == '+' || c == '-')
            return 1;
        else
            return -1;
    }
    private static char associativity(char c) {
        if (c == '^')
            return 'R';
        return 'L';
    }
}

    
