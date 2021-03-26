# [Programmers: 자물쇠와 열쇠 🔐](https://programmers.co.kr/learn/courses/30/lessons/60059)  

## sudo ✍  
하... 3일 걸린 문제 ^^  
처음에는 아 사방탐색해서 다 돌리고 난리쳐야하나 했는데 아니었다. 이미 돌린 상태에서 사방탐색을 하는 거였다.


#### ⭐ **아이디어**
1. key를 회전해서 상하좌우로 움직인다.
2. (20, 20)을 (0, 0) 이해한다. → 20 이하의 수는 음수 좌표를 의미
3. key-lock이 서로 맞물리는지 확인한다.
4. key로 lock을 풀 수 있는 경우가 있다면 true 없다면 false 출력한다.

#### ⭐ **Key Point**
1. key를 회전 시킨 후 위치를 옮긴다. → 총 4번 회전
2. N, M의 최대 크기 20이므로 배열의 크기를 최대 60으로 잡는다.
3. 키를 60 X 60 배열에서 옮겨 다니며 lock과 맞춰본다.

<br/>

## algorithm 💻  
1. key를 회전해서 상하좌우로 움직인다.
    ```java
    for (int i = 0; i < 4; i++) {
        key = rotation(key);
        if (move(key, lock, 20, 20)) return true;   // (20, 20)을 (0, 0)으로 이해
    }
    ```

2. 상하좌우 움직임  
    ```if(check(key, lock, now[0], now[1]))``` 이 코드가 for문 안으로 가면 시간은 더 줄일 수 있을 것 같다!
    
    ```java
    public boolean move(int[][] key, int[][] lock, int x, int y) {
        ...

        while (!q.isEmpty()) {
            int[] now = q.poll();
            if (check(key, lock, now[0], now[1])) return true;  // 현재 키가 lock과 일치 하는가?

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                // 20+size 보다 크다면 lock 내부에 키의 홈이 존재하지 않는 것
                if (nx < 20-size || nx >= 20+size || ny < 20-size || ny >= 20+size || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        ...
        return false;

    ```

3. key로 lock을 풀 수 있는 경우가 있다면 true 없다면 false 출력한다.
    ```java
    public boolean check(int[][] key, int[][] lock, int x, int y) {

        // key를 60 X 60 배열로 옮김 → x, y 좌표만큼 떨어진 자리에 기존 키를 삽입
        int[][] bigKey = new int[60][60];
        for (int i = 0; i < keySize; i++) {
            System.arraycopy(key[i], 0, bigKey[i + x], y, keySize);
        }

        // (20, 20) ~ (20 + lockSize, 20+lockSize) 위치에 있는 키와 lock 을 맞춰본다.
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                if (lock[i][j] == bigKey[20+i][20+j]) return false;
            }
        }
        return true;
    ```

<br>

## 느낀점 🌵 
난 멀었다. ^^