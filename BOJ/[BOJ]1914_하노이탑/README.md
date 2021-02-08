# [백준 1914: 하노이 탑🗼](https://www.acmicpc.net/problem/1914)

## sudo✍
2개, 3개일 때 직접 그려봤다.

* 2개 하노이 탑

    ![image](https://user-images.githubusercontent.com/36289638/107024868-f0365480-67eb-11eb-884b-391327f709b9.png)

    
    * 1번 기둥 → 2번기둥

        ![image](https://user-images.githubusercontent.com/36289638/107024984-1c51d580-67ec-11eb-9c02-fe4323715b3b.png)

    * 1번 기둥 → 3번기둥  
    ![image](https://user-images.githubusercontent.com/36289638/107025026-2bd11e80-67ec-11eb-91ba-a6ec2eec8d10.png)

    * 2번 기둥 → 3번기둥  
    ![image](https://user-images.githubusercontent.com/36289638/107025089-44413900-67ec-11eb-8e81-7fff7dae9607.png)

<br/>

* 3개의 하노이 탑  
![image](https://user-images.githubusercontent.com/36289638/107025581-fbd64b00-67ec-11eb-890e-0b093c592113.png)

    * 1번 기둥 → 3번기둥  
    ![image](https://user-images.githubusercontent.com/36289638/107025792-3fc95000-67ed-11eb-9fa9-fcdbb156d76a.png)

    * 1번 기둥 → 2번기둥    
    ![image](https://user-images.githubusercontent.com/36289638/107025809-45bf3100-67ed-11eb-819c-7279f346dcba.png)

    * 3번 기둥 → 2번기둥  
    ![image](https://user-images.githubusercontent.com/36289638/107025837-4fe12f80-67ed-11eb-809c-afb3c43a121b.png)

    * 1번 기둥 → 3번기둥  
    ![image](https://user-images.githubusercontent.com/36289638/107025863-57083d80-67ed-11eb-86d9-eba111ff7ba0.png)

    * 2번 기둥 → 1번기둥  
    ![image](https://user-images.githubusercontent.com/36289638/107025890-5e2f4b80-67ed-11eb-9a0c-c236e6b4ace8.png)

    * 2번 기둥 → 3번기둥  
     ![image](https://user-images.githubusercontent.com/36289638/107025915-64252c80-67ed-11eb-979e-1cb415b5d40c.png)

    * 1번 기둥 → 3번기둥  
    ![image](https://user-images.githubusercontent.com/36289638/107025935-68e9e080-67ed-11eb-8c81-ddc94933f7bd.png)


<br/>

여러개가 될수록 복잡해 보이는데 함축해서 보면 2개짜리 로직을 보면 된다.  
**N개가 있다고 가정**
1. N-1개 (1번에 위치) → 2번
2. 제일 바닥 (1번에 위치) → 3번
3. N-1개 (2번에 위치) → 3번
4. 반복 😳

<br/>

## algorithm💻

출발지 → 도착지
```java
// 하노이(개수, 출발지, 임시기둥, 도착지)
hanoi (int n, int first, int second, int third)
```


1. N-1개 (1번에 위치) → 2번
    ```java
    hanoi (n-1, first, third, second)
    ```

2. 제일 바닥 (1번에 위치) → 3번
    ```java
    if (n==1) {
        sb.append(first).append(" ").append(third).append("\n");
        return;
    }
    ```

3. N-1개 (2번에 위치) → 3번
    ```java
    hanoi (n-1, second, first, third)
    ``

4. 완성!


<br/>

## 배운점 🌵  
이 문제는 하노이 탑보다 실행이 몇 번 일어나는지 구하는 부분에서 애를 먹었다.  
엄청나게 큰 값은 long 형이 아니라 String으로 처리한다는 것은 알고 있었지만 어떤 방식으로 사용하는지 몰랐다. 이번 문제로 무한대를 처리할 수 있는 **BigInteger** 라는 친구를 알게됐다.  

<br/> 

```java
// 선언
BigInteger num0 = BigInteger.ZERO;     // 0
BigInteger num1 = BigInteger.ONE;      // 1
BigInteger num10 = BigInteger.TEN;      // 10
BigInteger result = new BigInteger("넣고 싶은 숫자");

// 계산 메소드
result = result.add(new BigInteger("1"));       // 더하기
result = result.subtract(new BigInteger("1"));  // 빼기
result = result.multiply(new BigInteger("1"));  // 곱하기
result = result.devide(new BigInteger("1"));    // 나누기
```
