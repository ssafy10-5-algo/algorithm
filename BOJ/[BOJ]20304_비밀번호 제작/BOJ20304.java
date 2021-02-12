import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20304 {
    static final int INF = -1;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> q = new ArrayDeque<>();
        int[] arr = new int[N+1];       // xor 연산의 결과를 bfs 형태로 저장할 배열
        Arrays.fill(arr, INF);          // visit 여부를 INF 로 체크하고자 배열 초기화
        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(st.nextToken());
            q.add(n);
            arr[n] = 0;                 // 시도한 비밀번호에 대해서 0으로 초기화
        }

        int ans = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i = 0; i<20 ; i++) {
                int next = now ^ (1<<i);
                if(next > N) break;             // 연산 결과가 N보다 크면 break
                if(arr[next] != INF) continue;  // 이미 방문했었으면 continue
                arr[next] = arr[now] + 1;       // now-next 비트 차이는 1
                q.add(next);
                ans = Math.max(ans, arr[next]); // arr 내부의 max 값을 위한 갱신
            }
        }
        System.out.println(ans);
    }
}
