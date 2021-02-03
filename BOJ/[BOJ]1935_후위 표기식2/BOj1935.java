package D0122;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parse(br.readLine());
        char[] expression = br.readLine().toCharArray();
        int[] alphabet = new int[26];

        // A부터 순차적으로 알파벳 사용
        for (int i = 0; i < N; i++) {
            alphabet[i] = parse(br.readLine());
        }

        // 알파벳이면 alphabet 배열과 매칭하여 값 삽입
        // 연산기호면 stack 값 2개 빼와서 연산 후 다시 삽입
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < expression.length; i++) {
            char c = expression[i];
            if (c >= 'A' && c <= 'Z') {
                stack.add((double) alphabet[c - 'A']);
            } else {
                double second = stack.pop();
                double first = stack.pop();
                double ans = 0;

                switch (c) {
                    case '*':
                        ans = first * second;
                        break;
                    case '+':
                        ans = first + second;
                        break;
                    case '-':
                        ans = first - second;
                        break;
                    case '/':
                        ans = first / second;
                        break;
                    default:
                }

                stack.add(ans);
            }
        }

        System.out.printf("%.2f", stack.pop());
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
