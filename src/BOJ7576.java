import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576 {
    
    // 큐가 x, y 좌표를 갖고 있어야 하므로 클래스 생성
    public static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // 상자에 토마토 넣기
        int[][] box = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        // 각 칸 방문 체크를 위한 배열 생성 (+ 익은 날 체크)
        int[][] grow = new int[n][m];
        // 방문한적이 없으면 -1로 표시 (최초에는 모두 -1)
        for(int i = 0; i < n; i++) {
            Arrays.fill(grow[i], -1);
        }

        // 인접한 칸을 체크하기 위한 변화값을 담은 배열 생성 (하, 좌, 상, 우)
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        // BFS를 위한 큐 생성
        Queue<Pair> queue = new LinkedList<Pair>();

        // 최초 시작 시 익은 토마토가 여러개일 수 있으므로 동시에 탐색하기 위해 큐에 등록하고 방문처리
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(box[i][j] == 1) {
                    queue.offer(new Pair(i, j));
                    grow[i][j] = 0;     // 보관 다음날부터 1일 경과이므로 최초에는 0일로 세팅
                }
            }
        }

        int days = 0;

        // BFS
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // 인접 칸 탐색
                while(!queue.isEmpty()) {
                    // 탐색 전 탐색하려는 칸 poll
                    Pair pollCell = queue.poll();

                    for(int k = 0; k < 4; k++) {
                        int nx = pollCell.x + dx[k];
                        int ny = pollCell.y + dy[k];

                        // 인접칸이 박스 범위에서 벗어나면 skip
                        if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                        // 익지 않은 토마토가 들어있고, 방문한적이 없는(익지 않은) 칸이면
                        if(box[nx][ny] == 0 && grow[nx][ny] == -1) {
                            queue.offer(new Pair(nx, ny));
                            grow[nx][ny] = grow[pollCell.x][pollCell.y] + 1;  // 이전 경과일수보다 +1 (하루 더 걸려서 익은 것이므로)
                            days = grow[nx][ny];
                        }
                    }
                }
            }
        }

        // 특이사항 체크
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(box[i][j] != -1 && grow[i][j] == -1) {  // 토마토가 있는 칸인데, 익지 않은 토마토가 있는 경우
                    days = -1;
                }

                if(box[i][j] == 0) {     // 처음부터 모든 토마토가 익어있던 경우 (박스에 0이 없는 경우)
                    cnt++;
                }
            }
        }
        if(cnt == 0) {
            days = 0;
        }

        System.out.println(days);

    }
}
