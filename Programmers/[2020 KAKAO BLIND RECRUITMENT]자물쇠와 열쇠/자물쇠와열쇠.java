import java.util.*;

class 자물쇠와열쇠 {
    public boolean solution(int[][] key, int[][] lock) {
        for (int i = 0; i < 4; i++) {
            key = rotation(key);                              // 키 회전
            if (move(key, lock, 20, 20)) return true;   // 움직이며 탐색
        }
        return false;
    }

    public int[][] rotation(int[][] key) {      // 90도 회전
        int M = key.length;
        int[][] arr = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) arr[M-j-1][i] = key[i][j];
        }
        return arr;
    }

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] visited = new boolean[60][60];      // 최대 N, M의 크기가 20이므로 좌우로 움직이는 최대 크기 60
    public boolean move(int[][] key, int[][] lock, int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        for (int i = 0; i < 40; i++) Arrays.fill(visited[i], false);
        visited[x][y] = true;

        int size = lock.length;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            if (check(key, lock, now[0], now[1])) return true;

            for (int i = 0; i < 4; i++) {   // 사방탐색하며 키를 움직인다.
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                // (20, 20) 좌표가 (0, 0)을 의미
                if (nx < 20-size || nx >= 20+size || ny < 20-size || ny >= 20+size || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
        return false;
    }

    public boolean check(int[][] key, int[][] lock, int x, int y) {
        int keySize = key.length;
        int lockSize = lock.length;

        int[][] bigKey = new int[60][60];
        for (int i = 0; i < keySize; i++) {
            System.arraycopy(key[i], 0, bigKey[i + x], y, keySize);
        }

        // (20, 20) ~ (20 + lockSize, 20+lockSize) 위치에 있는 키와 lock 을 맞춰본다.
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                if (lock[i][j] == bigKey[20+i][20+j]) return false;
            }
        }
        return true;
    }
}
