import java.util.*;

class Solution {
    int N = 0;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {-1, 1, 0, 0};

    public int solution(int[][] board) {
        Queue<Robot> q = new ArrayDeque<>();
        q.add(new Robot(0, 0, 0, 1, 0, 0));     // 로봇의 처음 위치 넣기
        N = board.length;
        boolean[][][][] visited = new boolean[N][N][N][N];  // 방문 체크를 위한 visited 배열
        visited[0][0][0][1] = true;
        visited[0][1][0][0] = true;

        while (!q.isEmpty()) {
            Robot now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx1 = now.x1 + dx[i];
                int ny1 = now.y1 + dy[i];
                int nx2 = now.x2 + dx[i];
                int ny2 = now.y2 + dy[i];
                if (!checkRange(nx1, ny1) || !checkRange(nx2, ny2)) continue;       // 범위 밖이면 out
                if (board[nx1][ny1] == 1 || board[nx2][ny2] == 1) continue;         // 가려는 곳이 벽이면 ;out
                if (isAnswer(nx1, ny1) || isAnswer(nx2, ny2)) return now.move + 1;  // 정답이면 움직인 횟수 return
                if (now.dir == 0 && i >= 2 || now.dir == 1 && i < 2) {  // 회전 하려는 경우
                    if (!visited[now.x1][now.y1][nx1][ny1]) {
                        q.add(new Robot(now.x1, now.y1, nx1, ny1, (now.dir + 1)%2, now.move + 1));
                        visited[now.x1][now.y1][nx1][ny1] = true;
                        visited[nx1][ny1][now.x1][now.y1] = true;
                    }
                    if (!visited[nx2][ny2][now.x2][now.y2]) {
                        q.add(new Robot(nx2, ny2, now.x2, now.y2, (now.dir + 1)%2, now.move + 1));
                        visited[nx2][ny2][now.x2][now.y2] = true;
                        visited[now.x2][now.y2][nx2][ny2] = true;
                    }
                }

                // 회전하지 않는 경우
                if (visited[nx1][ny1][nx2][ny2]) continue;
                q.add(new Robot(nx1, ny1, nx2, ny2, now.dir, now.move+1));
                visited[nx1][ny1][nx2][ny2] = true;
                visited[nx2][ny2][nx1][ny1] = true;
            }
        }
        return 0;
    }

    boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    boolean isAnswer(int x, int y) {
        return x==N-1 && y==N-1;
    }

    class Robot {
        int x1, y1, x2, y2, dir, move;

        public Robot(int x1, int y1, int x2, int y2, int dir, int move) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.dir = dir;
            this.move = move;
        }
    }
}