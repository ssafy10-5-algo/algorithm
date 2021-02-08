import java.util.*;
public class BOJ17213 {
    static int N, M, ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();       // 과일 종류 수
        M = sc.nextInt();       // 훔치려는 과일 개수
        sc.close();

        combination(0, M - N, 0);
        System.out.print(ans);
    }

    static void combination(int sum, int n, int count) {
        if (sum > n || N < count) return;   // 훔치려 했던 거보다 많이 훔치거나 없는 과일을 선택한 경우 break
        if (sum == n) {                     // 훔치려 했던만큼 훔친 경우
            ans++;
            return;
        }
        for (int i = 0; i <= n; i++) {
            combination(sum + i, n, count + 1); // 훔치는 중
        }
    }

}
