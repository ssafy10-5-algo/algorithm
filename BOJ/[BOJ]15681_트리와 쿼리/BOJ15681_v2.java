import java.io.*;
import java.util.*;

public class BOJ15681_v2 {
    static int N, R, Q;
    static List<Integer>[] graph;
    static boolean[] root;
    static int[] subTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parse(st.nextToken());  // 트리의 정점 수
        R = parse(st.nextToken());  // 루트노드
        Q = parse(st.nextToken());  // 쿼리 수

        graph = new List[N+1];      // 그래프
        root = new boolean[N+1];        // 각 노드의 루트 노드
        subTree = new int[N+1];     // 각 노드의 서브쿼리 수
        root[R] = true;

        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();  // 그래프 초기화
        for (int i = 1; i < N; i++) {                               // N-1 개 간선 그래프(트리) 생성
            st = new StringTokenizer(br.readLine());
            int a = parse(st.nextToken());
            int b = parse(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(R); // 트리 서브 쿼리 개수 구하기

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) sb.append(subTree[parse(br.readLine())]).append("\n");
        System.out.print(sb);
    }

    static int dfs(int now) {
        int sum = 1;
        root[now] = true;
        for (int next : graph[now]) {   // now와 연결된 node 가져오기
            if (root[next]) continue;   // 부모가 있는 node는 pass
            sum += dfs(next);           // dfs 탐색으로 서브 쿼리 개수 체크
        }
        return subTree[now] = sum;
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
