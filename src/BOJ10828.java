import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ10828 <T> {
    public List<T> stack = new ArrayList<T>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        BOJ10828<Integer> intClass = new BOJ10828<Integer>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            switch(st.nextToken()) {
                case "push" :
                    intClass.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop" :
                    bw.write(String.valueOf(intClass.pop()));
                    bw.newLine();
                    break;
                case "size" :
                    bw.write(String.valueOf(intClass.size()));
                    bw.newLine();
                    break;
                case "empty" :
                    bw.write(String.valueOf(intClass.empty()));
                    bw.newLine();
                    break;
                case "top" :
                    bw.write(String.valueOf(intClass.top()));
                    bw.newLine();
                    break;
            }
        }

        br.close();
        bw.flush();
        bw.close();

    }

    public void push(T x) {
        this.stack.add(x);
    }

    public int pop() {
        return this.stack.isEmpty() ? -1 : (int) this.stack.remove(this.stack.size()-1);
    }

    public int size() {
        return this.stack.size();
    }

    public int empty() {
        return this.stack.isEmpty() ? 1 : 0;
    }

    public int top() {
        return this.stack.isEmpty() ? -1 : (int) this.stack.get(this.stack.size()-1);
    }
}
