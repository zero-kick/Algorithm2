import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1926 {
    public static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());   // 세로(행의 개수)
        int m = Integer.parseInt(st.nextToken());   // 가로(열의 개수)

        int[][] paper = new int[n][m];

        for(int i = 0; i < n; i++) {
            String[] strArr = br.readLine().split(" ");
            for(int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(strArr[j]);
            }
        }
        br.close();

        boolean[][] visit = new boolean[n][m];

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        Queue<Pair> q = new LinkedList<Pair>();

        int cnt = 0;
        int area = 0;
        int maxArea = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // 시작점 찾기
                // 방문한적이 있거나, 그림이 안그려져 있으면 skip
                if(visit[i][j] || paper[i][j] == 0) {
                    continue;
                }

                // 새로운 그림 시작
                cnt++;                      // 그림 개수 +1
                area++;                     // 그림 넓이 + 1
                q.offer(new Pair(i, j));    // 큐에 칸을 넣는다.
                visit[i][j] = true;         // 방문처리

                while(!q.isEmpty()) {
                    Pair pollArea = q.poll();

                    // 인접 칸 방문(하 > 좌 > 상 > 우)
                    for(int k = 0; k < 4; k++) {
                        int nx = pollArea.x + dx[k];
                        int ny = pollArea.y + dy[k];

                        // 종이의 영역을 넘어가는지 validation
                        if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                        // 그림이 그려져 있으면서, 방문한적이 없으면, 큐에 넣고 방문처리
                        if(paper[nx][ny] == 1 && !visit[nx][ny]) {
                            area++;
                            q.offer(new Pair(nx, ny));
                            visit[nx][ny] = true;
                        }
                    }
                }
                
                // 최대 넓이 갱신
                if(area > maxArea) {
                    maxArea = area;
                }
                
                // 그림 하나를 끝냈으므로, 넓이는 초기화
                area = 0;

            }
        }

        bw.write(String.valueOf(cnt) + "\n");
        bw.write(String.valueOf(maxArea));
        bw.flush();
        bw.close();
    }
}
