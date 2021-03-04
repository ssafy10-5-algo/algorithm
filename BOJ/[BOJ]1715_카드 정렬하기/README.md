# [백준 1715번: 카드 정렬하기 🃏](https://www.acmicpc.net/problem/1715)

<br/>

### sudo ✍
사실 이 문제는 예전에 [파일 합치기](https://www.acmicpc.net/problem/11066) 문제에서 잘못 이해하여 작성한 코드가 이 문제의 답이었다. 우하하!

#### 아이디어  
1. 카드 더미를 오름차순 정렬한다.
2. 더미가 작은 2개의 더미를 합친다.
3. 하나의 카드 더미가 나올 때 까지 반복한다.
4. 끝

더미를 합치고 합친 더미의 수를 계속 더해 나아가야 한다. 그러므로 작은 더미들끼리 합치는게 최소 비교 횟수가 된다.

<br/>

### algorithm 💻  
1. 카드 더미를 오름차순 정렬한다.
    pq로 자동 정렬되게 해주었다.
    ```java
    PriorityQueue<Integer> q = new PriorityQueue<>();
    ```

2. 더미가 작은 2개의 더미를 합친다.
3. 하나의 카드 더미가 나올 때 까지 반복한다.
    ```java
    while (q.size() != 1) {
        int tmp = q.poll() + q.poll();
        q.add(tmp);
        sum += tmp;
    }
    ```
4. 끝!
    ```java
    Sysetm.out.println(sum);
    ```