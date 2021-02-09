# [백준 1074: Z 🇿](https://www.acmicpc.net/problem/1074)
## sudo ✍
지그재그 왔다 갔다 하니까 당연히 재귀겠군 했다.  

**첫시도 (실패)**  
1. 배열에 값을 재귀로 다 넣자!
2. arr[r][c]의 값을 print하자!
3. 실패

메모리 초과가 나서 재귀가 너무 많이 도는건가? 했다.  
그래서 범위를 나눠줬다. r, c의 범위에 따라 재귀가 돌도록 해줬다.  

**두번째 시도 (실패)**  
1. 배열의 값을 r, c에 맞는 부분만 넣어준다.
2. arr[r][c]의 값을 print 하자!
3. 실패  

IDE에서 N이 15일 때 돌려보니까 재귀가 아니라 arr에서 나는거였다.  
생각해보면 Math.pow(2, 15)면 왕 큰 숫자인데 메모리 초과가 안나는게 이상하지...  
그리고 재귀가 터지면 스택오버플로우가 뜨겠지...

**세번째 시도(성공)**
1. 값을 배열이 아니라 그냥 int 값으로 들고 있자.
2. 범위를 초과하지 않는 곳만 재귀를 돈다.
3. 범위가 일치하는 구간에서 num을 print 하자!
4. 성공

<br/>

## algorithm 💻
1. 값을 int로 들고 있자!
    ```java
    static int num;     // 답이 될 상인가...👀
    ```

2. 재귀를 돈다.  
탐색은 왼쪽 상단, 오른쪽 상단, 왼쪽 하단, 위쪽 하단 순으로 진행한다.  
    ```java
    zet(x, y, half);                     
    zet(x, y + half, half);          
    zet(x + half, y, half);          
    zet(x + half, y + half, half);   
    ```


3. 구해야하는 부분은 arr[r][c]이므로 주어진 r, c가 범위에 맞지 않으면 pass  
범위가 아니면 현재 범위 뒤쪽에 나온다는 의미이므로 num 값을 증가시켜준다.
    ```java
    if (r < x || r >= x + size || c < y || c >= y + size) {
        num += Math.pow(size, 2); 
        return;
    }
    ```

4. 범위가 일치하는 구간에서 num을 print 하자!  
무식하게 사방탐색을 하나씩 해줬다.  
for문으로 해도 될 듯!
    ```java
    if (size == 2) {
        if (x == r && y == c) System.out.print(num);
        else if (x == r && y+1 == c) System.out.print(num+1);
        else if (x+1 == r && y == c) System.out.print(num+2);
        else if (x+1 == r && y+1 == c) System.out.print(num+3);
        return;
    }
    ```

<br/>

## 배운 점 🌵
헝헝 범위 메모리 이런거 너무 어렵다.  
그나저나 Math.pow(2, 14)는 메모리 초과 안나는데 생각보다 엄청 많이 저장할 수 있다. 짱이다 👍