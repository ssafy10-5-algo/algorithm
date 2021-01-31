import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {

        // 1. 포도주 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        // 2. 최대 포도주 양 찾기
        if (N == 1) {                                                                   // 포도주가 2잔 이하의 경우는 모든 포도주의 합이 최대
            System.out.println(nums[0]);
        } else if (N == 2) {
            System.out.println(nums[0] + nums[1]);
        } else {                                                                        // 포도주가 3잔 이상인 경우 case 고려

            /*
                포도주 먹을 수 있는 경우
                1. O O X
                2. O X O
                3. X O O
            */

            int[] dp = new int[N];

            // dp[2]까지는 예외적이므로 따로 처리
            dp[0] = nums[0];                                                            // 1잔 최대는 첫 잔
            dp[1] = nums[0] + nums[1];                                                  // 2잔 최대는 2잔 합
            dp[2] = Math.max(nums[0] + nums[2], nums[1] + nums[2]);                     // 포도주 먹을 수 있는 모든 경우 check
            dp[2] = Math.max(dp[1], dp[2]);

            // 이 공식은 직접 쓰면서 규칙성을 찾았다 ㅠㅠ → README.md에 남기겠다.
            for (int i = 3; i < N; i++) {
                dp[i] = Math.max(dp[i-3] + nums[i-1] + nums[i], dp[i-2] + nums[i]);
                dp[i] = Math.max(dp[i-1], dp[i]);                                       // 이거 때문에 애먹었다 ㅠㅠ
            }

            System.out.println(dp[N-1]);
        }
    }
}