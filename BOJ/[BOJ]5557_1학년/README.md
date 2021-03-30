# [백준 5557번: 1학년 👶](https://www.acmicpc.net/problem/5557)

### sudo ✍

1. 현재 계산한 숫자의 개수, 연산 결과값을 나타낼 수 있는 배열 선언
2. 이전 연산 결과에 현재 값 +, -한 결과 추출
3. 0이상 20이하면 배열에 반영
4. 모든 수를 사용해서 연산한 결과가 result인 것 출력

<br>

### algoritm 💻  
1. 현재 계산한 숫자의 개수, 연산 결과값을 나타낼 수 있는 배열 선언  
    맨 처음 숫자를 초기값으로 설정
    ```java
    long[][] dp = new long[N-1][21];
    dp[0][num[0]] = 1;
    ```

2. 이전 연산 결과에 현재 값 +, -한 결과 추출
3. 0이상 20이하면 배열에 반영
    ```java
    for (int i = 1; i < N - 1; i++) {
        for (int j = 0; j <= 20; j++) {
            if (dp[i-1][j] != 0) {
                int plus = j + num[i];
                int minus = j - num[i];
                if (plus >= 0 && plus <= 20) dp[i][plus] += dp[i-1][j] ;
                if (minus >= 0 && minus <= 20) dp[i][minus] += dp[i-1][j] ;
            }
        }
    }
    ```
4. 모든 수를 사용해서 연산한 결과가 result인 것 출력
    ```java
    System.out.println(dp[N-2][result]);
    ```
<br>

### 느낀 점 🌵  
 
 후.. ```2^63-1 ```개라서 long형 선언해야한다. 화난다.