# [백준 19535번: ㄷㄷㄷㅈ](https://www.acmicpc.net/problem/19535)
### sudo   
![image](https://user-images.githubusercontent.com/36289638/115738648-32713a00-a3c8-11eb-93e8-b1078aa60043.png)


### algorithm  
1. ㄷ(D)
    ```java
    while (!q.isEmpty()) {
        int[] now = q.poll();
        D += ((conn[now[0]]-1) * (conn[now[1]]-1));
    }
    ```

2. ㅈ(G) 
    ```java
    for (long n : conn) {
        if (n >= 3) G += (n * (n-1) * (n-2))/6;
    }
    ```