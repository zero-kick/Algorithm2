import java.io.*;

public class Recursion {
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int i = 1;

        // 재귀를 사용하여 1부터 N까지 출력하는 함수
        oneToNPrint(i);

        // 재귀를 사용하여 1부터 N까지의 합을 출력하는 함수
        int sum = oneToNSum(i);
        System.out.println(sum);
    }

    public static void oneToNPrint(int i) {
        System.out.println(i);

        if(i < n) {
            oneToNPrint(i+1);
        }
    }

    public static int oneToNSum(int i) {
        if(i >= n) {
            return i;
        } else {
            return i + oneToNSum(i+1);
        }
    }
}
