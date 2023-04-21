import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        Stack<Character> leftStk = new Stack<Character>();
        Stack<Character> rightStk = new Stack<Character>();

        for(int i = 0; i < str.length(); i++) {
            leftStk.push(str.charAt(i));
        }

        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch(st.nextToken()) {
                case "L":
                    if(leftStk.isEmpty()) break;
                    rightStk.push(leftStk.pop());
                    break;
                case "D":
                    if(rightStk.isEmpty()) break;
                    leftStk.push(rightStk.pop());
                    break;
                case "B":
                    if(leftStk.isEmpty()) break;
                    leftStk.pop();
                    break;
                case "P":
                    leftStk.push(st.nextToken().charAt(0));
                    break;
            }
        }

        while(!leftStk.isEmpty()) {
            rightStk.push(leftStk.pop());
        }
        while(!rightStk.isEmpty()) {
            bw.write(rightStk.pop());
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
