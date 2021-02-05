# [백준 1966번: 프린터 큐 🖨️](https://www.acmicpc.net/problem/1966)

## sudo ✍
이 문제는 정말 직관적이라 문제만 이해하면 큰 문제없이 풀 수 있다.  

1. 현재 문서의 중요도를 확인
2. 더 높은 중요도 문서가 존재하면 이 문서를 큐의 마지막으로 보낸다.
3. 반복하면서 내가 원하는 문서가 인쇄될 때를 찾는다.

<br/>

## algorithm 💻
1. 중요도 비교를 위해 자동정렬되는 PriorityQueue 선언  
내림차순 정렬을 위해 역순 정렬
```java
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
```

2. 현재 문서의 중요도가 max 값인지 확인
    * 중요도가 max값이고 내가 찾는 문서 O → 몇 번째 인쇄물인지 반환
    * 중요도가 max값이고 내가 찾는 문서 X →  다음 큐 찾기
    * 중요도가 max 아님 → 큐의 맨 뒤로 보낸다.

    ```java
    if (pq.peek() == q.peek()[1] {
        ...
        int[] now = q.poll();
        if (now[0] == M) break ex;
    } else {
        q.add(q.poll());
    }
    ```

3. 끝 😀
