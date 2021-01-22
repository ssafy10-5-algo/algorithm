import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parse(br.readLine());

        // 입력
        int[] time = new int[N+5];
        int[] cost = new int[N+5];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = parse(st.nextToken());
            int c = parse(st.nextToken());
            time[i] = t;
            cost[i] = c;
        }


//        오늘까지 벌어 들일 수 있는 최대 돈 = 과거의 값들 중 max 값
//        오늘부터 소요 시간 날짜 뒤에 받을 수 있는 최대 돈 = 당일까지 벌어들일 수 있는 최대의 돈 비용 + 오늘 일하면 벌 수 있는 돈
//        max 값 갱신
        int max = 0;
        int[] dp = new int[N+5];
        for (int i = 0; i <= N; i++) {
            dp[i] = Math.max(dp[i], max);
            dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + cost[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(dp[N]);
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
