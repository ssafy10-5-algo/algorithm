import java.util.*;
import java.io.*;

public class BOJ17135 {
    static int N, M, D;                              // 행, 열, 공격 제한 거리
    static int kill;                                 // 적을 가장 많이 죽인 횟수
    static List<int[]> enemy = new ArrayList<>();    // 적 위치 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parse(st.nextToken());
        M = parse(st.nextToken());
        D = parse(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (parse(st.nextToken()) == 1) {
                    enemy.add(new int[]{i, j});
                }
            }
        }

        dfs(0, 0, new int[3]);          // 궁수 위치 조합 구하기
        System.out.print(kill);
    }

    static void dfs(int cnt, int idx, int[] archer) {
        if (cnt == 3) {                  // 궁수 3명이 배치되면 디펜스 시작
            defense(archer);
            return;
        }

        for (int i = idx; i < M; i++) {
            archer[cnt] = i;
            dfs(cnt+1, i+1, archer);
        }
    }

    static void defense(int[] archer) {
        int count = 0;

        boolean[] visited = new boolean[enemy.size()];      // 이미 죽인 적인지 판단
        int[] die = new int[3];                             // 동시에 공격하므로 각 궁수가 누구를 죽이는지 저장
        int X = N;                                          // 궁수들의 행
        while (!enemy.isEmpty()) {
            if (X == 0) break;                              // 궁수들이 격자 상단에 도착하면 끝
            for (int i = 0; i < 3; i++) {
                int minIdx = -1, minDist = 1000;
                for (int j = 0; j < enemy.size(); j++) {
                    if (enemy.get(j)[0] >= X) break;        // 적들의 행이 궁수보다 크거나 같으면 공격 제외 대상
                    if(visited[j]) continue;                // 이미 죽인 적 패스
                    int dist = Math.abs(X - enemy.get(j)[0]) + Math.abs(archer[i] - enemy.get(j)[1]);
                    if (dist > D) continue;                 // 공격 제한 거리보다 멀리있는 적 패스
                    if (dist < minDist) {                   // 가까운 적 찾기
                        minDist = dist;
                        minIdx = j;
                    } else if (dist == minDist) {           // 같은 거리일 때 가장 왼쪽에 있는 적 찾기
                        if (enemy.get(j)[1] < enemy.get(minIdx)[1]) minIdx = j;
                    }
                }
                die[i] = minIdx;                            // 각 궁수가 죽이는 적의 index 저장
            }

            for (int d : die) {                             // 동시에 죽이기
                if (d != -1 && !visited[d]) {
                    count++;
                    visited[d] = true;
                }
            }
            X--;                                            // 궁수 한 보 전진
        }
        kill = Math.max(count, kill);

    }


    static int parse(String s) {return Integer.parseInt(s);}
}
