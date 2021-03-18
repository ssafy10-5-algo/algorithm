import java.io.*;
import java.util.*;

public class BOJ17404 {
    static final int INF = 2000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] paint = new int[N][3];

        // 입력
       for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            // 0번 R, 1번 G, 2번 B
            paint[i][0] = Integer.parseInt(st.nextToken());
            paint[i][1] = Integer.parseInt(st.nextToken());
            paint[i][2] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int[][] dp = new int[N][3];
        for (int i = 0; i < 3; i++) Arrays.fill(dp[i], INF);
        int ans = Integer.MAX_VALUE;
        for (int color = 0; color < 3; color++) {
            // 첫번째 집이 무슨 색인지 체크
            for (int check = 0; check < 3; check++) {
                if (check == color) dp[0][check] = paint[0][color];
                else dp[0][check] = INF;
            }

            // i번째 집에 RGB를 칠하는 경우
            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + paint[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + paint[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + paint[i][2];
            }

            // 값 도출을 위한 최소값 도출
            for (int check = 0; check < 3; check++) {
                if (color == check) continue;
                ans = Math.min(ans, dp[N-1][check]);
            }
        }
        System.out.print(ans);
    }
}
