import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {
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

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] maze = new int[n][m];

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                maze[i][j] = Integer.parseInt(str.substring(j, j+1));
            }
        }
        br.close();

        Queue<Pair> q = new LinkedList<Pair>();

        int[][] dist = new int[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // find Start
                // 이동할 수 없는 칸이거나, 이미 방문한 칸이면 skip
                if(maze[i][j] == 0 || dist[i][j] > -1) continue;

                // Start
                q.offer(new Pair(i, j));    // 큐에 칸을 넣는다.
                dist[i][j] = 0;             // 방문처리(+ 거리계산)

                while(!q.isEmpty()) {
                    Pair pollCell = q.poll();

                    // 인접 칸 탐색
                    for(int k = 0; k < 4; k++) {
                        int nx = pollCell.x + dx[k];
                        int ny = pollCell.y + dy[k];

                        // 해당 칸이 범위를 벗어나지 않는지 체크
                        if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                        // 방문하지 않은 칸이면서, 이동 가능한 칸이면
                        if(dist[nx][ny] == -1 && maze[nx][ny] == 1) {
                            q.offer(new Pair(nx, ny));      // 큐에 칸을 넣는다.
                            dist[nx][ny] = dist[pollCell.x][pollCell.y] + 1;    // 방문처리(바로 이전 칸보다 거리가 +1)
                        }
                    }
                }

            }
        }

        // 시작칸부터 거리가 1이므로 +1 해주어야 함
        int distance = dist[n-1][m-1] + 1;

        bw.write(String.valueOf(distance));
        bw.flush();
        bw.close();
    }
}
