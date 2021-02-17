import java.util.Scanner;

public class BOJ1074 {
    static int num, N, r, c;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        sc.close();

        zet(0,0, (int) Math.pow(2, N));
    }

    static void zet(int x, int y, int size) {
        if (r < x || r >= x + size || c < y || c >= y + size) { // 범위 넘어가면 멈춰!
            num += Math.pow(size, 2);                           // 배열[x][y]의 값이 들어갈테니까 값을 올려줌
            return;
        }

        if (size == 2) {    // 범위 일치하면 그 안에 답이 있다.
            if (x == r && y == c) System.out.print(num);
            else if (x == r && y+1 == c) System.out.print(num+1);
            else if (x+1 == r && y == c) System.out.print(num+2);
            else if (x+1 == r && y+1 == c) System.out.print(num+3);
            return;
        }

        int half = size / 2;
        zet(x, y, half);                        // 왼쪽 위
        zet(x, y + half, half);              // 오른쪽 위
        zet(x + half, y, half);              // 왼쪽 아래
        zet(x + half, y + half, half);    // 오른쪽 아래
    }
}