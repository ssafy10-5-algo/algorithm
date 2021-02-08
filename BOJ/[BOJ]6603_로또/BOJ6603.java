import java.util.*;
import java.io.*;

public class BOJ6603 {
    static int k;
    static int[] S;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = parse(st.nextToken());
        while (k != 0) {
            S = new int[k];
            for (int i = 0; i < k; i++) S[i] = parse(st.nextToken());

            choose(0, 0, new int[6]);   // 숫자 선택

            st = new StringTokenizer(br.readLine());
            k = parse(st.nextToken());
            sb.append("\n");
        }
        sb.setLength(sb.length()-1);    // 마지막 개행문자 삭제
        System.out.print(sb);           // 출력
    }

    static void choose(int cnt, int index, int[] lotto) {
        if (cnt == 6) {                     // 6개 선택 완료
            for (int l : lotto) {
                sb.append(l).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = index; i < k ; i++) {
            lotto[cnt] = S[i];              // cnt번째 선택한 숫자 
            choose(cnt + 1, i + 1, lotto);  // cnt+1번째 숫자 선택을 위한 재귀
        }
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
