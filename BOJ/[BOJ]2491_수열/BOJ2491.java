import java.io.*;
import java.util.*;

public class BOJ2491 {
    public static void main(String[] args) throws Exception {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int small = 1, big = 1;         // 작아지는 수열, 커지는 수열 count
        int smallMax = 0, bigMax = 0;   // 작아지는 수열 최대값, 커지는 수열 최대값
        for (int i = 1; i < N; i++) {
            if (arr[i - 1] < arr[i]) {  // 현재 값이 이전 배열의 값보다 큰 경우, big을 증가, small을 1로 초기화
                big++;
                smallMax = Math.max(small, smallMax);
                small = 1;
            } else if (arr[i - 1] == arr[i]) {  // 값이 같은 같으면 big, small  모두 +1 증가
                big++;
                small++;
            } else {                     // 현재 값이 이전 값보다 작은 경우, small을 증가, big을 1로 초기화
                small++;
                bigMax = Math.max(big, bigMax);
                big = 1;
            }
        }

        // 마지막 배열을 적용한 결과를 마지막으로 체크
        bigMax = Math.max(big, bigMax);
        smallMax = Math.max(small, smallMax);

        System.out.println(Math.max(smallMax, bigMax)); // 결과출력
    }
}
