import java.io.*;
import java.util.*;

public class BOJ2667 {
    public static int n;
    public static int[][] house;
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

        n = Integer.parseInt(br.readLine());        // 지도 크기 n x n

        // 지도 그리기
        house = new int[n][n];
        visit = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            String[] strArr = br.readLine().split("");
            for(int j = 0; j < n; j++) {
                house[i][j] = Integer.parseInt(strArr[j]);
            }
        }
        br.close();

        // 집 찾기
        List<Integer> list = findHouse();

        // 단지 수
        bw.write(String.valueOf(list.remove(list.size()-1)) + "\n");

        // 단지 내 집의 수 오름차순 정렬
        list.sort(Comparator.naturalOrder());
        for(int i = 0; i < list.size(); i++) {
            bw.write(String.valueOf(list.get(i)) + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static List<Integer> findHouse() {
        List<Integer> returnList = new ArrayList<Integer>();
        
        int area = 0;   // 단지수

        q = new LinkedList<Pair>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {

                // 방문하지 않은 집이면 큐에 등록하고 방문처리
                if(house[i][j] == 1 && !visit[i][j]) {
                    q.offer(new Pair(i, j));
                    visit[i][j] = true;
                    area++;     // 단지수 + 1
                }

                int cnt = 0;    // 단지 내 집의 수

                // 인접 칸 탐색
                while(!q.isEmpty()) {
                    cnt++;      // 단지 내 집의 수 + 1
                    Pair pollCell = q.poll();

                    for(int k = 0; k < 4; k++) {
                        int nx = pollCell.x + dx[k];
                        int ny = pollCell.y + dy[k];

                        // 인접 칸이 지도 범위를 벗어나거나, 집이 없거나, 이미 방문한 집이면 skip
                        if(isNotRange(nx, ny) || house[nx][ny] == 0 || visit[nx][ny]) continue;

                        // 큐에 등록하고 방문처리
                        q.offer(new Pair(nx, ny));
                        visit[nx][ny] = true;
                    }
                }

                if(cnt > 0) returnList.add(cnt);
            }
        }

        returnList.add(area);

        return returnList;
    }

    public static boolean isNotRange(int x, int y) {
        return (x < 0 || x >= n || y < 0 || y >= n) ? true : false;
    }
}
