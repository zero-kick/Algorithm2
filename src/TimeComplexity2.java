import java.util.Arrays;

public class TimeComplexity2 {
    public static void main (String[] args) {

        // 시간복잡도 : O(N)
        System.out.println(func(new int[]{1, 52, 48}, 3));
        System.out.println(func(new int[]{50, 42}, 3));
        System.out.println(func(new int[]{4, 13, 63, 87}, 3));

    }

    public static int func (int[] arr, int n) {
        int answer = 0;

        for (int i = 0; i < arr.length; i++) {
            int sub = 100 - arr[i];
            Arrays.sort(arr);

            if(Arrays.binarySearch(arr, sub) < 0) {
                answer = 1;
                break;
            }
        }

        return answer;
    }
}
