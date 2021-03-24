import java.io.*;
import java.util.*;

public class BOJ1753 {
    static int V, E, K;
    static ArrayList<int[]>[] graph;
    static boolean[] visited;
    static int[] minDist;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());   // 정점의 개수
        E = Integer.parseInt(st.nextToken());   // 간선의 개수
        K = Integer.parseInt(br.readLine());    // 시작 정점 번호

        graph = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new int[]{v, w});
        }

        visited = new boolean[V+1];
        minDist = new int[V+1];
        Arrays.fill(minDist, INF);
        minDist[K] = 0;

        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        q.add(new int[]{K, 0});
        while(!q.isEmpty()) {
            int[] now = q.poll();
            if (visited[now[0]]) continue;
            visited[now[0]] = true;
            for (int i = 0, size = graph[now[0]].size(); i < size; i++) {
                int[] next = graph[now[0]].get(i);
                if (minDist[next[0]] > minDist[now[0]] + next[1]) {
                    minDist[next[0]] = minDist[now[0]] + next[1];
                    q.add(new int[]{next[0], minDist[next[0]]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V ; i++) sb.append(minDist[i] == INF ? "INF" : minDist[i]).append("\n");
        System.out.println(sb);
    }
}