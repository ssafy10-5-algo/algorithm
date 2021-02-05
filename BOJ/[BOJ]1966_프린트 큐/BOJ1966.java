import java.util.*;
import java.io.*;

public class BOJ1966 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = parse(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TC; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = parse(st.nextToken());  // 문서의 개수
            int M = parse(st.nextToken());  // 몇 번째에 놓여있는지 정수 M

            Queue<int[]> q = new ArrayDeque<>();       // int[]의 [0]은 인덱스, [1]은 문서 중요도
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());    // 내림차순
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = parse(st.nextToken());
                q.add(new int[]{j, val});
                pq.add(val);
            }

            int count = 0;
ex:            while (true) {
                if (pq.peek() == q.peek()[1]) {     // 문서 중요도의 max 값과 큐의 맨 앞 값이 일치하면 인쇄
                    count++;
                    pq.poll();
                    int[] now = q.poll();
                    if (now[0] == M) break ex;
                } else {                            // 일치하지 않으면 뒤로 보내주기
                    q.add(q.poll());
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
