import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179 {
    public static int r;
    public static int c;
    public static char[][] maze;
    public static int[][] fire;
    public static int[][] jihoon;

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

        r = Integer.parseInt(st.nextToken());   // 미로의 행
        c = Integer.parseInt(st.nextToken());   // 미로의 열

        // 미로, 불, 지훈 공간 생성
        // 불, 지훈 int 형으로 생성하는 이유는 몇분이 지났는지 함께 체크하기 위함
        maze = new char[r][c];
        fire = new int[r][c];
        jihoon = new int[r][c];

        // 불, 지훈 초기 세팅 -1 (불은 안났고, 지훈이는 아직 미로에 안들어온 것으로 세팅)
        for(int i = 0; i < r; i++) {
            Arrays.fill(fire[i], -1);
            Arrays.fill(jihoon[i], -1);
        }
        
        // 불, 지훈의 좌표 탐색을 위한 큐 생성
        Queue<Pair> qF = new LinkedList<Pair>();
        Queue<Pair> qJ = new LinkedList<Pair>();

        // 입력을 받아 공간을 세팅
        for(int i = 0; i < r; i++) {
            String[] str = br.readLine().split("");
            for(int j = 0; j < c; j++) {
                // maze : 미로 세팅
                maze[i][j] = str[j].charAt(0);

                // fire : 불이 시작되는 곳 세팅
                if(maze[i][j] == 'F') {
                    qF.offer(new Pair(i, j));
                    fire[i][j] = 0;
                }

                // jihoon : 지훈이의 시작 위치 세팅
                if(maze[i][j] == 'J') {
                    // 지훈이의 시작 위치가 가장자리인 경우 바로 탈출 (1분)
                    if(isEdge(i, j)) {
                        System.out.println(1);
                        return;
                    }

                    qJ.offer(new Pair(i, j));
                    jihoon[i][j] = 0;
                }
            }
        }
        br.close();

        // 미로찾기 시작
        bw.write(findEscape(qF, qJ));
        bw.flush();
        bw.close();

    }

    public static boolean isNotRange(int x, int y) {
        if(x < 0 || x >= r || y < 0 || y >= c) return true;
        else return false;
    }

    public static boolean isEdge(int x, int y) {
        if(x == 0 || x == r-1 || y == 0 || y == c-1) return true;
        else return false;
    }

    public static String findEscape(Queue<Pair> qF, Queue<Pair> qJ) {

        // 불의 번짐과 지훈의 이동을 위한 델타값 세팅 (하, 좌, 상, 우)
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        // 미로찾기 시작
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {

                // 불의 번짐
                while(!qF.isEmpty()) {
                    Pair pollCellF = qF.poll();      // 탐색할 칸 poll

                    // 인접한 칸 탐색
                    for(int k = 0; k < 4; k++) {
                        int nx = pollCellF.x + dx[k];
                        int ny = pollCellF.y + dy[k];

                        // 인접한 칸이 미로 범위를 벗어나거나, 벽이거나, 이미 불이 나있으면 skip
                        if(isNotRange(nx, ny) || maze[nx][ny] == '#' || fire[nx][ny] != -1) continue;

                        // 불이 번짐
                        qF.offer(new Pair(nx, ny));
                        fire[nx][ny] = fire[pollCellF.x][pollCellF.y] + 1;
                    }
                }

                // 지훈 이동
                while(!qJ.isEmpty()) {
                    Pair pollCellJ = qJ.poll();     // 탐색할 칸 poll

                    // 지훈이가 이동 시 도착 시간
                    int time = jihoon[pollCellJ.x][pollCellJ.y] + 1;

                    // 지훈이 이동 중 가장자리에 도달하면 탈출
                    if(isEdge(pollCellJ.x, pollCellJ.y)) return String.valueOf(time);

                    // 인접한 칸 탐색
                    for(int k = 0; k < 4; k++) {
                        int nx = pollCellJ.x + dx[k];
                        int ny = pollCellJ.y + dy[k];

                        // 인접한 칸이 미로 범위를 벗어나거나, 벽이거나,
                        // 지훈이 도착 시간 전에 이미 불이 나있거나, 지훈이가 이미 지나온 곳이면 skip
                        if(isNotRange(nx, ny) || maze[nx][ny] == '#'
                                || fire[nx][ny] <= time || jihoon[nx][ny] != -1) continue;
                        
                        // 지훈 이동
                        qJ.offer(new Pair(nx, ny));
                        jihoon[nx][ny] = jihoon[pollCellJ.x][pollCellJ.y] + 1;
                    }
                }
            }
        }
        // 이동을 끝마쳤는데도 탈출을 하지 못했으므로
        return "IMPOSSIBLE";
    }
}
