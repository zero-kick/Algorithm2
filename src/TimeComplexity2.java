import java.util.Arrays;

public class TimeComplexity2 {
    public static void main (String[] args) {

        /* 문제 */
        // 주어진 길이 n의 int 배열 arr에서 합이 100인 서로 다른 위치의 두 원소가 존재하면 1을,
        // 존재하지 않으면 0을 반환하는 함수 func(int arr[], int n)을 작성하라.
        // arr의 각 수는 0 이상 100 이하이고 n은 1000 이하이다.

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
