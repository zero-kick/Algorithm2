import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20304 {
    public static int n, m;
    public static int[] safeDis;
    public static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // 안전거리 초기화
        safeDis = new int[1000001];
        Arrays.fill(safeDis, Integer.MIN_VALUE);

        // 해커가 시도한 비밀번호는 안전거리 0으로 세팅하고 큐에 담는다.
        q = new LinkedList<Integer>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            int p = Integer.parseInt(st.nextToken());
            q.offer(p);
            safeDis[p] = 0;
        }

        bw.write(String.valueOf(findSafeDis()));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int findSafeDis() {
        int maxSafeDis = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();

            // n의 최댓값인 1,000,000은 2진법으로 20자리이므로, 0 ~ 19까지 확인한다.
            for(int i = 0; i < 20; i++) {
                int nx = cur^(1<<i);

                // 비밀번호의 최댓값을 초과하거나, 이미 안전거리 계산을 마친 비밀번호인 경우 skip
                if(nx > n || (safeDis[nx] != Integer.MIN_VALUE)) continue;

                q.offer(nx);
                safeDis[nx] = safeDis[cur] + 1;
                maxSafeDis = Math.max(maxSafeDis, safeDis[nx]);
            }
        }

        return maxSafeDis;
    }
}