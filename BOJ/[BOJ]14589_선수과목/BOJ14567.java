import java.io.*;
import java.util.*;

public class BOJ14567 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());   // 과목의 수
        int M = Integer.parseInt(st.nextToken());   // 선수 조건의 수

        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);    // 낮은 수가 먼저 선행이므로 pq 사용
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            q.add(new int[]{a, b});
        }

        int[] cnt = new int[N+1]; 
        Arrays.fill(cnt, 1);
        while (!q.isEmpty()) {
            int[] now = q.poll();
            cnt[now[1]] = Math.max(cnt[now[1]], cnt[now[0]] + 1);   // 선행되는 과목 수 갱신   
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append(cnt[i]).append(" "); // 정답 출력
        System.out.println(sb);
    }
}
