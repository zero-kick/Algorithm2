import java.io.*;
import java.util.*;

public class BOJ2468 {
    public static int n, h;
    public static int[][] city;
    public static boolean[][] visit;

    public static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static Queue<Pair> q;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        h = 0;      // 구역 내 최고층 높이

        city = new int[n][n];

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int f = Integer.parseInt(st.nextToken());
                city[i][j] = f;
                if(h < f) {
                    h = f;
                }
            }
        }
        br.close();

        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i <= h; i++) {
            list.add(findSafeZone(i));
        }

        list.sort(Comparator.reverseOrder());

        bw.write(String.valueOf(list.get(0)));
        bw.flush();
        bw.close();
    }

    public static int findSafeZone(int maxH) {
        q = new LinkedList<Pair>();
        visit = new boolean[n][n];
        int cnt = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(visit[i][j] || city[i][j] <= maxH) continue;

                q.offer(new Pair(i, j));
                visit[i][j] = true;

                cnt++;

                while(!q.isEmpty()) {
                    Pair pollCell = q.poll();

                    for(int k = 0; k < 4; k++) {
                        int nx = pollCell.x + dx[k];
                        int ny = pollCell.y + dy[k];

                        if(isNotRange(nx, ny) || visit[nx][ny] || city[nx][ny] <= maxH) continue;

                        q.offer(new Pair(nx, ny));
                        visit[nx][ny] = true;
                    }
                }
            }
        }
        return cnt;
    }

    public static boolean isNotRange(int x, int y) {
        return (x < 0 || x >= n || y < 0 || y >= n) ? true : false;
    }
}
