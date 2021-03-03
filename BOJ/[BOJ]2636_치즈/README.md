# [백준 2636: 치즈 🧀](https://www.acmicpc.net/problem/2636)  

## sudo✍
사실 이 문제는 한 달전 쯤에 풀었던 문제다. 당시에 굉장히 끙끙 앓면서 풀었던 걸로 기억하는데 아무튼 한 번 풀어본 문제라 그런지 쉽게 풀었다. 

1. 지도 입력받기
2. (0, 0)을 땅 queue에 넣고 상하좌우로 탐색
    1. 방문하지 않은 땅 → 땅 queue 삽입
    2. 접촉한 치즈 →  
        * 치즈 queue 삽입
        * 지도에서 접촉 치즈 위치 1로 변경
    3. 방문 체크
3. 반복 → 필요한 값 카운팅
4. 끝

<br/>

## algorithm 💻

1. 입력받기는 패스
2. (0, 0)을 땅 queue에 넣고 상하좌우로 탐색
    ```java
    land.add(new int[]{0, 0});

    ...

    while (!land.isEmpty()) {
        int[] nowLand = land.poll();
        for (int i = 0; i < 4; i++) {
            int nx = nowLand[0] + dx[i];
            int ny = nowLand[1] + dy[i];

            ...
        }
    }
    ```

    1. 방문하지 않은 땅
        ```java
        if (map[nx][ny] == 0){ land.add(new int[]{nx, ny});}
        ```
    2. 접촉한 치즈 
        ```java
        cheese.add(new int[]{nx, ny});
        map[nx][ny] = 0;
        ```
    3. 방문 체크
        ```java
        visited[nx][ny] = true;
        ```
3. 반복 → 반복 횟수 count (녹는데 소요되는 시간), 치즈 queue size 체크 (마지막 치즈 개수 count)
4. 끝

<br/>

## 소감 📢
풀어봤던 문제라 그런지 문제 핵심을 빠르게 캐치할 수 있었다! 그래서 예전 코드보다 시간, 메모리를 더 효율적으로 사용할 수 있었다. 문제를 다 풀고 예전 코드보니 똥이다.  
![image](https://user-images.githubusercontent.com/36289638/106635186-dfa39580-65c3-11eb-8e82-d37cabd010cb.png)

