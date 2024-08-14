import java.util.Scanner;
import java.util.Stack;
public class Stack_programs {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter the string : ");
        String input= scan.next();
        System.out.println("Postfix : "+infixToPostfix(input));
        System.out.println("Prefix : "+infixToPrefix(input));
        System.out.println("Infix : "+prefixToInfix(infixToPrefix(input)));
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
    // function for infix to prefix Conversion
    public static String infixToPrefix(String input) {
        StringBuffer prefix=new StringBuffer();
        Stack<Character> stack=new Stack<>();
        for (int i=input.length()-1;i>=0;i--){
            char c= input.charAt(i);
            if (c>='A'&&c<='Z'||c>='a'&&c<='z'||c>='0'&&c<='9'){
                prefix.insert(0,c);
            }
            else if (c==')'){
                stack.push(c);
            } else if (c=='(') {
                while (!stack.isEmpty()&&stack.peek()!=')'){
                    prefix.insert(0,stack.pop());
                }
                stack.pop();
            }
            else {
                while (!stack.isEmpty()&&priority(c)<priority(stack.peek())||
                        priority(c)==priority(stack.peek())&&associativity(c)=='L'){
                    prefix.insert(0,stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()){
            prefix.insert(0,stack.pop());
        }
        return prefix.toString();
    }
    // function for prefix to infix Conversion
    public static String prefixToInfix(String prefix){
        Stack<String> stack =new Stack<>();
        for (int i=prefix.length()-1;i>=0;i--){
            char c= prefix.charAt(i);
            if (isOperator(c)){
                String s1=stack.pop();
                String s2=stack.pop();
                String temp="("+s1+c+s2+")";
                stack.push(temp);
            }
            else {
                stack.push(c+"");
            }
        }
        return  stack.pop();
    }
    private static boolean isOperator(char c){
        switch (c){
            case '+':
            case '-':
            case '*':
            case '/':
            case '%':
            case '^':
                return true;
        }
        return false;
    }
}

    
