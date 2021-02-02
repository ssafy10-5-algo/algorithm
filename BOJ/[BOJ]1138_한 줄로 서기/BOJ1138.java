import java.io.*;
import java.util.*;

public class BOJ1138 {
    static final int INF = 100;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 인원수
        int[] arr = new int[N];                     // 주어진 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] line = new int[N];    // 실제 어디에 누가 서있나 저장하는 배열
        Arrays.fill(line, INF);     // 앞에 자신보다 큰 사람이 몇명있나 비교를 위해 INF로 초기화

        for (int i = 0; i < N; i++) {
            int cnt = 0;            // 나보다 큰 사람 count 변수
            for (int j = 0; j < N; j++) {
                // 자신보다 큰 사람의 인원수가 일치하고 빈 공간이면 저장
                if (arr[i] == cnt && line[j] == INF){
                    line[j] = i+1;
                    break;
                }
                // 자신보다 큰 사람이면 cnt 증가
                if (i < line[j]) cnt++;
            }
        }

        // 출력
        for (int l : line) {
            System.out.print(l + " ");
        }
    }
}
