import java.io.*;
import java.util.*;

public class Main {

    // 상하좌우 검색하기 위한 배열
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // R, C 입력
        int R = parse(st.nextToken());
        int C = parse(st.nextToken());

        char[][] land = new char[R][C];             // 울타리, 빈 필드, 양, 늑대 정보담는 배열
        Queue<int[]> field = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            land[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (land[i][j] != '#') {            // 울타리가 아닌 부분 queue에 저장
                   field.add(new int[]{i, j});
                }
            }
        }

        boolean[][] visited = new boolean[R][C];    // 방문 여부 체크
        int wolfCnt = 0; int sheepCount = 0;        // 최종적으로 살아남은 늑대, 양 체크

        while (!field.isEmpty()) {                  // 울타리가 아닌 모든 땅을 체크
            int[] pos = field.poll();
            int posR = pos[0]; int posC = pos[1];
            if(visited[posR][posC]) continue;       // 이미 방문했던 땅은 pass

            visited[posR][posC] = true;             // 방문 체크
            Queue<int[]> town = new ArrayDeque<>(); // 울타리로 나눠진 공간을 저장할 queue
            town.add(pos);

            int wolf = 0; int sheep = 0;            // 나눠진 공간에서의 양과 늑대의 수 체크
            if (land[posR][posC] == 'v') wolf++;
            else if (land[posR][posC] == 'o') sheep++;

            while (!town.isEmpty()) {               // 나눠진 공간을 본격적으로 체크
                int[] now = town.poll();
                int r = now[0]; int c = now[1];
                visited[r][c] = true;

                for (int i = 0; i < 4; i++) {       // 상하좌우로 연결된 땅 존재하는지 체크
                    int nx = r + dx[i];
                    int ny = c + dy[i];

                    // 범위를 벗어나거나 이미 방문했던 땅이거나 울타리이면 pass
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny] || land[nx][ny] == '#') continue;

                    // 연결된 땅이면 queue에 삽입하고 늑대/양이 존재하는지 체크해서 카운팅
                    if (land[nx][ny] == 'v') wolf++;
                    else if (land[nx][ny] == 'o') sheep++;
                    visited[nx][ny] = true;
                    town.add(new int[]{nx, ny});
                }
            }

            // 나눠진 공간에서 늑대보다 양이 많으면 최종적인 양 count 증가, 같거나 늑대가 많다면 최종적인 늑대 count 증가
            if (wolf < sheep) sheepCount += sheep;
            else wolfCnt += wolf;
        }

        System.out.println(sheepCount + " " + wolfCnt);
    }

    static int parse(String s) {return Integer.parseInt(s);}
}
