import java.io.*;
import java.util.*;

public class BOJ16398 {
    static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) cost[i][j] = Integer.parseInt(st.nextToken());
        }

        boolean[] visited = new boolean[N];
        long[] dist = new long[N];
        visited[0] = true;
        Arrays.fill(dist, INF);
        dist[0] = 0;

        long ans = 0;
        for (int i = 0; i < N; i++) {
            long min = INF;
            int index = 0;

            // 최단거리와 그 인덱스 찾기
            for (int j = 0; j < N; j++) {
                if (min > dist[j] && !visited[j]) {
                    min = dist[j];
                    index = j;
                }
            }
            visited[index] = true;

            // 최단거리에서 연결된 길의 최단 값 찾기
            for (int j = 0; j < N; j++) {
                if (visited[j] || index == j) continue;
                dist[j] = Math.min(dist[j], cost[index][j]);
            }
            ans += dist[index];
        }
        System.out.println(ans);
    }
}