import java.io.*;
import java.util.*;

public class BOJ5557 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] num = new int[N-1];
        for (int i = 0; i < N - 1; i++) num[i] = Integer.parseInt(st.nextToken());
        int result = Integer.parseInt(st.nextToken());

        long[][] dp = new long[N-1][21];
        dp[0][num[0]] = 1;                      // num[0]이 시작점
        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i-1][j] != 0) {
                    int plus = j + num[i];      // j 값 + num[i] → 범위가 0이상 20이하면 dp[i][plus]값 증가
                    int minus = j - num[i];     // j 값 - num[i] → 범위가 0이상 20이하면 dp[i][minus]값 증가
                    if (plus >= 0 && plus <= 20) dp[i][plus] += dp[i-1][j] ;
                    if (minus >= 0 && minus <= 20) dp[i][minus] += dp[i-1][j] ;
                }
            }
        }
        System.out.println(dp[N-2][result]);    // 끝까지 다 계산했을 때 result 값인 경우의 수 출력
    }
}