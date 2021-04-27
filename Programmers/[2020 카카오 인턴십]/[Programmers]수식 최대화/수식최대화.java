import java.util.*;

class 수식최대화 {
    Deque<String> q = new ArrayDeque<>();
    List<String> list = new ArrayList<>();  // 숫자, 연산자을 분리
    Character[][] orders = {                // 나올 수 있는 모든 경우의 수
            {'-', '+', '*'},
            {'-', '*', '+'},
            {'+', '-', '*'},
            {'+', '*', '-'},
            {'*', '+', '-'},
            {'*', '-', '+'}};

    public long solution(String expression) {

        //========== 숫자, 연산자 분리 ==========//
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '*' || c == '+' || c == '-') {
                list.add(sb.toString());
                list.add(String.valueOf(c));
                sb.setLength(0);
            } else sb.append(c);
        }
        list.add(sb.toString());
        list.add("/");
        //========== 분리 끝 ==========//

        long answer = 0;
        for (Character[] order : orders) {          // 연산자 우선순위에 따라 탐색 시작
            q.clear();
            q.addAll(list);                         // q 초기화
            for (Character o : order) {
                String sop = String.valueOf(o);
                while (true) {
                    long n1 = Long.parseLong(q.poll()); // 첫번째 수
                    String op = q.poll();               // 연산자
                    if ("/".equals(op)) {               // op가 /이라면 한 턴을 다 돌았다는 의미
                        q.add(String.valueOf(n1));
                        q.add("/");
                        break;
                    }
                    if (sop.equals(op)) {                           // 연산자가 현재 우선 순위와 일치하다면
                        long n2 = Long.parseLong(q.poll());         // q의 다음 값을 꺼낸다.
                        q.addFirst(String.valueOf(cal(n1, n2, o))); // n1과 n2의 연산된 결과를 큐의 맨 앞에 넣는다.
                    } else {                        // 우선 순위와 일치하지 않는다면
                        q.add(String.valueOf(n1));  // n1과 연산자를 q의 맨 뒤에 넣는다.
                        q.add(op);
                    }
                }
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(q.peek())));
        }
        return answer;
    }

    private long cal(long a, long b, char op) {
        if (op == '*') return a*b;
        if (op == '+') return a+b;
        return a-b;
    }
}