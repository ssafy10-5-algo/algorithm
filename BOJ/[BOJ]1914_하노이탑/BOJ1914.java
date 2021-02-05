import java.math.BigInteger;
import java.util.*;

public class BOJ1914 {
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        BigInteger result = new BigInteger("1");        // long형으로 안 됨
        for (int i = 1; i <= N; i++) {
            result = result.multiply(new BigInteger("2"));
        }
        result = result.subtract(new BigInteger("1"));
        sb.append(result).append("\n");                     // 계산 횟수 2^N - 1

        if (N <= 20) hanoi(N, 1,2,3);
        System.out.println(sb);
    }


    // 자세한 설명은 README 에서!
    static void hanoi (int n, int first, int second, int third){
        if (n == 1) {
            sb.append(first).append(" ").append(third).append("\n");
            return;
        }

        hanoi(n-1, first, third, second);
        sb.append(first).append(" ").append(third).append("\n");
        hanoi(n-1, second, first, third);
    }
}
