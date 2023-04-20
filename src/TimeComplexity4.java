public class TimeComplexity4 {
    public static void main(String[] args) {

        /* 문제 */
        // n 이하의 수 중에서 가장 큰 2의 거듭제곱수를 반환하는 함수 func(int n)를 작성하라.
        // n은 10억 이하의 자연수이다.

        // 시간복잡도 : O(1)
        System.out.println(func(5));
        System.out.println(func(97615282));
        System.out.println(func(1024));

    }

    public static int func(int n) {
        int answer = 0;
        double pow = Math.floor(baseLog(n, 2));

        answer = (int) Math.pow(2, pow);

        return answer;
    }

    public static double baseLog(double n, double base) {
        return Math.log10(n) / Math.log10(base);
    }
}
