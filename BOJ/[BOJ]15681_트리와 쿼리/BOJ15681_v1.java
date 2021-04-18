import java.io.*;
import java.util.*;

public class BOJ15681_v1 {
    static int N, R, Q;
    static List<Integer>[] graph;
    static Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
    static Queue<int []> q = new ArrayDeque<>();
    static int[] root;
    static int[] subTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parse(st.nextToken());  // 트리의 정점 수
        R = parse(st.nextToken());  // 루트노드
        Q = parse(st.nextToken());  // 쿼리 수

        graph = new List[N+1];      // 그래프
        root = new int[N+1];        // 각 노드의 루트 노드
        subTree = new int[N+1];     // 각 노드의 서브쿼리 수
        root[R] = R;

        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();  // 그래프 초기화
        for (int i = 1; i < N; i++) {                               // N-1 개 간선 그래프(트리) 생성
            st = new StringTokenizer(br.readLine());
            int a = parse(st.nextToken());
            int b = parse(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        q.add(new int[]{R, 1});     // 현재 노드와 노드의 깊이 저장
        while(!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < graph[now[0]].size(); i++) {
                int next = graph[now[0]].get(i);
                if (root[next] != 0) continue;                  // 그래프에 연결된 노드가 이미 부모가 있으면 pass
                int[] nextNode = new int[]{next, now[1] + 1};   // 부모가 없으면 현재 now가 next의 부모
                q.add(nextNode);
                pq.add(nextNode);
                root[next] = now[0];                            // 부모 갱신
            }
        }

        Arrays.fill(subTree, 1);
        while(!pq.isEmpty()) {                          // 노드 깊이가 깊은 것부터 저장
            int[] child = pq.poll();
            int rootNode = root[child[0]];
            subTree[rootNode] += subTree[child[0]];     // 부모노드에 자식노드 서브트리 값 더하기
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) sb.append(subTree[parse(br.readLine())]).append("\n");
        System.out.print(sb);
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
