import java.io.*;
import java.util.*;

public class BOJ9177 {
    static String word1, word2, word;
    static boolean[][] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());   // max 1000
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= TC; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            word1 = st.nextToken();     // 첫번째 단어
            word2 = st.nextToken();     // 두번째 단어
            word = st.nextToken();      // 합쳐서 만든 단어
            check = new boolean[word1.length()+1][word2.length()+1];    // 메모이제이션을 위한 배열
            sb.append("Data set ").append(test_case).append(": ").append(makeWord(0, 0, 0)? "yes" : "no").append("\n");
        }
        System.out.print(sb);
    }

    static boolean makeWord(int index1, int index2, int index) {
        if (index == word.length()) return true;    // 원하는 단어를 만들었다? 성공
        if (check[index1][index2]) return false;    // 기존에 만들었던 단언데 실패했던 단어면 미리 제거

        // 첫번째 단어 크기보다 index1의 크기가 작고
        // 만드려는 단어의 index번째 알파벳과 첫번째 단어의 index1번째 알파벳이 일치
        if (index1 < word1.length() && word1.charAt(index1) == word.charAt(index)) if (makeWord(index1+1, index2, index+1)) return true;

        // 두번째 단어 크기보다 index2의 크기가 작고
        // 만드려는 단어의 index번째 알파벳과 두번째 단어의 index2번째 알파벳이 일치
        if (index2 < word2.length() && word2.charAt(index2) == word.charAt(index)) if (makeWord(index1, index2+1, index+1)) return true;

        // 아무것도 일치하지 않는다.
        check[index1][index2] = true;
        return false;
    }
}
