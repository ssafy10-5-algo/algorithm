import java.util.*;
import java.io.*;

public class BOJ1799 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int black, white;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];            // 비숍을 놓을 수 있는 곳인지 아닌지 체크
        visited = new boolean[N][N];    // 비숍을 놓았는지 아닌지 체크
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0, true, 0);     // 체스판의 검은색 부분
        dfs(0, 1, false, 0);    // 체스판의 하얀색 부분
        System.out.println(black + white);
    }

    static void dfs(int x, int y, boolean isBlack, int count) {
        if (y >= N) {
            x++;                                // col 값이 N 이상이 되면 row 값을 증가
            if (isBlack) y = x%2==0 ? 0 : 1;    // 검은색 칸인 경우, row 값이 짝수면 col은 0부터 시작 홀수면 1부터 시작
            else y = x%2==0 ? 1 : 0;            // 흰색 칸인 경우, row 값이 짝수면 col은 1부터 시작 홀수면 0부터 시작
        }
        if (x >= N) {   // x 값이 범위를 벗어나면 끝나면서 black과 white의 최대 개수를 체크
            if (isBlack) black = Math.max(black, count);
            else white = Math.max(white, count);
            return;
        }

        // 비숍이 놓아지지 않았고 대각선에 다른 비숍이 존재하지 않으며 비숍을 놓을 수 있는 자리인 경우
        if (isPossible(x, y) && !visited[x][y] && map[x][y] == 1) {
            visited[x][y] = true;
            dfs(x, y+2, isBlack, count+1);
            visited[x][y] = false;
        }
        // 현재 자리에 비숍을 놓지 않는 경우
        dfs(x, y+2, isBlack, count);
    }

    static int[] dx = {-1, -1, 1, 1};
    static int[] dy = {-1, 1, -1, 1};
    static boolean isPossible(int x, int y) {   // 대각선에 다른 비숍이 존재하는지 체크
        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            while(true) {
                nx += dx[i];
                ny += dy[i];
                if (nx<0 || nx>=N || ny<0 || ny>=N) break;
                if (visited[nx][ny]) return false;
            }
        }
        return true;
    }
}
