import java.util.*;
import java.io.*;

public class BOJ16562 {
    static int N, M, k, v, w;
    static int[] friends, fee;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        friends = new int[N+1];
        fee = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            friends[i] = i;
            fee[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            union(v, w);
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if (friends[i] == i) sum += fee[i];
        }

        if (sum <= k) System.out.print(sum);
        else System.out.print("Oh no");
    }

    static int find(int x) {
        if (friends[x] == x) return x;
        return find(friends[x]);
    }

    static void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        int min1 = Math.min(fee[rx], fee[ry]);
        int min2 = Math.min(fee[x], fee[y]);

        fee[rx] = Math.min(min1, min2);
        fee[ry] = fee[rx];

        if (rx == ry) return;
        if (rx > ry) friends[ry] = rx;
        else friends[rx] = ry;
    }
}
