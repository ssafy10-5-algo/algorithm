import java.io.*;
import java.util.*;

public class Main {
    static Set<String> set = new HashSet<>();   // 중복값 제거를 위해 set
    static String[][] map = new String[5][5];   // 입력 받아오는 칸

    static int[] dx = {1, -1, 0, 0};            // 상하좌우 탐색을 위한 배열
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().split(" ");
        }

        // 각 칸에서 시작 → dfs
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, 1 , map[i][j]);
            }
        }

        System.out.println(set.size());
    }

    static void dfs(int x, int y, int cnt, String s) {  // 현재 좌표, 움직인 횟수, 만들어진 문자열을 매개변수로 받음
        if (cnt == 6) {     // 6번 이동했을 때 stop
            set.add(s);     // 생성된 문자열 set에 삽입
            return;
        }

        for (int i = 0; i < 4; i++) {                               // 상하좌우로 이동
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;   // 범위 벗어나면 pass
            dfs(nx, ny, cnt+1, s+map[nx][ny]);               // 재귀
        }

    }
}