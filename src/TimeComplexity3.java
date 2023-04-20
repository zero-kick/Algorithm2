public class TimeComplexity3 {
    public static void main(String[] args) {

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
