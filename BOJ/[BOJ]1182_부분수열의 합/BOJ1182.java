import java.io.*;
import java.util.*;

public class BOJ1182 {
    static int N, S;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       // 정수의 개수
        S = Integer.parseInt(st.nextToken());       // 정수
        arr = new int[N];                           // 수열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int count = 0;
        for (long i = 1, size = 1L << N; i < size; i++) {           // 비트연산으로 부분수열 조합 구하기
            String s = Long.toBinaryString(i);
            int sum = 0;
            for (int j = 0, len = s.length(); j < len; j++) {
                if (s.charAt(j) == '1') sum += arr[N - len + j];    // 선택된 경우 배열의 값 더하기
            }
            if (sum == S) count++;                                  // 값이 일치하는 경우 count 증가
        }
        System.out.println(count);  // 정답 출력
    }
}
