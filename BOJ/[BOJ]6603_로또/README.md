# [백준 6603번: 로또 🤹](https://www.acmicpc.net/problem/6603)

## sudo✍
케이스마다 수를 고르는 모든 방법을 사전 순으로 출력한다고 명시되어 있다. 재귀로 돌리면 순차적으로 나온다는 보장이 있다고 생각했다. 만약 배열의 값 {1, 2, 3, 4, 5} 중 3개를 선택하는 모든 sub set을 순차적으로 구하라는 문제를 손으로 재귀를 돌려보면 아래와 같을 것이다.

![image](https://user-images.githubusercontent.com/36289638/107066187-feeb2e80-6820-11eb-8c92-be3d1b8283de.png)


그래서 재귀를 돌렸다.👀

<br/>

## algorithm 💻
조합의 기본 문제 구조로 알고리즘은 이게 다 입니다...
```java
for (int i = index; i < k ; i++) {
    lotto[cnt] = S[i];              
    choose(cnt + 1, i + 1, lotto);  
}
```

재귀가 끝나는 조건
```java
if (cnt == 6) {
    ...
    return;
}
```
