import java.io.*;
import java.util.*;

public class BOJ16235 {
    static int N, M, K;
    static int[][] A, land;
    static PriorityQueue<Tree> alive = new PriorityQueue<>();
    static Queue<Tree> dead = new ArrayDeque<>();
    static Queue<Tree> tmp = new ArrayDeque<>();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 땅 크기
        M = Integer.parseInt(st.nextToken());   // 구매한 묘목 수
        K = Integer.parseInt(st.nextToken());   // K년 후

        A = new int[N][N];      // 추가할 양분
        land = new int[N][N];   // 땅의 양분
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                land[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            alive.add(new Tree(x-1, y-1, year));
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }
        System.out.println(alive.size());
    }

    // 봄 :  나무가 나이만큼 양분을 먹고 먹지 못하면 죽는다.
    static void spring() {
        while (!alive.isEmpty()) {
            Tree tree = alive.poll();
            if (land[tree.x][tree.y] < tree.year) dead.add(tree);
            else {
                land[tree.x][tree.y] -= tree.year;
                tmp.add(new Tree(tree.x, tree.y, tree.year+1));
            }
        }
        while(!tmp.isEmpty()) alive.add(tmp.poll());
    }

    // 여름 : 죽은 나무가 거름으로 변환한다.
    static void summer() {
        while (!dead.isEmpty()) {
            Tree tree = dead.poll();
            land[tree.x][tree.y] += (tree.year/2);
        }
    }

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    // 가을 : 나이가 5의 배수인 경우 인접한 모든 곳에 나이가 1인 나무가 자라난다.
    static void autumn() {
        while (!alive.isEmpty()) {
            Tree tree = alive.poll();
            tmp.add(tree);
            if (tree.year % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = tree.x + dx[i];
                    int ny = tree.y + dy[i];
                    if (nx<0 || nx>=N || ny<0 || ny>=N) continue;
                    tmp.add(new Tree(nx, ny, 1));
                }
            }
        }
        while(!tmp.isEmpty()) alive.add(tmp.poll());
    }

    // 겨울 : 땅에 양분을 추가한다.
    static void winter() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                land[i][j] += A[i][j];
    }

    static class Tree implements Comparable<Tree>{
        int x, y, year;

        public Tree(int x, int y, int year) {
            this.x = x;
            this.y = y;
            this.year = year;
        }

        @Override
        public int compareTo(Tree o) {
            return this.year - o.year;
        }
    }
}