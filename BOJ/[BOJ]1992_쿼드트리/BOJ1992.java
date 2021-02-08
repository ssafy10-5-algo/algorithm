import java.io.*;

public class BOJ1992 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        quadTree(arr, N);
        System.out.println(sb);
    }

    static void quadTree(char[][] arr, int n) {
        // 배열의 값이 하나거나 배열 내부의 값이 모드 일치하면 값 삽입
        if (arr.length == 1 || check(arr)) {
            sb.append(arr[0][0]);
            return;
        }

        // arr 배열을 4등분
        char[][] leftTop = new char[n/2][n/2];
        char[][] leftBottom = new char[n/2][n/2];
        char[][] rightTop = new char[n/2][n/2];
        char[][] rightBottom = new char[n/2][n/2];

        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n/2; j++) {
                leftTop[i][j] = arr[i][j];
                rightTop[i][j] = arr[i][j+n/2];
                leftBottom[i][j] = arr[i+n/2][j];
                rightBottom[i][j] = arr[i+n/2][j+n/2];
            }
        }

        // 배열을 4분할해서 재귀
        sb.append('(');
        quadTree(leftTop, n/2);
        quadTree(rightTop, n/2);
        quadTree(leftBottom, n/2);
        quadTree(rightBottom, n/2);
        sb.append(')');
    }

    // arr 배열의 모든 값이 같은지 체크
    static boolean check(char[][] arr) {
        for (int i = 0; i < arr.length ; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[0][0] != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
