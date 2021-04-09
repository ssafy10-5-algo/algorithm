import java.io.*;
import java.util.*;

public class BOJ1194 {
    static int N, M;
    static char[][] map;
    static boolean[][][] visited;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static Queue<Pos> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][1<<6];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == '0') {                   // 민식이 위치 찾기
                    q.add(new Pos(i, j, 0, 0));
                    map[i][j] = '.';
                } else map[i][j] = s.charAt(j);
            }
        }

        // bfs 탐색 시작
        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                // 방문한 적이 있거나 벽이거나 범위 벗어나면 out
                if (nx<0 || nx>=N || ny<0 || ny>=M || map[nx][ny] == '#' || visited[nx][ny][now.key]) continue;

                // 문을 만났을 때 키가 없으면 out
                if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F' && (now.key & (1<<map[nx][ny]-'A')) == 0) continue;

                // 출구를 찾으면 바로 이동한 횟수 출력 후 종료
                if (map[nx][ny] == '1') {
                    System.out.println(now.time + 1);
                    return;
                }

                // 키를 만나면 키를 획득 → 방문 여부 체크
                if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                    int nkey = now.key | 1 << (map[nx][ny] - 'a');
                    q.add(new Pos(nx, ny, now.time+1, nkey));
                    visited[nx][ny][nkey] = true;
                } else {
                    q.add(new Pos(nx, ny, now.time+1, now.key));
                    visited[nx][ny][now.key] = true;
                }
            }
        }

        // 큐를 다 뒤져도 출구를 찾지 못하면 -1 출력
        System.out.println(-1);
    }

    static class Pos {
        int x, y, time, key;

        public Pos(int x, int y, int time, int key) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.key = key;
        }
    }
}
