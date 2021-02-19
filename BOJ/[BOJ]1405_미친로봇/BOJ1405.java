import java.io.*;
import java.util.*;

public class BOJ1405 {
    static int N;
    static double sum;
    static int[] EWSN = new int[4];
    static boolean[][] map = new boolean[29][29];

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) EWSN[i] = Integer.parseInt(st.nextToken());     // 동서남북 확률 저장

        map[14][14] = true;                         // 음수로 갈 수도 있어 (0,0)을 (14, 14) 잡아줌
        combination(14, 14,  0, 1);
        System.out.println(sum);                    // 답 출력
    }

    static void combination(int x, int y, int cnt, double cal) {    // x, y좌표, 이동 횟수, 확률 계산
        if (cnt == N) {     // 단순하게 N번 이동한 경우의 확률들을 더함
            sum += cal;
            return;
        }

        for (int i = 0; i < 4; i++) {   // 상하좌우 이동
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (map[nx][ny] || EWSN[i] == 0) continue;  // 단순하지 않은 경로, 확률이 0인 경우 pass
            map[nx][ny] = true;
            combination(nx, ny, cnt+1, cal * EWSN[i] / 100);
            map[nx][ny] = false;
        }
    }
}
