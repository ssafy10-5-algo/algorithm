import java.io.*;
import java.util.*;

public class BOJ20440 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int in = Integer.parseInt(st.nextToken());
            int out = Integer.parseInt(st.nextToken());
            list.add(new int[]{in, out});
        }

        // 입력받은 모기의 입장, 퇴장시간을 정렬을 입장이 빠른 순으로 정렬한다.
        Collections.sort(list, (o1, o2) -> o1[0] - o2[0]);

        // 퇴장이 빠른 순서대로 정렬하는 큐
        Queue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        int count = 0;
        int in = 0;
        int out = 2100000001;
        for (int i = 0; i < N; i++) {
            int[] now = list.get(i);

            // 현재 입장하는 모기보다 퇴실 시간이 앞인 모기 큐에서 제거
            while (!q.isEmpty() && q.peek()[1] <= now[0]) q.poll();
            q.add(now); // 모기 입장

            if (q.size() > count) { // 현재 큐에 존재하는 모기가 기존 시간의 모가보다 많다면 값 갱신
                in = now[0];        // 현재 모기가 입장한 시간
                out = q.peek()[1];  // q에서 제일 빠른 퇴실 시간
                count = q.size();   // 최대 모기 수 갱신
            } else if (q.size() == count) { // 모기 개수가 같은 경우
                // (1, 3) (3, 5) 처럼 이어지는 경우 (1, 5)로 이어줌
                if (out == now[0]) out = q.peek()[1];
            }
        }
        System.out.printf("%d%n%d %d", count, in, out);
    }
}
