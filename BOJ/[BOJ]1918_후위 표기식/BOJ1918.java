import java.util.*;
public class BOJ1918 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    // 입력
        String s = sc.next();

        StringBuilder sb = new StringBuilder(); // 답
        Stack<Character> ops = new Stack<>();   // 연산자 저장 스택
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) sb.append(c); // 알파벳 → 답에 넣음
            else if (c == '(') ops.push(c);             // ( → 연산자 스택
            else if (c == ')') {                        // ) → 연산자 스택에서 ( 나올 때 까지 pop
                while (ops.peek() != '(') {
                    sb.append(ops.pop());
                }
                ops.pop();
            } else if (c == '*' || c == '/') {          // *, / → 스택 위에 *, /가 있다면 계속 pop (연산자 순위 높음)
                while (!ops.isEmpty() && (ops.peek() == '*' || ops.peek() == '/')) {
                    sb.append(ops.pop());
                }
                ops.push(c);
            } else {                                    // +, -  → (가 나오기 전까지 모든 연산자 꺼냄
                while (!ops.isEmpty() && ops.peek() != '(' ) {
                    sb.append(ops.pop());
                }
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) sb.append(ops.pop());    // 남은 연산자 꺼냄
        System.out.print(sb);
    }
}
