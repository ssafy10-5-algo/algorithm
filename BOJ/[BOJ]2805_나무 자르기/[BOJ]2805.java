import java.io.*;
import java.util.*;

public class D2805 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parse(st.nextToken());      // 나무의 수
        int M = parse(st.nextToken());      // 상근이가 가져가려는 나무 길이

        int[] tree = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = parse(st.nextToken());
        }

        int left = 0;
        int right = Arrays.stream(tree).max().getAsInt();   // 가진 나무 중 가장 큰 나무의 길이 체크
        while (left <= right) {                             // 이분 탐색 시작
            int mid = (left + right) / 2;

            long sum = 0;                                   // 잘려진 나무의 합 → int overflow
            for (int i = 0; i < N; i++) {
                if(tree[i] > mid) {                         // 절단기보다 큰 나무만 절단
                    sum += (tree[i] - mid);                 // 잘린 나무들의 합
                }
            }

            if (sum >= M) {                                 // 가려가려는 나무보다 많이 잘렸으면 절단기 최소 높이 up
                left = mid + 1;
            } else {                                        // 가려가려는 나무보다 조금 잘렸으면 절단기 최소 높이 down
                right = mid-1;
            }
        }

        System.out.println(right);
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
