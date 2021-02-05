# [백준 2210: 숫자판 점프🔢](https://www.acmicpc.net/problem/2210)  

## sudo✍
음 사실 이 문제 보자마자 dfs로 풀어야겠다 생각이 들어서 바로 코딩을 했다.

<br/>

## algorithm💻
### DFS
ABCD로 만들 수 있는 4글자를 만든다고 가정하자. (본인 글자 뒤에 본인 제외한 모든 글자가 올 수 있다.)  
![image](https://user-images.githubusercontent.com/36289638/106431367-7f6fff00-64b0-11eb-8b86-6ffebd4d86c8.png)

    그럼 이렇게 뻗어 나갈텐데 dfs가 깊이 탐색으로 아래처럼 모든 가지를 탐색하게 된다.
    * A → B → C → D 
    * A → B → C → A
    * A → B → C → B
    * ...

    이 방식을 2210번 문제에 적용해보쟈!

<br/>


1. 모든 위치가 시작점이므로 모든 (i, j)에 대해 dfs를 돌려주자.
    ```java
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            dfs(i, j, 1 , map[i][j]);
        }
    }
    ```

2. 상하좌우 중 하나의 위치로 이동하자
    ```java
     for (int i = 0; i < 4; i++) {            
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
        dfs(nx, ny, cnt+1, s+map[nx][ny]);    
    }

3. 이동을 6번 했을 경우, set에 저장하고 종료한다.
    ```java
    if (cnt == 6) {
        set.add(s);
        return;
    }
    ```

4. 끝!
