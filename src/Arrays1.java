import java.util.Arrays;
import java.util.Vector;

public class Arrays1 {
    public static void main(String[] args) {

        /* 배열의 원소 삽입/삭제 */
        int[] arr = new int[]{10, 50, 40, 30, 70 ,20, 0, 0, 0, 0};
        int len = 6;

        insert(3, 60, arr, len);
        System.out.println("arr : " + Arrays.toString(arr));

        erase(4, arr, len);
        System.out.println("arr : " + Arrays.toString(arr));

    }

    public static void insert(int idx, int num, int arr[], int len) {
        for(int i = arr.length-1; i >= idx; i--) {
            if(i == 9) continue;
            arr[i+1] = arr[i];
        }
        arr[idx] = num;
    }

    public static void erase(int idx, int arr[], int len) {
        for(int i = idx; i < arr.length-1; i++) {
            arr[i] = arr[i + 1];
        }
    }
}
