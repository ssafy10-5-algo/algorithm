# [백준 15685번: 드래곤 커브 🐉](https://www.acmicpc.net/problem/15685)

## sudo ✍

처음에는 마지막 점을 기준으로 90도 회전을 시켜주면 되겠다! 라고 생각을 했다. 어림도 없지 🤪  
무슨 수학적 공식으로 해서 cosθ, sinθ 막 이런 멋진거 사용했는데 값이 이상하게 나왔다. 당연했다.  

<br>

기존 생각에 얽매이지 말고 다시 생각해보자! 하고 봤더니 생각보다 쉬운 문제였다... 다시 보니까 한시간?도 안 걸려서 푼 것 같다.  
우선, 이 문제에서 (x, y)에 대해서 일반적으로 우리가 아는 **함수 그래프와 방향이 반대**다. 그래서 나는 내가 아는 방향대로 생각했다.  

``` 
[문제 재정의]
0: x 좌표가 증가하는 방향 (→)
1: y 좌표가 감소하는 방향 (↓)
2: x 좌표가 감소하는 방향 (←)
3: y 좌표가 증가하는 방향 (↑)
```

<br>

문제를 재정의하고 세대가 증가할 때마다 각 점들이 어떻게 변하는지 봤다. 그려보니 방향에 따라 회전하는 방향도 일정함을 알게 됐다. 아래 그림은 예제 입력 1번에 있는 커브 중 하나인 ```4 2 1 3``` 값을 가지고 직접 그린 예시다.

![image](https://user-images.githubusercontent.com/36289638/113915401-b8628200-9819-11eb-9550-280a891caa54.png)  


* 커다란 까만색 점은 세대가 변경되며 새롭게 커브가 만들어지기 시작하는 지점이다.  
* 형광펜은 기존 커브가 새로운 커브로 어떻게 변경됐는지 체크하고자 칠했다.  

그려보니 각 좌표가 회전을 하게되면 어떤 방향으로 회전되는지 알 수 있었다. 또, 새로 생기는 커브가 가장 최신에 만들어진 커브부터 0세대 커브의 순으로 생성됨을 알 수 있었다.
|방향|회전 후 방향|
|-|-|
|0|1|
|1|2|
|2|3|
|3|0|

<br>

#### **아이디어**
1. 드래곤 커브를 입력받는다. 
2. 드래곤 커브를 만든다.
    1. 최신에 만들어진 드래곤 커브부터 0세대 커브까지 하나씩 가져온다.
    2. 가져온 커브 값을 마지막에 생성된 last 좌표 값에 회전하여 붙인다.
    3. 새로 생성된 선분을 list에 추가한다.
    4. last 값을 새로 갱신된 값으로 변경한다.
    5. g세대 만큼 반복한다.
3. 드래곤 커브 수인 N개 만큼 반복한다.
4. 현재 위치에서 상단, 우측, 대각선을 체크하여 네 방향이 모두 드래곤 커브 일부인 곳을 count
5. count 출력

<br>

## algorithm 💻  
1. 드래곤 커브를 입력받는다.
```java
for (int i = 0; i < N; i++) {
    StringTokenizer st = new StringTokenizer(br.readLine());
    x = Integer.parseInt(st.nextToken());   
    y = Integer.parseInt(st.nextToken());  
    d = Integer.parseInt(st.nextToken());  
    g = Integer.parseInt(st.nextToken());  

    ...
}
```

2. 드래곤 커브를 만든다.
    1. 최근에 만들어진 커브부터 가져온다.
    2. 만들어진 커브를 회전한다.
    3. 만들어지는 선분의 끝 꼭짓점 값인 last 값에 선분을 붙인다.
    4. 새로 만들어진 선분을 list에 추가한다.
    5. last 값을 갱신한다.
    6. g세대만큼 반복
```java
for (int j = 0; j < g; j++) {                   // 6. g세대만큼 반복
    for (int k = list.size()-1; k >= 0; k--) {  // 1. 최근에 만들어진 커브부터 가져온다.
        Line now = list.get(k);
        int rot_dir = rotation(now.dir);        // 2. 만들어진 커브를 회전한다.

        nx = last[0] + dx[rot_dir];             // 3. last 값에 선분을 붙인다.
        ny = last[1] + dy[rot_dir];

        list.add(new Line(last[0], last[1], nx, ny, rot_dir)); // 4. 새로 만든 선분 list에 추가한다.

        last[0] = nx;                           // 5. last값 갱신한다.
        last[1] = ny;

        ...
    }
}
```

3. 현재 위치에서 상단, 우측, 대각선을 체크하여 네 방향이 모두 드래곤 커브 일부인 곳을 count  (완전탐색)
``` java
for (int i = 0; i < 100; i++) {
    for (int j = 0; j < 100; j++) {
        boolean flag = true;
        for (int k = 0; k < 4; k++) { 
            int nx = i + cx[k];
            int ny = j + cy[k];
            
            if (!visited[nx][ny]) { // 하나라도 커브의 일부가 아닌 경우
                flag = false;
                break;
            }
        }
        if (flag) cnt++;   // 네 꼭짓점 모두 커브인 경우
    }
}

```

4. 정답 출력
``` java
System.out.println(cnt);
```