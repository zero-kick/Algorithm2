import java.io.*;

public class BOJ11729 {
    public static int n, cnt;

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        bw.write((int) Math.pow(2, n) - 1 + "\n");
        move(n, 1, 2,3);

        bw.flush();
        bw.close();
    }

    // start : 출발지 장대 (A)
    // via : 임시 장대 (B)
    // end : 목적지 장대 (C)
    public static void move(int n, int start, int via, int end) throws IOException {

        if(n == 1) {
            bw.write(start + " " + end + "\n");
            return;
        }

        // step1. n-1개의 원판을 (A)에서 (B)로 이동
        move(n-1, start, end, via); // start 장대의 n-1개의 원판을 via 장대로 옮긴다.

        // step2. 1개의 원판을 (A)에서 (C)로 이동
        bw.write(start + " " + end + "\n");

        // step3. n-1개의 원판을 (B)에서 (C)로 이동
        move(n-1, via, start, end); // via 장대의 n-1개의 원판을 end 장대로 옮긴다.

    }
}
