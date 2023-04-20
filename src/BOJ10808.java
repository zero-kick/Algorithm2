import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BOJ10808 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String answer = "";
        int[] arr = new int[26];

        for(int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            arr[idx]++;
        }

        for(int i = 0; i < arr.length; i++) {
            answer += String.valueOf(arr[i]) + " ";
        }

        System.out.println(answer.trim());
    }
}
