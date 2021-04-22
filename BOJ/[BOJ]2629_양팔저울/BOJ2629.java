import java.io.*;
import java.util.*;

public class BOJ2629 {
    static int N, M;
    static int[] weights;
    static StringBuilder sb = new StringBuilder();
    static boolean[][] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());    // 추 개수
        weights = new int[N];                   // 추들의 무게
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) weights[i] = Integer.parseInt(st.nextToken());

        memo = new boolean[N+1][55001];
        dfs(0, 0);

        M = Integer.parseInt(br.readLine());    // 구슬 개수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(memo[N][target] ? "Y" : "N").append(" "); // 구슬 무게 측정가능하면 Y, 아니면 N
        }
        System.out.println(sb);
    }

    static void dfs(int sum, int index) {
        if (memo[index][sum]) return;   // 기존에 이미 만들었던 거면 return
        memo[index][sum] = true;        // index 개수만큼의 추로 sum을 나타낼 수 있으면 true
        if (N == index) return;         // 추를 모두 다 사용했으면 return
        dfs(sum + weights[index], index+1);         // 추를 한 곳에 올린 경우
        dfs(Math.abs(sum - weights[index]), index+1);    // 추를 반대편에 올린 경우
        dfs(sum, index+1);                               // 추를 사용하지 않은 경우
    }
}
