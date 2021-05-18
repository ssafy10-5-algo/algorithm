import java.util.*;

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    final int INF = Integer.MAX_VALUE;

    public int solution(int[][] board) {
        int len = board.length;
        int[][] map = new int[len][len];
        for (int i = 0; i < len; i++) Arrays.fill(map[i], INF);
        map[0][0] = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 2, 0});   // x, y, dir, cost 저장
        q.add(new int[]{0, 0, 3, 0});   // (0, 0)에서는 오른쪽, 아래방향만 갈 수 있으므로 dir 2, 3만 추가

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || nx >= len || ny < 0 || ny >= len || board[nx][ny] == 1) continue; // 범위 out 또는 벽인 경우 불가
                int cost = now[3] + 100;
                if (now[2] != i) cost += 500;            // 현재 방향과 다른 경우 코너
                if (cost <= map[nx][ny]) {               // cost값이 기존 건설 비용보다 작거나 같다면
                    map[nx][ny] = cost;                  // 값 갱신
                    q.add(new int[]{nx, ny, i, cost});
                }
            }
        }
        return map[len-1][len-1];
    }
}