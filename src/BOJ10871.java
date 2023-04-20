import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ10871 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        String[] strArr = br.readLine().split(" ");

        String answer = "";

        for(int i = 0; i < n; i++) {
            if(x > Integer.parseInt(strArr[i])) {
                answer += strArr[i] + " ";
            }
        }

        System.out.println(answer.trim());

    }
}
