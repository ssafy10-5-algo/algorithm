import java.io.*;
import java.util.*;

public class BOJ1976 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 도시의 수
        int M = Integer.parseInt(br.readLine());    // 여행을 계획한 도시의 수
        List[] graph = new List[N+1];               // 연결된 도시
        int[] cities = new int[M];                // 계획한 도시
        boolean[] visited = new boolean[N+1];       // bfs 도시 방문체크
        Queue<Integer> q = new ArrayDeque<>();      // bfs 큐
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<Integer>();
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                int check = Integer.parseInt(st.nextToken());
                if (check == 1) graph[i].add(j);
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) cities[i] = Integer.parseInt(st.nextToken());

        // 방문할 도시의 첫번째 도시를 root로 해서 bfs 돌린다.
        q.add(cities[0]);
        visited[cities[0]] = true;
        while(!q.isEmpty()) {
            int now = q.poll();

            // 현재 도시에서 방문하지 않은 연결된 도시들을 방문한다.
            for (int i = 0, size = graph[now].size(); i < size; i++) {
                int next = (int) graph[now].get(i);
                if (!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                }
            }
        }

        // 방문 예정인 도시들을 방문했는지 여부를 체크한다.
        for (int city : cities) {
            if (!visited[city]) {
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
    }
}
