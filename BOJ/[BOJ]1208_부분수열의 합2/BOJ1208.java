import java.io.*;
import java.util.*;

public class BOJ1208 {
    static int N;
    static long S, ans;
    static int[] arr;
    static List<Long> list1 = new ArrayList<>();
    static List<Long> list2 = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       // 정수의 개수
        S = Integer.parseInt(st.nextToken());       // 정수
        arr = new int[N];                           // 수열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int half = N/2;
        subSet(0, half, 0, list1);       // 배열을 반 잘라서 subSet 구하기
        subSet(half, N, 0, list2);             // 배열을 반 잘라서 subSet2 구하기

        Collections.sort(list1);                    // 투 포인터 사용을 위한 정렬
        Collections.sort(list2);                    // 투 포인터 사용을 위한 정렬2

        int left = 0;                               // list1의 왼쪽부터 탐색
        int right = list2.size()-1;                 // list2의 오른쪽부터 탐색
        while (left < list1.size() && right >= 0) { // 이분탐색
            long leftVal = list1.get(left);
            long rightVal = list2.get(right);
            long sum = leftVal + rightVal;

            if (sum == S) {                 // list1의 부분집합과 list2의 부분집합의 합이 조건과 일치
                long countL = 0;
                long countR = 0;

                // 동일한 값 더 있나 탐색
                while (left < list1.size() && leftVal == list1.get(left)) { countL++; left++; }
                while (right >= 0 && rightVal == list2.get(right)) { countR++; right--; }
                ans += (countL * countR);   // S를 이루는 left와 right의 총 개수 더하기
            }
            else if (sum < S) left++;
            else right--;
        }

        if (S == 0) ans -= 1;         // S가 0이면 공집합도 존재하므로 -1
        System.out.println(ans);      // 답 출력
    }

    static void subSet(int index, int end, long sum, List<Long> list) {
        if (index == end) {     // 집합의 끝에 도착
            list.add(sum);      // 부분집합의 합을 list에 저장
            return;
        }
        subSet(index + 1, end, sum + arr[index], list); // 현재 값을 선택한 부분집합
        subSet( index + 1, end, sum, list);                  // 현재 값을 선택 안 한 부분집합
    }
}