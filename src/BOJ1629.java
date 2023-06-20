import java.io.*;
import java.util.StringTokenizer;

public class BOJ1629 {
    public static long a, b, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        bw.write(String.valueOf(pow(a, b)));
        bw.flush();
        bw.close();
        br.close();
    }

    public static long pow(long x, long e) {
        // 지수가 1일 경우 x^1 이므로 x 리턴
        if(e == 1) {
            return x % c;
        }

        // 지수의 절반 만큼을 제곱으로하여 거듭제곱 값을 구한다.
        long temp = pow(x, e/2);

        // 지수가 홀수인 경우에는 x를 한 번 더 곱해준다.
        if(e % 2 == 1) {
            return (temp * temp % c) * x % c;
        }

        return temp * temp % c;
    }
}
