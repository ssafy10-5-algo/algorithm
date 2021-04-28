# [Programmers: 보석쇼핑 💎](https://programmers.co.kr/learn/courses/30/lessons/67258)

## sudo ✍  
이 문제는 백준에 있는 [회전 초밥](https://www.acmicpc.net/problem/2531) 문제를 슬라이딩 윈도우로 풀었던 기억이 남아있어 쉽게 풀었다! 😁

<br>  

1. 사용된 보석 종류를 count
2. 보석을 순차적으로 하나씩 주머니에 넣는다.
3. 주머니에 있는 보석 종류의 개수와 총 보석의 종류 수가 같아질 때 까지 반복한다.
4. 3번 조건이 true라면
    1. 현재 구간이 최소라면 갱신
    2. 보석을 넣은 순서대로 하나씩 제거
    3. 3번 조건이 true라면 1~2 반복


<br>

## algorithm 💻 
1. 사용된 보석 종류를 count
    ```java
    set.addAll(Arrays.asList(gems));
    ```

2. 보석을 순차적으로 하나씩 주머니에 넣는다.
    ```java
    for (int end = 0, size = gems.length; end < size; end++) {
        if (map.containsKey(gems[end])) map.put(gems[end], map.get(gems[end]) + 1);
        else map.put(gems[end], 1);

        ...
    }
    ```

3. 주머니에 있는 보석 종류의 개수와 총 보석의 종류 수가 같아질 때 까지 반복한다.
4. 3번 조건이 true라면
    1. 현재 구간이 최소라면 갱신
    2. 보석을 넣은 순서대로 하나씩 제거
    3. 3번 조건이 true라면 1~2 반복

    ```java
    while (set.size() == map.size()) {
        // 4-1. 현재 구간이 최소라면 갱신
        if (end - start < len) {
            len = end - start;
            answer[0] = start + 1;
            answer[1] = end + 1;
        }

        // 4-2. 보석을 넣은 순서대로 하나씩 제거
        map.put(gems[start], map.get(gems[start]) - 1);
        if (map.get(gems[start]) == 0) map.remove(gems[start]);
        start++;
    }
    ```