# [백준 1753번: 최단경로 🗺](https://www.acmicpc.net/problem/1753)

### sudo ✍  
이름부터 최단경로 문제라서 다익스트라 알고리즘을 사용했다.  
처음에는 **메모리 초과**가 떠서 엥? 하고 봤더니 V가 200,000개 였다. 이중 배열로 처리하니 당연히 시간 초과가 날 수 밖에... 😇😇  

아무튼 ArrayList[]를 사용했더니 성공은 했다. [배열을 사용한 다익스트라 알고리즘](https://colorscripter.com/s/Dm3Qj9J) 성공하긴 했는데 2140ms 시간이 떠서 동공지진 왔다. 그래서 우선순위 큐를 사용해서 다시 풀었더니 812ms가 나왔다.

<br>

#### 아이디어  
1. ArrayList[] graph로 각 지점에 연결된 다른 지점과 가중치를 받아온다.
2. 시작점 K와 K-K 거리인 0을 pq에 넣는다.
3. pq의 사이즈가 0이 될 때까지 탐색을 한다.
    * pq에서 값을 꺼낸 값이 root
    * 이미 방문한 적이 있는 root라면 continue,  
        아니면 방문 true로 변경 후, 아래 과정 수행한다.
    * root과 연결된 graph[root]의 값을 가져온다.
    * K-root-graph[root].get(j) 사이의 거리가 갱신된다면 pq에 갱신된 값을 넣어준다.


<br>

### algorithm 💻
1. ArrayList[] graph로 각 지점에 연결된 다른 지점과 가중치를 받아온다.
```java
for (int i = 0; i < E; i++) {
    st = new StringTokenizer(br.readLine());
    int u = Integer.parseInt(st.nextToken());
    int v = Integer.parseInt(st.nextToken());
    int w = Integer.parseInt(st.nextToken());
    graph[u].add(new int[]{v, w});
}
```

2. 시작점 K와 K-K 거리인 0을 pq에 넣는다.
    pq의 정렬 조건은 가중치 값이 작은 순
```java
PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
q.add(new int[]{K, 0});
```

3. pq의 사이즈가 0이 될 때까지 탐색을 한다.
    * root과 연결된 graph[root]의 값을 가져온다.
    * K-root-graph[root].get(j) 사이의 거리가 갱신된다면 pq에 갱신된 값을 넣어준다.  

```java
while(!q.isEmpty()) {
    int[] now = q.poll();

    if (visited[now[0]]) continue;  // 방문한 적 있으면 pass
    visited[now[0]] = true;         // 없으면 이제 방문한거니까 체크

    for (int i = 0, size = graph[now[0]].size(); i < size; i++) {
        int[] next = graph[now[0]].get(i);  // 현재 root와 연결되어 있는 지점

        // minDist값이 갱신 된다면 pq에 next값과 K-next 거리인 minDist[next] 값을 넣어준다.
        if (minDist[next[0]] > minDist[now[0]] + next[1]) { 
            minDist[next[0]] = minDist[now[0]] + next[1];
            q.add(new int[]{next[0], minDist[next[0]]});
        }
    }
}
```