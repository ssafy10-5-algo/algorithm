# [백준 1935: 후위표기식2➕](https://www.acmicpc.net/problem/1935)  


## 🤔 내가 생각한 알고리즘
1. 후위표현식을 받아와 앞에서 순차적으로 탐색한다.
2. 연산자가 나오는 경우 앞에 나온 두 알파벳을 계산한다.
3. 반복
4. 정답 😍


<br/>

## 🔑 알고리즘 풀이
1. 후위표현식 받아와 문자열의 배열로 저장

    ```{.java}
    char[] expression = br.readLine().toCharArray();
    ```

2. 배열을 알파벳의 값을 받아온다.  (index-alphabet[index] = 알파벳-숫자)  
문제조건 :  A부터 순서대로 N개의 영대문자만이 사용

    ```{.java}
    for (int i = 0; i < N; i++) {
        alphabet[i] = parse(br.readLine());
    }
    ```

3. 후위표현식을 하나씩 읽어온다.
    * 알파벳인 경우, stack에 알파벳이 의미하는 숫자 push
    * 연산자인 경우, stack에서 값 2개를 빼서 연산 → 결과값 push  

    ```{.java}
    Stack<Double> stack = new Stack<>();
    for (char c : expression) {
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
    ```


