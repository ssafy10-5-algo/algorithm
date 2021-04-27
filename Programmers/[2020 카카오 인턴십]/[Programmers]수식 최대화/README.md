# [Programmers: 수식 최대화 👆](https://programmers.co.kr/learn/courses/30/lessons/67257)

## sudo ✍  
1. 연산자 우선 순위 조합을 구한다.
2. expression을 숫자, 연산자로 분리한다.
3. 우선 순위에 따라 계산을 시작한다.  
    * 첫 번째 우선 순위 연산자가 포함된 수식을 모두 계산한다.
    * 두 번째 우선 순위 연산자가 포함된 수식을 모두 계산한다.
    * 마지막 연산자가 포함된 수식을 모두 계산한다.
2. 최종 계산 결과의 max값을 반환한다.

<br>

## algorithm 💻  
1. 연산자 우선 순위 조합을 구한다.  
    이 문제에 나오는 수식은 +, -, * 뿐이라 처음부터 모든 조합을 만들고 시작했다.
    ```java
    Character[][] orders = {
            {'-', '+', '*'},
            {'-', '*', '+'},
            {'+', '-', '*'},
            {'+', '*', '-'},
            {'*', '+', '-'},
            {'*', '-', '+'}
        };
    ```

2. expression을 숫자, 연산자로 분리한다.

    ### [**Example**]
    expression  :  100-200*300-500+20

    |List|1|2|3|4|5|6|7|8|9|
    |-|-|-|-|-|-|-|-|-|-|
    ||100|-|200|*|300|-|500|+|20|

    ```java
    for (int i = 0; i < expression.length(); i++) {
        char c = expression.charAt(i);
        if (c == '*' || c == '+' || c == '-') {     // 연산자가 나오면
            list.add(sb.toString());                // sb에 저장한 문자열(숫자)을 저장
            list.add(String.valueOf(c));            // 이어서 연산자 저장
            sb.append(0);
        } else sb.append(c);
    }
    list.add(sb.toString())                         // 마지막 숫자 저장
    ```
    
<br>

3. 우선 순위에 따라 계산을 시작한다.    
    ```
    [Example]  
    expression  :  100-200*300-500+20  
    operation   :  + > - > *

    after + :   100-200*300-520
    after - :   -100*(-220) 
    after * :   4400

    return : 4400
    ```
    ```java
    for (Character[] order : orders) {
        ...
        for (Character o : order) {
            String sop = String.valueOf(o);
            while (true) {
                long n1 = Long.parseLong(q.poll());
                String op = q.poll();

                ...

                if (sop.equals(op)) {  
                    long n2 = Long.parseLong(q.poll());
                    q.addFirst(String.valueOf(cal(n1, n2, o)));
                } else {
                    q.add(String.valueOf(n1));
                    q.add(op);
                }
            }
        }
    }
    ```
4. 최종 값 리턴
    ```java
    answer = Math.max(answer, Math.abs(Long.parseLong(q.peek())));
    ```

## 느낀점 🌵
[예전에 작성한 코드](https://colorscripter.com/s/kYEMwZE)보다 속도가 훨씬 빠르다. 거의 1초 내에서 다 끝나서 신기하다. 그리고 예전코드 왜케 복잡해보이지... 다시 이해해 봐야겠다... 진짜 막 작성했는데 신기하다. 예전에는 이거 어디선가 구글링해서 풀었던 거 같은데 이번에는 안보고 풀었다! 뿌듯하당! 🙌