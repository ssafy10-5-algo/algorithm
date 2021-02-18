# [백준 1208번: 부분수열의 합 2 ➕](https://www.acmicpc.net/problem/1208)

## sudo ✍
으 이문제 싫다. 아니 그냥 범위가 큰 문제는 싫다.  
이분탐색이라고 해서 뭘 이분탐색해야하나 엄청 고민했다. 머리에 쥐가 날 것 같아서 다른 분의 아이디어만 봤다. N의 최대값이 40이니까 이걸 통채로 돌리면 메모리, 시간 초과가 나니 **20개씩 나눠서 저장한 뒤 이분탐색**해야 한다고 했다. 😒😒  

<br/>

#### 성공한 sudo
1. 배열의 반 나눠서 부분수열의 합을 구한다.
2. 반 씩 나눈 부분수열의 합을 투포인터로 구한다.
3. 합이 S인 경우를 체크하며 답을 찾는다.
4. 끝!

<br/>

## algorithm 💻
1. 배열의 반 씩 부분수열의 합을 구한다.
    ```java
    subSet(0, half, 0, list1);
    subSet(half, N, 0, list2);
    ```

    * 부분수열의 합 메소드
    ```java
     static void subSet(int index, int end, long sum, List<Long> list) {
        if (index == end) {     
            list.add(sum);
            return;
        }
        subSet(index + 1, end, sum + arr[index], list); 
        subSet( index + 1, end, sum, list);            
    }
    ```

2. list1, list2의 합이 S인 것을 찾는다. (투포인터 이용)
    ```java
    while (left < list1.size() && right >= 0) {
        ...

        if (sum == S) {
            long countL = list1.get(left)값과 일치하는 개수 
            long countR = list2.get(right)값과 일치하는 개수
            ans += (countL * countR);
        }
        else if (sum < S) left++;
        else right--;
    }
    ```

3. 끝!

<br/>

## 주의할 점 ⛔  
역시나 **범위**다. int로 했다가 틀렸습니다가 뜨길래 아 이건 범위문제겠다 해서 그냥 전부다 long형으로 때려박았더니 성공했다. 알고리즘에서는 변수 다 static에 long형으로 때려박는게 최곤거 같다.

<br/>

## 배운 점 🌵  
나눈다는 생각을 왜 못했을까? 바보다. **반으로 나눠서 저장한 다음 계산한다!** 잊지말아야지 ㅠㅠ  
나는 930ms 정도의 속도가 나왔는데 다른 사람의 코드를 보니 300ms 쯤 나온 코드가 있었다. 보니까 조합을 재귀가 아닌 반복문+queue로 구현하셨다. 미친거같다. 나도 따라 해봐야겠다.