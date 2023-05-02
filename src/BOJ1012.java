import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012 {
    public static int t, m, n, k;
    public static int[][] farm;
    public static boolean[][] visit;

    public static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());       // 테스트케이스 개수

        for(int test = 0; test < t; test++) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());       // 배추농장의 열 개수
            n = Integer.parseInt(st.nextToken());       // 배추농장의 행 개수
            k = Integer.parseInt(st.nextToken());       // 배추의 개수

            // 배추 심기 (1로 세팅)
            farm = new int[n][m];
            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                farm[x][y] = 1;
            }

            bw.write(String.valueOf(findFarm()) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static int findFarm() {
        // 인접한 칸 탐색을 위한 델타값 세팅 (하, 좌, 상, 우)
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        // 탐색을 위한 큐 생성
        Queue<Pair> q = new LinkedList<Pair>();
        // 방문처리를 위한 배열 생성
        visit = new boolean[n][m];
        // 배추가 몇군데에 무리지어 있는지 체크
        int cnt = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // 배추 위치 찾기 (시작점 찾기)
                // 배추가 심어져 있지 않거나, 방문한적이 있으면 skip
                if(farm[i][j] == 0 || visit[i][j]) continue;

                // 배추 무리 + 1
                cnt++;

                // 시작 배추 심고, 방문 처리
                q.offer(new Pair(i, j));
                visit[i][j] = true;

                // 인접 칸 탐색
                while(!q.isEmpty()) {
                    Pair pollCell = q.poll();

                    for(int k = 0; k < 4; k++) {
                        int nx = pollCell.x + dx[k];
                        int ny = pollCell.y + dy[k];

                        // 농장 범위를 벗어나거나, 배추가 심어져 있지 않거나, 이미 방문한 칸인 경우 skip
                        if(isNotRange(nx, ny) || farm[nx][ny] == 0 || visit[nx][ny]) continue;

                        // 방문 처리
                        q.offer(new Pair(nx, ny));
                        visit[nx][ny] = true;
                    }

                }
            }
        }

        return cnt;
    }

    public static boolean isNotRange(int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= m) return true;
        else return false;
    }
}
