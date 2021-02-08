# [백준 1992번: 쿼드트리 🔢](https://www.acmicpc.net/problem/1992)
## sudo ✍  
1. 배열의 앞 뒤로 괄호가 들어간다.
2. 배열 내부의 값이 모두 일치하면 그 값을 return한다.
3. 그렇지 않다면 4등분을 해서 각 배열의 내부를 다시 본다. (재귀 스멜 풀풀~);
4. 반복  

    ![image](https://user-images.githubusercontent.com/36289638/107187316-27de1000-6a29-11eb-9816-68e3423b00d7.png)


<br/>

## algorithm 💻
1. 배열의 앞 뒤로 괄호가 들어간다.
    ```java
    sb.append('(');
    // 배열 내부 계산
    sb.append(')');
    ```

2. 배열 내부의 값이 모두 일치하면 그 값을 return  
    배열의 크기가 1이면 내부 값이 일치하니까 빠른 계산을 위해 조건에 넣어줌  
    ```java
    if (arr.length == 1 || check(arr)) {
        sb.append(arr[0][0]);
        return;
    }
    ```

3. 그렇지 않다면 4등분을 해서 각 배열의 내부를 다시 본다.  
![image](https://user-images.githubusercontent.com/36289638/107187963-2b25cb80-6a2a-11eb-9001-ba8d71e7f771.png)

    ```java
     for (int i = 0; i < n/2; i++) {
        for (int j = 0; j < n/2; j++) {
            leftTop[i][j] = arr[i][j];
            rightTop[i][j] = arr[i][j+n/2];
            leftBottom[i][j] = arr[i+n/2][j];
            rightBottom[i][j] = arr[i+n/2][j+n/2];
        }
    }

    ...

    quadTree(leftTop, n/2);
    quadTree(rightTop, n/2);
    quadTree(leftBottom, n/2);
    quadTree(rightBottom, n/2);

    ...
    ```

4. 끝!

<br/>

이 문제는 4등분하는게 너무 귀찮은 문제였다...😥
