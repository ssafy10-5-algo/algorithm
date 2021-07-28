import java.awt.*;
import java.io.*;
import java.util.*;

public class BOJ3197 {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;

    static Point[] swanPos = new Point[2];          // 백조 2마리 위치
    static Queue<Point> water = new ArrayDeque<>(); // 얼음 녹아서 물 되는거
    static Queue<Point> swan = new ArrayDeque<>();  // 백조 한마리 헤엄치는거

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        //===== 입력 시작 =====//
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        int index = 0;
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') swanPos[index++] = new Point(i, j);
                if (map[i][j] != 'X') water.add(new Point(i, j));
            }
        }
        //===== 입력 끝 =====//
        swan.add(swanPos[0]);

        int day = 0;
        while (!moveSwan()) {
            melt();
            day++;
        }
        System.out.println(day);
    }

    // 백조 움직인다.
    static Queue<Point> nextSwan = new ArrayDeque<>();
    static boolean moveSwan() {
        while (!swan.isEmpty()) {
            Point now = swan.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (!NotOutOfRange(nx, ny) || visited[nx][ny]) continue;    // 범위 out 또는 이미 방문했던 장소면 pass
                if (nx == swanPos[1].x && ny == swanPos[1].y) return true;  // 첫번째 백조가 움직이다 두번째 백조 만나면 true
                if (map[nx][ny] == 'X') nextSwan.add(new Point(nx, ny));    // 얼음인 곳은 내일 움직일 수 있으므로 다른 곳에 보관
                else swan.add(new Point(nx, ny));                           // 갈 수 있는 곳이면 바로 이동
                visited[nx][ny] = true;
            }
        }
        while (!nextSwan.isEmpty()) swan.add(nextSwan.poll());  // 오늘이 지났으니 내일 갈 수 있는 곳 가져오기
        return false;
    }

    static void melt() {
        for (int loop = 0, size = water.size(); loop < size; loop++) {
            Point now = water.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (!NotOutOfRange(nx, ny) || map[nx][ny] != 'X') continue; // 범위 out 또는 얼음이 아닌 곳은 이미 방문했던 곳이므로 pass
                map[nx][ny] = '.';              // 인접한 곳의 얼음을 물로 녹임
                water.add(new Point(nx, ny));
            }
        }
    }

    static boolean NotOutOfRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
