import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179 {
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

        int r = Integer.parseInt(st.nextToken());   // 미로의 행
        int c = Integer.parseInt(st.nextToken());   // 미로의 열

        // 미로 생성, 지훈이 위치 생성
        char[][] maze = new char[r][c];
        int[][] jihoon = new int[r][c];
        for(int i = 0; i < r; i++) {
            Arrays.fill(jihoon[i], -1);
        }
        Queue<Pair> qJ = new LinkedList<Pair>();

        for(int i = 0; i < r; i++) {
            String[] str = br.readLine().split("");
            for(int j = 0; j < c; j++) {
                maze[i][j] = str[j].charAt(0);
                if(str[j].charAt(0) == 'J') {
                    qJ.offer(new Pair(i, j));
                    jihoon[i][j] = 0;
                }
            }
        }
        br.close();

        // 불이 붙은 칸을 체크하기 위한 배열 생성 (+ 불이 붙은 시간 체크)
        int[][] fire = new int[r][c];
        for(int i = 0; i < r; i++) {
            Arrays.fill(fire[i], -1); // 일단 모든 칸에 불이 안난 것으로 세팅
        }

        // 불이 꼭 하나로 시작된다고 하지 않았으므로, 모든 불이 난 칸을 Start로 잡음
        Queue<Pair> q = new LinkedList<Pair>();
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(maze[i][j] == 'F') {
                    q.offer(new Pair(i, j));
                    fire[i][j] = 0;
                }
            }
        }

        // 인접한 칸에 불이 번질때 체크를 위한 변화값 세팅 (하, 좌, 상, 우)
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};
        
        // 미로찾기 시작
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                while(!q.isEmpty()) {
                    // 불의 번짐
                    // 탐색할 칸 poll
                    Pair pollCell = q.poll();

                    // 인접한 칸 탐색
                    for(int k = 0; k < 4; k++) {
                        int nx = pollCell.x + dx[k];
                        int ny = pollCell.y + dy[k];

                        // 인접한 칸이 미로 범위를 벗어나는지 체크, 벽인지 체크
                        if((nx < 0 || nx >= r || ny < 0 || ny >= c)
                            || maze[nx][ny] == '#') continue;

                        // 불이 안나있고, 벽이 아니면 불이 번짐
                        if(fire[nx][ny] == -1 && maze[nx][ny] != '#') {
                            q.offer(new Pair(nx, ny));
                            fire[nx][ny] = fire[pollCell.x][pollCell.y] + 1;
                        }

                    }

                }

                int time = 0;

                while(!qJ.isEmpty()) {
                    // 지훈이 이동
                    // 탐색할 칸 poll
                    Pair pollCellJ = qJ.poll();
                    time++;     // 경과 시간

                    // 인접한 칸 탐색
                    for(int k = 0; k < 4; k++) {
                        int nx = pollCellJ.x + dx[k];
                        int ny = pollCellJ.y + dy[k];

                        // 인접한 칸위 미로 범위를 벗어나는지, 벽인지, 불이났는지 체크
                        if((nx < 0 || nx >= r || ny < 0 || ny >= c)
                                || maze[nx][ny] == '#' || fire[nx][ny] <= time) continue;

                        // 지훈이가 도착한 시간에 불이 안나있고, 벽이 아니면 이동 (시간 + 1분)
                        if(fire[nx][ny] > time && maze[nx][ny] != '#') {
                            qJ.offer(new Pair(nx, ny));
                            jihoon[nx][ny] = jihoon[pollCellJ.x][pollCellJ.y] + 1;
                        }
                    }
                }
            }
        }

        bw.write(isEscape(r, c, fire, jihoon));
        bw.flush();
        bw.close();

    }

    public static String isEscape(int r, int c, int[][] fire, int[][] jihoon) {

        // 지훈이가 미로를 탈출했는지 확인
        String answer = "";
        int minTime = r;    // 최초에는 최대 탈출시간으로 세팅 (입력이 R줄 동안 이루어진다고 했으므로)

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                // 불이 나기 전에 가장자리에 도달했는지 체크
                if((fire[i][j] != -1 && jihoon[i][j] != -1)
                        && fire[i][j] > jihoon[i][j]
                        && (i == 0 || i == r-1 || j == 0 || j == c-1)) {
                    // 최소 탈출 시간을 구해야 함
                    if(jihoon[i][j] + 1 < minTime) {
                        minTime = jihoon[i][j] + 1;
                        answer = String.valueOf(minTime);
                    }
                }
            }
        }

        if(answer.equals("")) {
            return "IMPOSSIBLE";
        } else {
            return answer;
        }
    }
}
