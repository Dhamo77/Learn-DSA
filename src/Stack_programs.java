import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
public class Stack_programs {
    static Scanner scan = new Scanner(System.in);
   static Stack<Integer> s=new Stack<>();
    public static void main(String[] args) {
        int price[] = { 10, 4, 5, 90, 120, 80 };
        int[] span=stockSpan(price);
        System.out.println(Arrays.toString(span));
    }

    // method for  Infix expression to Postfix expression
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
    // method for infix to prefix Conversion
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
                while (!stack.isEmpty() && (priority(c)<priority(stack.peek())||
                        priority(c)==priority(stack.peek())&&associativity(c)=='L')){
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
    // method for prefix to infix Conversion
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
    // method for prefix to postfix
    public  static String preToPost(String prefix){
        Stack<String> stack =new Stack<>();
        char c;
        for (int i=prefix.length()-1;i>=0;i--){
            c=prefix.charAt(i);
            if(isOperator(c)){
                String temp =stack.pop()+stack.pop()+c;
                stack.push(temp);
            }
            else {
                stack.push(c+"");
            }
        }
        return stack.pop();
    }
    public  static String postToPre(String postfix){
        Stack<String> stack =new Stack<>();
        char c;
        for (int i=0;i<postfix.length();i++){
            c=postfix.charAt(i);
            if (isOperator(c)){
                String s1=stack.pop();
                String s2=stack.pop();
                String temp=c+s2+s1;
                stack.push(temp);
            }
            else {
                stack.push(c+"");
            }
        }
        return stack.pop();
    }
    // method for postfix to infix
    public static String postToInfix(String postfix){
        char c;
        Stack<String> stack=new Stack<>();
        for (int i=0;i<postfix.length();i++){
            c=postfix.charAt(i);
            if (isOperator(c)){
                String s1=stack.pop();
                String s2=stack.pop();
                String temp="("+s2+c+s1+")";
                stack.push(temp);
            }
            else {
                stack.push(c+"");
            }
        }
        return stack.pop();
    }

    // method Check for Balanced Brackets in an expression
    public static String balancedBrackets(String input){
        Stack<Character> stack =new Stack<>();
        char c;
        for (int i=0;i<input.length();i++){
            c=input.charAt(i);
            if (stack.isEmpty()&&(c==']'||c=='}'||c==')')){
                return "Unbalanced";
            }
            else if (c=='('||c=='{'||c=='['){
                stack.push(c);
            }
            else if ((stack.peek()=='{'&&(c!=']'&&c!=')'))||(stack.peek()=='('&&(c!=']'&&c!='}'))||(stack.peek()=='['&&(c!='}'&&c!=')'))){
                stack.pop();
            }
        }
        if (!stack.isEmpty())
            return "Unbalanced";
        return "Balanced";
    }
    // method for Arithmetic Expression Evaluation
    public static int arithmeticEvaluation(String input){
      Stack<Integer> value=new Stack<>();
      Stack<Character> operation = new Stack<>();
      char c;
      for (int i=0;i<input.length();i++){
          c=input.charAt(i);
          if (c==' ');
          else if (c>='0'&&c<='9'){
              StringBuffer num=new StringBuffer();
              while (i<input.length()&&input.charAt(i)>='0'&&input.charAt(i)<='9'){
                  num.append(input.charAt(i++));
              }
              value.push(Integer.parseInt(num.toString()));
              i--;
          } else if (c=='(') {
              operation.push(c);

          } else if (c==')') {
              while (operation.peek()!='('){
                  value.push(mathOperation(value.pop(),value.pop(),operation.pop()));
              }
              operation.pop();

          } else if (isOperator(c)) {
              while (!operation.isEmpty()&&hasPrecedence(c,operation.peek()))
              {
                  value.push(mathOperation(value.pop(),value.pop(),operation.pop()));
              }
              operation.push(c);
          }
      }
      while (!operation.isEmpty()){
          value.push(mathOperation(value.pop(),value.pop(),operation.pop()));
      }
      return value.pop();
    }
    private static boolean hasPrecedence(
            char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') &&
                (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    private static int mathOperation(int n1,int n2,char operator ){
        int ans = 0;
        switch (operator){
            case '+':
                 ans=n1+n2;
                 break;
            case '*':
                 ans=n1*n2;
                break;
            case '-':
                 ans=n2-n1;
                break;
            case '/':
                 ans=n2/n1;
                break;
            case '%':
                 ans=n2%n1;
                break;
            case '^':
                 ans=(int) Math.pow(n2, n1);
                break;
        }
        return ans;
    }
    // method for Reverse a Stack using Recursion
    public static  void reverseStack(){
        if (s.isEmpty()){
            return;
        }
        int x=s.peek();
        s.pop();
        reverseStack();
        addBottom(x);
    }
    private static void addBottom(int x) {
        if (s.isEmpty()){
            s.push(x);
        }
        else {
            int k=s.peek();
            s.pop();
            addBottom(x);
            s.push(k);
        }
    }
    // method for Reverse a String using Stack
    public static String reverseString(String input){
        StringBuffer ans =new StringBuffer();
        Stack<Character> stack =new Stack<>();
        for (int i=0;i<input.length();i++){
            stack.push(input.charAt(i));
        }
        for (int i=0;i<input.length();i++){
            ans.append(stack.pop());
        }
        return ans.toString();
    }
    // The Stock Span Problem
    public static int[] stockSpan(int[] price){
        int length =price.length;
        int[] span =new  int[length];
        Stack<Integer> stack =new Stack<>();
        stack.push(0);
        span[0]=1;
        for (int i=1;i<length;i++){
            while(!stack.isEmpty()&&price[stack.peek()]<=price[i]){
                stack.pop();
            }
            span[i]=stack.isEmpty()?(i+1):(i- stack.peek());
            stack.push(i);
        }
        return span;
    }
    // write a method for Next Greater Element
}
