import java.util.*;
import java.io.*;

public class BOJ1238 {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, X;         // 학생 N명, 도로 M개, 파티 마을 X
    static List<Conn>[] cities; // 도시의 연결 상태
    static int[] ans;           // 각 도시별 X에 왔다 가는 소요 시간

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        cities = new List[N+1];
        ans = new int[N+1];
        for (int i = 0; i <= N; i++) cities[i] = new ArrayList<Conn>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            cities[x].add(new Conn(y, dist));
        }

        // 각 지역별 X 마을까지 가는 최단 거리를 구하기
        for (int i = 1; i <= N; i++) dijkstra(i);
        ans[0] = 0;

        // 가장 오래 걸린 시간 구하기
        System.out.println(Arrays.stream(ans).max().getAsInt());
    }

    static void dijkstra (int root) {
        int[] minDist = new int[N+1];           // root로부터 최단 거리
        boolean[] visited = new boolean[N+1];   // 최단 거리로 방문한 상태 체크
        Arrays.fill(minDist, INF);              // 최소 거리를 위한 배열 무한대값 초기화
        minDist[root] = 0;                      // root 최단 거리 0 초기화

        for (int i = 1; i < N; i++) {
            int index = 0;      // 최소 거리를 가진 인덱스
            int min = INF;      // 최소 거리
            for (int j = 1; j <= N; j++) {
                // 아직 최소 거리의 값으로 확정되지 않은 것 중 가장 짧은 위치
                if (!visited[j] && min > minDist[j]) {
                    min = minDist[j];
                    index = j;
                }
            }

            // 가장 짧은 거리를 가진 인덱스와 연결된 도시들의 값을 갱신해주는 작업
            for (int j = 0, size = cities[index].size(); j < size; j++) {
                Conn conn = cities[index].get(j);
                if (minDist[index] == INF || visited[conn.x]) continue;
                minDist[conn.x] = Math.min(minDist[conn.x], minDist[index] + conn.dist);
            }
            visited[index] = true;

            // 파티하는 마을이 아니라면 root-X까지 가는 값만 구하면 되므로 X까지 가는 최소 값이 정해지면 break
            if (root != X && index == X) break;
        }

        if (root == X) {
            // X에서 모든 마을까지의 돌아가는 최단 거리이므로 ans의 모든 값에 minDist를 더해준다.
            for (int i = 1; i <= N; i++) ans[i] += minDist[i];
        } else {
            // root-X까지 가는 최단 거리를 ans[root]에 더해준다.
            ans[root] += minDist[X];
        }
    }

    static class Conn {
        int x, dist;

        public Conn(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }
    }
}
