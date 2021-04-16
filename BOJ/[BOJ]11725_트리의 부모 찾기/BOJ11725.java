import java.io.*;
import java.util.*;

public class BOJ11725 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] root = new int[N+1];
        List<Integer>[] graph = new List[N+1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        // 그래프 생성
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        Queue<Integer> q = new ArrayDeque<>();  // bfs 탐색
        boolean[] visited = new boolean[N+1];   // 방문 체크
        q.add(1);                               // root 노드 1
        visited[1] = true;
        while(!q.isEmpty()) {
            int now = q.poll();
            for (int i = 0; i < graph[now].size(); i++) {
                int val = graph[now].get(i);    // 현재 루트와 연결된 노드를 가져옴
                if (visited[val]) continue;     // 이미 부모가 존재하는 노드 pass
                root[val] = now;                // 자식노드의 루트를 현재 노드로 설정
                visited[val] = true;            // 자식노드의 부모가 정해졌으므로 방문 체크
                q.add(val);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) sb.append(root[i]).append("\n");
        System.out.print(sb);
    }
}
