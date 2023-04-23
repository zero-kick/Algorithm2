import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {
    public static int n, k;
    public static int[] subin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        br.close();

        subin = new int[100001];
        Arrays.fill(subin, -1);

        Queue<Integer> q = new LinkedList<Integer>();
        subin[n] = 0;
        q.offer(n);

        while(!q.isEmpty()) {
            int bfN = q.poll();

            if(bfN == k) {
                System.out.println(subin[bfN]);
            }

            for(int i = 0; i < 3; i++) {
                if(i == 0) {
                    if((bfN+1) <= 100000 && subin[bfN + 1] == -1) {
                        q.offer(bfN + 1);
                        subin[bfN + 1] = subin[bfN] + 1;
                    }
                } else if(i == 1) {
                    if((bfN-1) >= 0 && subin[bfN - 1] == -1) {
                        q.offer(bfN - 1);
                        subin[bfN - 1] = subin[bfN] + 1;
                    }
                } else {
                    if((bfN*2) <= 100000 && subin[bfN * 2] == -1) {
                        q.offer(bfN * 2);
                        subin[bfN * 2] = subin[bfN] + 1;
                    }
                }
            }
        }
    }
}
