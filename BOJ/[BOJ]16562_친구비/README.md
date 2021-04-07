# [백준 16562번: 친구비 💵](https://www.acmicpc.net/problem/16562)

### sudo ✍
최근 union-find 알고리즘을 배워서 그걸로 호다닥! 5분 컷! 했다 😁😁  
매일 이렇게 아는 알고리즘만 나오면 을매나 좋게요!  

<br/>

#### 아이디어  
1. 연결된 친구인지 조사해본다.
2. 연결되지 않았다면 서로 연결해준다.
3. 연결된 친구들의 최소 비용을 갱신한다.
4. 서로 친구인 사람 무리들의 친구 비용 합을 구한다.
5. 친구 비용 합이 준석이 돈 보다 크면 "Oh no" 출력 아니면 비용의 합 출력

<br/>

### algorithm 💻  
1. 연결된 친구인지 조사
2. 연결되지 않았다면 서로 연결해준다.
    ```java
    int rx = find(x);
    int ry = find(y);

    if (rx == ry) return;
    if (rx > ry) friends[ry] = rx;
    else friends[rx] = ry;
    ```

3. 연결된 친구들의 최소 비용을 갱신한다.
    ```java
    int rx = find(x);
    int ry = find(y);

    int min1 = Math.min(fee[rx], fee[ry]);
    int min2 = Math.min(fee[x], fee[y]);

    fee[rx] = Math.min(min1, min2);
    fee[ry] = fee[rx];
    ```

4. 각 친구 무리의 비용 합을 구한다.  
    ```java
    for (int i = 1; i <= N; i++) {
        if (friends[i] == i) sum += fee[i];
    }
    ```

5. 답 출력
    준석이 돈보다 크면 "Oh no" 출력 
    준석이 돈보다 작거나 같으면 비용의 합 출력


### 추가! 🌵  
Union-Find 개념은 티스토리에 따로 정리해서 올려야겠다! 그리고 리드미에 추가해야징!