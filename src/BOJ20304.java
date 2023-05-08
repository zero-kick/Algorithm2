import java.io.*;
import java.util.StringTokenizer;

public class BOJ20304 {
    public static int n, m;
    public static int[] p;
    public static int[] binary;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        p = new int[m];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

       // 1,000,000을 이진법으로 표현하기 위해서는 2^0 ~ 2^19 까지, 총 20자리가 필요
        binary = new int[20];
    }
}
