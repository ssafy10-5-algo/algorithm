# [백준 3184: 양🐑](https://www.acmicpc.net/problem/3184)  

## 내가 생각한 알고리즘 💭
1. #가 아닌 곳은 모두 빈 땅이다!
2. 연결된 땅끼리는 queue에 넣어서 따로 저장하자!
3. 연결된 땅에서의 늑대와 양의 수를 세자!
    * 늑대 < 양, 늑대를 쫓아낸다.
    * 늑대 >= 양, 양을 잡아먹는다.
4. 2, 3번을 반복한다.

<br/>

## 알고리즘 풀이 💡
1. #가 아닌곳은 모두 빈 땅  
    빈 땅은 입력을 받으며 field라는 큐에 저장해주었다.
    ```java
    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
            if (land[i][j] != '#') {           
                field.add(new int[]{i, j});
            }
        }
    }
    ```

<br/>

2. 연결된 땅끼리는 큐에 넣어서 따로 저장하자!
3. 연결된 땅에서의 늑대와 양의 수를 세자!
    * 코드
        ```java
        while (!town.isEmpty()) {               
            int[] now = town.poll();
            int r = now[0]; int c = now[1];
            visited[r][c] = true;

            for (int i = 0; i < 4; i++) {      
                int nx = r + dx[i];
                int ny = c + dy[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny] || land[nx][ny] == '#') continue;

                if (land[nx][ny] == 'v') wolf++;
                else if (land[nx][ny] == 'o') sheep++;
                visited[nx][ny] = true;
                town.add(new int[]{nx, ny});
                }
            }
        }
        ```
    
    * 코드를 그림으로 도식화 (3번 땅으로 예시)
        * 울타리로 공간이 분리된다.  
        아래 그림의 공간은 1, 2, 3번으로 총 3 공간이다.

            <img src = "https://user-images.githubusercontent.com/36289638/106361737-afa98780-6362-11eb-9406-7800da0146aa.png" width="400px">

        1. 우선 field 큐에는 1, 2, 3번 공간의 모든 땅 정보가 존재한다.
        2. field 값을 하나 꺼내어 town이라는 큐에 넣는다. 그 값을 (2, 2) 지점이라고 하자.
        3. town에서 땅의 정보를 꺼내어 그 땅과 연결(상하좌우 체크)되었고 방문하지 않은 땅을 town에 삽입한다.

            ![info](https://user-images.githubusercontent.com/36289638/106362578-fac59980-6366-11eb-86fb-297c710c5193.jpg)

   * 한 공간에 존재하는 양과 늑대의 수를 비교하여 최종 양 또는 늑대 수에 반영한다.
   ```java
   if (wolf < sheep) sheepCount += sheep;
   else wolfCnt += wolf;
   ```

4. 2, 3번을 반복한다.


## 느낀 점 😁
일반적으로 bfs의 패턴은 방향 설정과 방문 여부를 판단해 queue에 넣는 방식이다.  
bfs와 다른 알고리즘과 합쳐진 골드 레벨 이상의 문제를 풀어봐야겠다


    
