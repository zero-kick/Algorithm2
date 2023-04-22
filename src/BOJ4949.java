import java.io.*;
import java.util.Stack;

public class BOJ4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = "";

        while(true) {
            str = br.readLine();

            if(str.equals(".")) break;

            sb.append(isBalance(str) + "\n");
        }
        br.close();

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
    }

    public static String isBalance(String str) {
        Stack<Character> stack = new Stack<Character>();

        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(' || str.charAt(i) == '[') {
                stack.push(str.charAt(i));
            } else if(str.charAt(i) == ')') {
                if(!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(str.charAt(i));
                }
            } else if(str.charAt(i) == ']') {
                if(!stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                } else {
                    stack.push(str.charAt(i));
                }
            }
        }

        return stack.isEmpty() ? "yes" : "no";
    }
}