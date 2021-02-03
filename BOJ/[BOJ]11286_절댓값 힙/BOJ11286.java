import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Num> q = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (q.isEmpty()) sb.append("0\n");
                else {
                    Num num = q.poll();
                    sb.append(num.real + "\n");
                }
            } else {
                q.add(new Num(x, Math.abs(x)));
            }

        }
        System.out.println(sb.toString());
    }

    static class Num implements Comparable<Num> {
        int real, abs;

        public Num(int real, int abs) {
            this.real = real;
            this.abs = abs;
        }

        @Override
        public int compareTo(Num o) {
            if (this.abs == o.abs) return this.real - o.real;
            return this.abs - o.abs;
        }
    }
}
