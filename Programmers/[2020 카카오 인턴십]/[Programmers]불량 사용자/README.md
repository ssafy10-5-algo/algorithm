# [Programmers: 불량 사용자 🤬](https://programmers.co.kr/learn/courses/30/lessons/64064)

### sudo ✍  

1. 각 불량 사용자 아이디에 매핑되는 제재 아이디를 구한다.
2. 1번에서 구한 제재 아이디들로 조합을 구한다.
3. 조합이 완성되면 아래 사항을 체크한다.
    * 중복된 id가 존재 
    * 전에 구한 조합과 중복되는 결과
4. 가능한 조합의 수를 출력한다.

<br>


### algorithm 💻

1. 각 불량 사용자 아이디에 매핑되는 제재 아이디를 구한다.  
    ``` java
    String ban = banned_id[i];  // 불량 사용자 아이디
    String user = user_id[k];   // 사용자 아이디

    ...

    if (ban.length() != user.length()) continue;    // 아이디 길이가 다르면 제재 아이디에 포함 X
    
    for (int j = 0, size = ban.length(); j < size; j++) { // ban, user의 id를 하나씩 비교
        // * 이 아닌데 두 아이디의 알파벳이 다르면 제재 아이디 X
        if (ban.charAt(j) != '*' && ban.charAt(j) != user.charAt(j)) { 
            flag = false;
            break;
        }
    }
    if (flag) list[i].add(user);    // *을 제외한 알파벳과 아이디 길이가 일치하면 제재 아이디
    ```


2. 1번에서 구한 제재 아이디들(``list``)로 조합을 구한다.  
    ``` java
    void combination(int index, int[] comb) {
        ...
        for (int i = 0; i < list[index].size(); i++) {
            comb[index] = i;
            combination(index+1, comb);
        }
    }
    ```

3. 조합이 완성되면 아래 사항을 체크한다.
    * 중복된 id가 존재 
    ``` java
    for (int i = 0; i < comb.length; i++) set.add(list[i].get(comb[i]));
    if (set.size() != list.length) return;
    ```  

    * 전에 구한 조합과 중복되는 결과
    ```java
    if (set.size() == list.length) {
        for (String s : set) sb.append(s);
        ans.add(sb.toString());
    }
    ```  
    이 과정에서 set은 TreeSet이므로 자동으로 아이디가 정렬이 된다.  
    그러므로 순서가 바뀐 조합 ``[example] (frodo, crodo), (crodo, frodo)``은 동일한 문자열로 추출된다.

4. 가능한 조합의 수를 출력한다.
    ``` java
    return ans.size();
    ```
<br>

### 회고록 🌵
진짜 수정없이 한 번에 풀려서 기분이 굉장히 좋군 🤟  
카카오 문제를 풀어보면서 ``HashMap<String>``으로 중복을 제거하는 코드를 몇 번 사용한 경험이 있다. 그래서 여기도 적용했더니 진짜 샤랄랄라랄~ 호롤ㄹ롤롤로~ 풀려버렸지 뭐야? 중복제거용으로 ``String + HashMap``을 종종 써야겠다! 회고록 끗! 
