# [백준 16235번: 나무 재테크 🌲](https://www.acmicpc.net/problem/16235)

### sudo ✍

이 문제는 문제의 내용대로 코딩을 했다! 딱 구현문제?  
문제대로 코딩하면 결과 끝!

<br/>

#### 구현 💭  
1. 봄: 나이만큼 양분을 먹고 나이 1 증가, 양분 못먹으면 사망
2. 여름: 봄에 죽은 나무가 거름으로 전환
3. 가을: 나이가 5의 배수인 경우 인접한 곳에 나이가 1인 나무 생성
4. 겨울: 로봇이 땅에 양분 추가

<br/>

PriorityQueue와 Queue를 사용해서 풀었다 :)  
봄에서 나이가 어린 나무 먼저 양분을 먹어줘야하므로 PriorityQueue를 사용했다!  
죽은 나무와 값이 변화할 때 임시로 사용할 애들은 그냥 Queue를 사용했다! pq를 하면 정렬이 되니까 거기서도 시간이 소요될 거라 생각했기 떄문이다.

<br/>

결과적으로 속도는 느려... 흠흠... 다른 사람 코드를 구경해야겠다...

<br/>

### algoritm 💻  
1. 봄: 나이만큼 양분을 먹고 나이 1 증가하고 양분을 먹지 못하면 사망
    ```java
    if (land[tree.x][tree.y] < tree.year) dead.add(tree);
    else {
        land[tree.x][tree.y] -= tree.year;
        tmp.add(new Tree(tree.x, tree.y, tree.year+1));
    }
    ```

2. 여름: 봄에 죽은 나무 거름으로 전환
    ```java
    while (!dead.isEmpty()) {
        Tree tree = dead.poll();
        land[tree.x][tree.y] += (tree.year/2);
    }
    ```

3. 가을: 나이가 5의 배수인 나무 인접한 곳에 새로운 아기 나무 생성
    ```java
    if (tree.year % 5 == 0) {
        for (int i = 0; i < 8; i++) {
            int nx = tree.x + dx[i];
            int ny = tree.y + dy[i];
            tmp.add(new Tree(nx, ny, 1));
        }
    }
    ```

4. 겨울: 땅에 양분 추가
    ```java
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            land[i][j] += A[i][j];
    ```