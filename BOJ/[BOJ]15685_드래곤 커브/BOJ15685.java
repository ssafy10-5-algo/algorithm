import java.util.*;
import java.io.*;

public class BOJ15685 {
    static int N, x, y, d, g;
    static List<Line> list = new ArrayList<>();
    static boolean[][] visited = new boolean[101][101];

    // 0: 상, 1: 좌, 2: 하, 3: 우 
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int[] cx = {0, 1, 0, 1};
    static int[] cy = {0, 0, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());        // 드래곤 커브의 개수

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());   // 커브 시작점 x
            y = Integer.parseInt(st.nextToken());   // 커브 시작점 y
            d = Integer.parseInt(st.nextToken());   // 시작 방향
            g = Integer.parseInt(st.nextToken());   // 세대

            list.clear();                           // N 번째 커브를 담아야 하므로 초기화
            int nx = x + dx[d];                     // 0세대 방향의 x
            int ny = y + dy[d];                     // 0세대 방향의 y
            list.add(new Line(x, y, nx, ny, d));    // 0세대 커브 list 추가
            visited[x][y] = true;                   // 0세대 커브가 존재하는 꼭지점 true 변경
            visited[nx][ny] = true;
            int[] last = new int[]{nx, ny};
            for (int j = 0; j < g; j++) {            // 세대만큼 커브 확장
                for (int k = list.size()-1; k >= 0; k--) {  // k-1번째 만들어진 커브들을 하나씩 돌림
                    Line now = list.get(k);
                    int rot_dir = rotation(now.dir);    // dir 방향을 돌려서 나온 방향
                    nx = last[0] + dx[rot_dir];         // 생성되는 커브 끝점 x
                    ny = last[1] + dy[rot_dir];         // 생성되는 커브 끝점 y
                    list.add(new Line(last[0], last[1], nx, ny, rot_dir));
                    last[0] = nx;               // 끝점 갱신
                    last[1] = ny;
                    visited[nx][ny] = true;     // 커브가 존재하는 포인트 방문 체크
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                boolean flag = true;
                for (int k = 0; k < 4; k++) {   // 현재, 우측, 상단, 대각선방면 체크
                    int nx = i + cx[k];
                    int ny = j + cy[k];
                    if (!visited[nx][ny]) {     // 네 꼭짓점 중 하나라도 커브 일부가 아니라면 제외
                        flag = false;
                        break;
                    }
                }
                if (flag) cnt++;                // 네 꼭짓점 모두 커브인 경우 cnt 증가
            }
        }
        System.out.println(cnt);    // 정답 출력
    }

    static int rotation(int dir) {
        switch (dir) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 0;
            default:
                return -1;
        }
    }

    static class Line {
        int x1, y1, x2, y2, dir;

        public Line(int x1, int y1, int x2, int y2, int dir) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.dir = dir;
        }
    }
}
