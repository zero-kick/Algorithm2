import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5014 {
    public static int f, s, g, u, d;
    public static int[] visit;
    public static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        f = Integer.parseInt(st.nextToken());       // 건물 총 층수
        s = Integer.parseInt(st.nextToken());       // 강호 위치
        g = Integer.parseInt(st.nextToken());       // 목적지
        u = Integer.parseInt(st.nextToken());       // 위로 u층
        d = Integer.parseInt(st.nextToken());       // 아래로 d층
        br.close();

        visit = new int[f];         // 강호 방문, 이동 횟수
        Arrays.fill(visit, -1); // 최초에는 -1 세팅
        q = new LinkedList<Integer>();

        int[] df = {u, d * -1};

        // 강호 시작 위치 세팅
        q.offer(s);
        visit[s-1] = 0;

        while(!q.isEmpty()) {
            int pollCell = q.poll();

            // 강호가 스타트링크에 도착하면 이동 횟수 출력 후 return
            if(pollCell == g) {
                System.out.println(visit[pollCell-1]);
                return;
            }

            for(int k = 0; k < 2; k++) {
                int nf = pollCell + df[k];

                // 빌딩 범위를 벗어나거나, 이미 방문한 층이라면 skip
                if(isNotRange(nf) || isVisit(nf)) continue;

                q.offer(nf);
                visit[nf-1] = visit[pollCell-1] + 1;
            }
        }
        System.out.println("use the stairs");
    }

    public static boolean isNotRange(int x) {
        return (x < 0 || x > f) ? true : false;
    }

    public static boolean isVisit(int x) {
        if(x - 1 < 0 || visit[x-1] != -1) return true;
        else return false;
    }
}
