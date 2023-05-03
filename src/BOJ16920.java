import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16920 {
    public static int n, m, p;              // 맵 사이즈, 플레이어 수
    public static char[][] map;             // 게임 맵 (격자판)
    public static boolean[][] visited;      // 방문처리
    public static int[] s;                  // 플레이어별 최대 확장 거리
    public static int[] cnt;                // 플레이어별 성 개수
    public static int[] dx = {-1, 1, 0, 0}; // 인접칸 탐색을 위한 변화값
    public static int[] dy = {0, 0, -1, 1}; // 인접칸 탐색을 위한 변화값
    public static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static Queue<Node>[] qs;         // 플레이어 수만큼 큐를 가져가기 위함

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());   // 맵의 열 크기
        m = Integer.parseInt(st.nextToken());   // 맵의 행 크기
        p = Integer.parseInt(st.nextToken());   // 플레이어 수
        map = new char[n][m];                   // 맵 세팅
        visited = new boolean[n][m];            // 방문처리

        qs = new Queue[p+1];                    // 플레이어 1부터 index 1에 넣기 위해 사이즈 1 크게 잡음
        for(int i = 0; i <= p; i++) {
            qs[i] = new LinkedList<Node>();     // 배열의 각 원소에 큐 세팅
        }

        s = new int[p+1];                       // 플레이어 1부터 index 1에 넣기 위해 사이즈 1 크게 잡음
        cnt = new int[p+1];                     // 플레이어 1부터 index 1에 넣기 위해 사이즈 1 크게 잡음

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= p; i++) {
            s[i] = Integer.parseInt(st.nextToken());    // 플레이어별 최대 확장 거리 세팅
        }

        for(int i = 0; i < n; i++) {
            String[] strArr = br.readLine().split("");
            for(int j = 0; j < m; j++) {
                char ch = strArr[j].charAt(0);
                map[i][j] = ch;                         // 플레이어 성, 벽 세팅

                // 성이 있는 칸이면 각 플레이어별 시작 노드로 세팅
                if(ch != '.' && ch != '#') {
                    int player = Character.getNumericValue(ch);
                    qs[player].offer(new Node(i, j));
                    visited[i][j] = true;
                    cnt[player]++;
                }
            }
        }

        // 아무 플레이어도 더이상 성을 확장할 수 없을때까지 라운드 진행
        while(true) {
            if(round()) break;
        }

        // 정답 출력
        for(int i = 1; i <= p; i++) {
            bw.write(String.valueOf(cnt[i]) + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean round() {
        int emptyPlayer = 0;        // 플레이어의 큐가 비어있는지 체크를 위함

        // 플레이어별로 돌아가며 라운드 진행
        for(int player = 1; player <= p; player++) {
            // 해당 플레이어의 큐가 비어있다면 더이상 성을 확장할 수 없다는 것이므로 emptyPlayer++ 하고 다음 플에이어 턴으로 넘김
            if(qs[player].isEmpty()) {
                emptyPlayer++;
                continue;
            }

            // 한 명의 플레이어씩 턴을 진행
            turnStart(player);
        }

        return emptyPlayer == p;    // 모든 플레이어의 큐가 비어있으면 true를 리턴하여 위에서 while문 break하도록 함
    }

    public static void turnStart(int player) {
        // 플레이어의 최대 확장 거리 만큼 성 확장 진행 (중요)
        for(int ext = 0; ext < s[player]; ext++) {
            // 처음에 들고 있는 큐의 사이즈 만큼만 확장을 진행해야 한 턴이 끝나는 것이기에 size 만큼만 탐색
            // 변수를 별도로 잡지 않고 qs[player].size()를 사용하게 되면 탐색 과정에서 추가로 큐에 원소가 들어오게 되면,
            // 진작 턴이 종료되어야 했음에도 더 턴을 이어나가는 꼴이 됨
            int size = qs[player].size();

            for(int i = 0; i < size; i++) {
                Node cur = qs[player].poll();

                for(int k = 0; k < 4; k++) {
                    int nx = cur.x + dx[k];
                    int ny = cur.y + dy[k];

                    if(isNotRange(nx, ny) || visited[nx][ny] || map[nx][ny] == '#') continue;

                    qs[player].offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                    map[nx][ny] = Character.forDigit(player, 10);
                    cnt[player]++;
                }
            }
        }
    }

    public static boolean isNotRange(int x, int y) {
        return (x < 0 || x >= n || y < 0 || y >= m) ? true : false;
    }
}