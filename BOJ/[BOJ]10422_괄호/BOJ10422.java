import java.io.*;
import java.util.*;

public class BOJ10422 {
    static int N, T;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        long[] dp = new long[2501];     // 최대 괄호 갯수 2500쌍
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= 2500; i++) // 카탈란 수 뭐시깽이
            for (int j = 0; j < i; j++) {
                dp[i] += (dp[j] * dp[i - j - 1]);
                dp[i] %= 1000000007;
            }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            sb.append(N % 2 == 0 ? dp[N/2] : 0).append("\n");   // 홀수쌍이면 잘못된 괄호이므로 무조건 0
        }
        System.out.print(sb);
    }
}
