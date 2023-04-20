public class TimeComplexity1 {
    public static void main (String[] args) {

        /* 문제 */
        // n 이하의 자연수 중에서 3의 배수이거나 5의 배수인 수를 모두 합한 값을 반환하는 함수 func(int n)을 작성하라.
        // n은 10만 이하의 자연수이다.

        // 시간복잡도 : O(N)
        System.out.println(func (16));
        System.out.println(func (34567));
        System.out.println(func (27639));

    }

    public static long func(int n) {

        long answer = 0;

        for (int i = 1; i <= n; i++) {
            if (i%3 == 0 || i%5 == 0) {
                answer += i;
            }
        }

        return answer;
    }
}
