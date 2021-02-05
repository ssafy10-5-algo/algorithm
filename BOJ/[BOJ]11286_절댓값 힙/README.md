# [백준 11286 : 절댓값 힙](https://www.acmicpc.net/problem/11286)

## 내가 생각한 알고리즘 🤔 
1. 절댓 값과 실제 값을 함께 저장한다.
2. 정렬 조건으로 정렬한다.
    * 절댓 값이 작은 순서
    * 절댓 값이 같은 경우, 실제 값이 작은 순서
3. 조건에 맞춰 값을 삽입/삭제를 한다.

<br/>

## 알고리즘 풀이 😄
1. 절댓 값과 실제 값을 저장하는 객체 생성
```{.java}
class Num {
    int real, abs;

    public Num(int real, int abs) {
        this.real = real;
        this.abs = abs;
    }
}
```

<br/>

2. 우선 순위에 따라 정렬되는 큐로 값을 받아온다.
```{.java}
PriorityQueue<Num> q = new PriorityQueue<>();
```

<br/>

3. 정렬조건을 설정한다.
    compareTo를 재정의하여 Num 객체의 정렬 조건을 바꿔준다.

```{.java}
class Num implements Comparable<Num> {

    ...

    @Override
    public int compareTo(Num o) {
        if (this.abs == o.abs) return this.real - o.real;
        return this.abs - o.abs;
    }
}
```

<br/>

4. 0이 아니면 값을 삽입  
    0인데 값이 없으면 0을 출력  
    0인데 값이 있으면 제일 작은 값 출력

```{.java}
if (x == 0) {
    if (q.isEmpty()) sb.append("0\n");
    else {
        Num num = q.poll();
        sb.append(num.real + "\n");
    }
} else {
    q.add(new Num(x, Math.abs(x)));
}
```
Num 객체의 정렬 조건을 재정의했으므로 PriorityQueue는 Num의 정렬 조건대로 최상단에 실제값과 절댓값이 가장 작은 수부터 존재할 것이다!
