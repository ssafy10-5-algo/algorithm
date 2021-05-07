import java.util.*;
import java.io.*;

public class BOJ19535 {
    static int N;
    static long D, G;
    static long[] conn;
    static Queue<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        conn = new long[N+1];
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            q.add(new int[]{u, v});
            conn[u]++; conn[v]++;
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();
            D += ((conn[now[0]]-1) * (conn[now[1]]-1));
        }

        for (long n : conn) {
            if (n >= 3) G += (n * (n-1) * (n-2))/6;
        }

        if (D > 3*G) System.out.print("D");
        else if (D < 3*G) System.out.print("G");
        else System.out.print("DUDUDUNGA");
    }
}