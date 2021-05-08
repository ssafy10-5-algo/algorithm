import java.io.*;
import java.util.*;

public class BOJ19236 {

    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[4][4];    // (i, j)에 어떤 물고기가 있는지 체크
        Fish[] fish = new Fish[17];     // i번째 물고기의 좌표값과 방향값 저장
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                int index = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                map[i][j] = index;
                fish[index] = new Fish(i, j, dir);
            }
        }

        // ======= 입력 받기 끝 ========= //

        int index = map[0][0];
        Shark shark = new Shark(0, 0, fish[index].dir, map[0][0]);      // 상어의 시작은 (0,0)
        fish[index].x = -1;     // (0, 0) 위치에 있던 물고기 제거
        map[0][0] = -1;
        moveFish(map, fish);    // 물고기 이동
        dfs(map, fish, shark, 0);
        System.out.print(ans);
    }

    static void dfs(int[][] map, Fish[] fish, Shark shark, int count) {
        int nx = shark.x;
        int ny = shark.y;
        for (int i = 0; i < 4; i++) {
            nx += dx[shark.dir];
            ny += dy[shark.dir];

            if (!isPossibleToMove(map, nx, ny, shark.dir)) { // 더 이상 이동할 수 없으면 return;
                ans = Math.max(ans, shark.cost);
                return;
            }

            if (map[nx][ny] > 0) {                              // 물고기가 있는 자리
                int[][] copy = copyMap(map, new int[4][4]);
                int index = map[nx][ny];
                Shark nextShark = new Shark(nx, ny, fish[index].dir, shark.cost + index);   // 상어 자리 (nx, ny)로 갱신해주고 먹은 물고기 번호를 더해준다.
                Fish[] nextFish = new Fish[17];
                for (int j = 1; j < 17; j++) {
                    Fish f = fish[j];
                    nextFish[j] = new Fish(f.x, f.y, f.dir);
                }
                nextFish[index].x = -1;         // nx, ny에 있는 물고기를 먹는다.
                copy[shark.x][shark.y] = 0;     // 기존 상어의 위치에서
                copy[nx][ny] = -1;              // 잡아먹는 물고기 위치로 이동한다.
                moveFish(copy, nextFish);       // 물고기 이동한다.
                dfs(copy, nextFish, nextShark, count + 1);
            }
        }
    }

    static void moveFish(int[][] map, Fish[] fish) {
        for (int i = 1; i <= 16; i++) {
            Fish f = fish[i];
            if (f.x == -1) continue;        // 이미 먹힌 물고기라면 pass
            int count = 0;
            while(count <= 8) {
                int nx = f.x + dx[f.dir];
                int ny = f.y + dy[f.dir];

                if (!isPossibleRange(nx, ny) || map[nx][ny] == -1) {    // 범위 밖이거나 상어가 존재하는 위치면 회전
                    f.dir = rotation45(f.dir);
                    count++;
                    continue;
                }

                if (map[nx][ny] == 0) { // 빈 칸이면 바로 그 자리로 이동
                    map[f.x][f.y] = 0;
                } else {                // 물고기가 있다면 위치 swap
                    Fish f2 = fish[map[nx][ny]];
                    f2.x = f.x;
                    f2.y = f.y;
                    map[f.x][f.y] = map[nx][ny];
                }
                f.x = nx;
                f.y = ny;
                map[nx][ny] = i;
                break;
            }
        }
    }

    static boolean isPossibleRange(int nx, int ny) {
        return nx >= 0 && nx < 4 && ny >= 0 && ny < 4;
    }

    // 상어가 움직이는 방향쪽으로 물고기가 존재하는지 체크
    static boolean isPossibleToMove(int[][] map, int nx, int ny, int dir) {
        while (isPossibleRange(nx, ny)) {
            if (map[nx][ny] > 0) return true;
            nx += dx[dir];
            ny += dy[dir];
        }
        return false;
    }

    static int rotation45(int dir) {
        return dir + 1 == 9 ? 1 : dir + 1;
    }

    static int[][] copyMap(int[][] map, int[][] copy) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) copy[i][j] = map[i][j];
        }
        return copy;
    }

    static class Shark extends Fish {
        int cost;

        public Shark(int x, int y, int dir, int cost) {
            super(x, y, dir);
            this.cost = cost;
        }
    }

    static class Fish {
        int x, y, dir;

        public Fish(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
