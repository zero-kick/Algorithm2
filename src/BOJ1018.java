import java.io.*;
import java.util.StringTokenizer;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : BOJ1018
 * author         : young
 * date           : 2024-09-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-16        young       최초 생성
 */
public class BOJ1018 {

    public static int row, col;
    public static String[][] board;

    public static void main(String[] args) throws IOException {
        // 0. 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        board = new String[row][col];
        for(int i = 0; i < row; i++) {
            String str = br.readLine();
            for(int j = 0; j < col; j++) {
                board[i][j] = Character.toString(str.charAt(j));
            }
        }

        // 1. 체스판 자르기
        int min = Integer.MAX_VALUE;
        for(int i = 0; i <= row - 8; i++) {     // row-8이 start row의 최대이기 때문.
            for(int j = 0; j <= col - 8; j++) {     // col-8이 start col의 최대이기 때문.
                // 2. 현재 체스판 완성을 위한 최소 비용 구하기
                int curMin = getMinCount(i, j, board);

                // 3. 모든 경우의 체스판에 대한 최소 비용 구하기
                if(min > curMin) min = curMin;
            }
        }

        br.close();
        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
    }

    public static int getMinCount(int sttRow, int sttCol, String[][] board) {
        String[][] whiteBoard = {{"W", "B", "W", "B", "W", "B", "W", "B"}   // 맨 왼쪽 위가 하얀색인 체스판을 기준으로 카운트
                , {"B", "W", "B", "W", "B", "W", "B", "W"}};
        int cnt = 0;
        for(int i = 0; i < 8; i++) {
            int row = i + sttRow;
            for(int j = 0; j < 8; j++) {
                int col = j + sttCol;
                if(!board[row][col].equals(whiteBoard[i%2][j])) cnt++;
            }
        }

        return Math.min(cnt, 64 - cnt);     // 맨 왼쪽 위가 검정색이 체스판의 카운트는 전체 칸 수 (64) - 하얀색 체스판의 카운트
    }
}
