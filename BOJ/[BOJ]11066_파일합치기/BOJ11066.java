import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11066 {
    static final int INF = 99999999;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 0; test_case < TC; test_case++) {
            int N = Integer.parseInt(br.readLine()) + 1;
            int[] arr = new int[N];         // 입력 받을 배열
            int[] sum = new int[N];         // 구간합
            int[][] dp = new int[N+1][N+1]; // dp 배열
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + arr[i];
            }

            // x부터 y까지 작은 값을 구함
            // 경로지 k를 두고 x~k, k~y의 합이 가장 작은 것을 선택
            for (int y = 2; y < N; y++) {
                for (int x = y - 1; x > 0; x--) {
                    dp[x][y] = INF;
                    for (int k = x; k < y; k++) {
                        dp[x][y] = Math.min(dp[x][y], dp[x][k] + dp[k+1][y] + sum[y] - sum[x-1]);
                    }
                }
            }
            sb.append(dp[1][N-1]).append("\n");
        }
        System.out.println(sb);
    }
}
