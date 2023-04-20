public class TimeComplexity3 {
    public static void main(String[] args) {

        /* 문제 */
        // n이 제곱수이면 1을 반환하고 제곱수가 아니면 0을 반환하는 함수 func(int n)을 작성하라.
        // n은 10억 이하의 자연수이다.

        // 시간복잡도 : O(1)
        System.out.println(func(9));
        System.out.println(func(693953651));
        System.out.println(func(756580036));

    }

    public static int func(int n) {

        int answer = 0;

        answer = n % Math.sqrt(n) == 0 ? 1 : 0;

        return answer;
    }
}
