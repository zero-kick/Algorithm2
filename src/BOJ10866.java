import java.io.*;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> dq = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push_front":
                    dq.offerFirst(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back":
                    dq.offerLast(Integer.parseInt((st.nextToken())));
                    break;
                case "pop_front":
                    if(dq.isEmpty()) bw.write(String.valueOf(-1) + "\n");
                    else bw.write(String.valueOf(dq.pollFirst()) + "\n");
                    break;
                case "pop_back":
                    if(dq.isEmpty()) bw.write(String.valueOf(-1) + "\n");
                    else bw.write(String.valueOf(dq.pollLast()) + "\n");
                    break;
                case "size":
                    bw.write(String.valueOf(dq.size()) + "\n");
                    break;
                case "empty":
                    if(dq.isEmpty()) bw.write(String.valueOf(1) + "\n");
                    else bw.write(String.valueOf(0) + "\n");
                    break;
                case "front":
                    if(dq.isEmpty()) bw.write(String.valueOf(-1) + "\n");
                    else bw.write(String.valueOf(dq.peekFirst()) + "\n");
                    break;
                case "back":
                    if(dq.isEmpty()) bw.write(String.valueOf(-1)+ "\n");
                    else bw.write(String.valueOf(dq.peekLast()) + "\n");
                    break;
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
