# [백준 1918: 후위 표기식 ➕](https://www.acmicpc.net/problem/1918)

## sudo ✍  
이번 문제는 복잡하게 생각하다가 산🌄으로 들어간 문제였다.  거의 국토대장정급으로 돌아왔다.  

**1차 생각**  
1. 스택 2개 사용 (알파벳, 연산자)
2. 알파벳은 그냥 알파벳에 넣는다.
3. ( 가 나오면 continue;
4. ) 가 나오면 연산자 값을 빼서 알파벳에 넣는다.

주어진 예시는 당연시 맞아서 당당하게 제출했다가 틀렸다고 까였다.  
생각해보니까 *, /의 우선 순위를 따져줘야 했다.

<br/>  

**2차 생각**  
1. *, /가 나오면 문자열 2칸 뒤에 )를 추가하며 새로운 문자열로 갱신한다.
2. 위의 알고리즘을 이용한다.  

멍청한 생각이었다. (A*B) 이런 식으로 괄호가 이미 묶였거나 뭐 이러쿵저러쿵 여러 상황에서 에러가 발생했다. 위 알고리즘에 사로잡혀서 [테스트 케이스](https://www.acmicpc.net/board/view/54088)에 나온 예시들에 답을 끼워 맞추기 시작했다. 시간 순삭 안녕 🖐

<br/>

안되겠다 생각해서 다른 사람 코드를 봤다. 그랬더니 아주 간결하고 깔끔하고 스택을 유용하게 잘 사용하셨다. 감동 받아서 (사실 내 멍청함에) 광광 울었따 울었어...😭😂  

**3차 생각**
1. 연산자 스택 하나만 사용
2. **알파벳** → StringBuilder에 삽입
3. **(** → 연산자 스택 push
4. **)** → (가 나올 때까지 연산자 스택 값을 pop해서 StringBuilder에 삽입
5. ***, /** → 연산자 스택 제일 위에 *, /가 존재하면 값을 pop해서 StringBuilder에 삽입 후 자신을 연산자에 push
6. **+, -** →  연산자 높은 애들이 나오지 않을 때 까지 stack의 모든 값 StringBuilder에 삽입 후 자신을 연산자에 push
7. stack에 남은 값들을 StringBuilder에 삽입
8. 끝


<br/>

## algorithm 💻  
1. 답 저장을 위한 StringBuilder와 연산자 Stack 선언
    ```java
    StringBuilder sb = new StringBuilder();
    Stack<Character> ops = new Stack<>();
    ```

2. String s의 문자열을 하나씩 가져와서 순회
    ```java
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        ...
    }
    ```

3. 알파벳
    ```java
    if (Character.isUpperCase(c)) sb.append(c);
    ```

4. (
    ```java
    else if (c == '(') ops.push(c);
    ```

5. )
    ```java
    else if (c == ')') {
        while (ops.peek() != '(') sb.append(ops.pop());
        ops.pop();
    }
    ```

6. *, /
    ```java
    else if (c == '*' || c == '/') {  
        while (!ops.isEmpty() && (ops.peek() == '*' || ops.peek() == '/')) sb.append(ops.pop());
        ops.push(c);
    }
    ```

7. +, -
    ```java
    else {                     
        while (!ops.isEmpty() && ops.peek() != '(' ) sb.append(ops.pop());
        ops.push(c);
    }
    ```

8. 나머지 연산자 pop
    ```java
    while (!ops.isEmpty()) sb.append(ops.pop());
    ```

<br/>

## 배운 점🌵
내 아이디어에 몰입하지 말자. 오히려 시간을 낭비하고 있을 수도 있는거 같다.  
너무 오래 걸린다면 과감하게 코드를 지우고 새롭게 짜곤 하는데 왜 이번 문제는 안 그랬을까?  
아무튼 다른 사람의 코드를 보고 짠 것이니 내 것이 될 수 있도록 꼼꼼하게 다시 한 번 봐야겠다 😀