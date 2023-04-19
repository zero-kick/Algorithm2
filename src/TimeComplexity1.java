public class TimeComplexity1 {
    public static void main (String[] args) {

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
