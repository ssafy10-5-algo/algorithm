import java.io.*;
import java.util.*;

public class BOJ7579 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] memory = new int[N + 1];
        int[] cost = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int costSum = 0;
        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());
            costSum += cost[i];
        }

        // dp[i][j] : i번째 앱까지 j만큼의 비활성화 비용
        int[][] dp = new int[N + 1][costSum + 1];
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            // i번째 앱까지 j만큼의 비용으로 비활성화
            for (int j = 0; j <= costSum; j++) {

                // 비용 j가 i번째 앱의 비활성화 비용보다 작다면 i번째 앱을 비활성화하지 않았다는 의미
                if (j < cost[i]) dp[i][j] = dp[i-1][j];

                // 최저의 비용으로 최대의 메모리를 확보
                else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]] + memory[i]);

                // 현재 확보한 메모리가 M 이상이고 최저의 cost인 경우 정답답
               if (dp[i][j] >= M && ans > j) ans = j;
            }
        }
        System.out.println(ans);
    }
}
