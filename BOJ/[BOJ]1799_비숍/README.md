# [백준 1799번: 비숍 🧙‍♂](https://www.acmicpc.net/problem/1799)

### sudo ✍ 
약간 N-queen 문제 같았다. 이 문제는 고려할게 대각선밖에 없었다.  
**서로 영향을 끼치는 부분**을 그려보니 체스판 처럼 **검은 부분과 흰 부분**이었다.   
저 부분에 대해서 부분 수열 구하는 것처럼 놓고 안놓고 하면 되지 않을까? 라는 아이디어가 떠올랐다!

<img src="https://user-images.githubusercontent.com/36289638/110507868-ca320600-8143-11eb-92e7-f06c73c03ac6.png" style="width: 300px;">

<br/><br/>

#### 아이디어 💡  
1. 두 부분으로 나눈다. 
2. 검은 부분에서 최대 놓을 수 있는 비숍의 갯수를 구한다.
    * row값이 짝수면 col값은 0부터 시작
    * row값이 홀수면 col값은 1부터 시작
3. 흰 부분에서 최대 놓을 수 있는 비숍의 갯수를 구한다.
    * row값이 짝수면 col값은 1부터 시작
    * row값이 홀수면 col값은 0부터 시작
4. 최대 검은 부분 놓을 수 있는 값 + 최대 흰 부분 놓을 수 있는 값

<br/>


### algorithm 💻  

1. 두 부분으로 나눈다. 
    ```java
    dfs(0, 0, true, 0);
    dfs(0, 1, false, 0);
    ```

2. 검은 부분에서 최대 놓을 수 있는 비숍의 갯수를 구한다.
    * row값이 짝수면 col값은 0부터 시작
    * row값이 홀수면 col값은 1부터 시작

    ```java
    if (y >= N) y = x%2==0 ? 0 : 1;
    if (x >= N) black = Math.max(black, count);

    ... 

    if (isPossible(x, y) && !visited[x][y] && map[x][y] == 1) {    
        visited[x][y] = true;
        dfs(x, y+2, isBlack, count+1);  // 비숍을 놓는 경우
        visited[x][y] = false;
    }
    dfs(x, y+2, isBlack, count);    // 비숍을 놓지 않는 경우
    ```

3. 흰 부분에서 최대 놓을 수 있는 비숍의 갯수를 구한다.
    * row값이 짝수면 col값은 1부터 시작
    * row값이 홀수면 col값은 0부터 시작

    ```java
    if (y >= N) y = x%2==0 ? 1 : 0;
    if (x >= N) white = Math.max(white, count);

    ... 

    // 비숍 놓는 코드는 위와 동일
    ```


4. 최대 검은 부분 놓을 수 있는 값 + 최대 흰 부분 놓을 수 있는 값
    ```java
    System.out.println(black + white);
    ```


<br/>

### 느낀점 🌵  
isBlack을 둘 다 true로 주고 답 왜 안 나오냐고 소리없는 아우성을 질렀다. 느낀점은 내가 쓴 코드 똑디 잘보자... 은근 저런 사소한 것들이 눈에 잘 안보인단 말이지... 잘보고 처음부터 작성을 잘하자 ...^^^^ 😊😊😊😊