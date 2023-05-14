import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ5430 {
    public static int tc, n;
    public static String p;
    public static ArrayDeque<String> deque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        tc = Integer.parseInt(br.readLine());

        for(int t = 0; t < tc; t++) {
            p = br.readLine();
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[],");
            deque = new ArrayDeque<String>();

            // 배열을 덱에 담는다.
            for(int i = 0; i < n; i++) {
                deque.offer(st.nextToken());
            }

            bw.write(ac() + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static String ac() {
        // 뒤집기가 되어있는 상태인지 체크하는 flag
        boolean isReverse = false;

        for(char cmd : p.toCharArray()) {
            switch(cmd) {
                // 명령어가 'R'인 경우
                case 'R' :
                    // 뒤집기 flag 반전
                    isReverse = !isReverse;
                    break;
                // 명령어가 'D'인 경우
                case 'D' :
                    // 역방향인 경우
                    if(isReverse) {
                        // 덱이 비어있는 경우 error
                        if(deque.pollLast() == null) {
                            return "error";
                        }
                        // 정방향인 경우
                    } else {
                        // 덱이 비어있는 경우 error
                        if(deque.pollFirst() == null) {
                            return "error";
                        }
                    }
                    break;
            }
        }

        // 결과 조립
        String result = "[";

        // 덱에 원소가 있을 경우에만.
        if(deque.size() > 0) {
            if(isReverse) {
                // 첫번째 원소 poll
                result += deque.pollLast();

                // 다음 원소부터는 , 추가
                while(!deque.isEmpty()) {
                    result += "," + deque.pollLast();
                }
            } else {
                // 첫번째 원소 poll
                result += deque.pollFirst();

                // 다음 원소부터는 , 추가
                while(!deque.isEmpty()) {
                    result += "," + deque.pollFirst();
                }
            }
        }

        result += "]";

        return result;
    }
}