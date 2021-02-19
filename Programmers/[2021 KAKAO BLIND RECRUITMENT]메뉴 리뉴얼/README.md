## [Programmers : 메뉴 리뉴얼 👩‍🍳](https://programmers.co.kr/learn/courses/30/lessons/72411)

## sudo ✍  
음... 이 문제는 worst case가 orders 20개, courses의 크기는 9개라 재귀로 추가될 수 있는 코스요리를 구하고 손님들로부터 주문된 단품메뉴의 조합 인지와 몇 분의 손님에게 주문되었는지를 체크해서 답을 내면 되겠다! 라고 생각했다.  

<br/>

**첫번째 시도(시간초과) 😂**
1. 알파벳 26개로 course 개수로 이루어진 조합을 구한다.
2. 손님에게 주문된 메뉴인지 확인한다.
3. 조합 중 가장 많이 주문된 조합들만 선택한다.
4. return  

<br/>  

65점으로 4~5 문제정도가 시간초과가 났다. 그래서 최적화 할 수 있는 부분이 무엇이 있을까 고민하다가 **사용된 알파벳만 추출**해서 조합을 구하기로 했다. 그랬더니 엄청 느리지만 성공은 했다!  

<br/>

**2134912번째 시도(성공) 😊**
1. Set으로 사용된 알파벳만 추출한다.
2. Set의 알파벳으로 course 개수로 이루어진 조합 구한다.  
3. 손님이 주문한 메뉴에 포함되는지 확인한다.
    * course보다 손님의 주문이 적으면 pass 
4. 조합 중 가장 많이 주문된 조합들만 선택한다.
4. return

<br/>

## algorithm 💻  
1. Set으로 사용된 알파벳만 추출한다.
    ```java
    Set<Character> set = new TreeSet<>();
    for (String order : orders) { 
        for (int i = 0; i < order.length(); i++) {
            set.add(order.charAt(i));
        }
    }
    ```

2. 사용된 알파벳으로 course 개수로 이루어진 조합 구하기  
    실제 내 코드는 최적화 해보겠다고 String 대신 StringBuilder 사용했다.  
    그치만 그닥 큰 효과는 못봤다. 시간 차이가 안나니 String 사용하는게 더 이득👍
    ```java
     void combination(int end, int count, int index, String s) {
        if (end == count) {
            subset.add(s);
            return;
        }
        for (int i = index, size = objects.length; i < size; i++) {
            combination(end, count+1, i+1, s+objects[i]);
        }
    }
    ```

3. 손님이 주문한 메뉴에 포함되는지 확인
    ```java
    if (order.length() < end) continue; 
    if (isContain(order, sub)) {
        if (map.containsKey(sub)) map.put(sub, map.get(sub)+1);
        else map.put(sub, 1);
    }
    ```
    ```java
    boolean isContain(String order, String menu) {
        for (int i = 0; i < menu.length(); i++) {
            if (order.indexOf(menu.charAt(i)) == -1) return false;
        }
        return true;
    }
    ```

4. 조합 중 가장 많이 주문(max번)된 조합들만 선택  
    최소 2명 이상 손님으로 주문되어야하므로 max가 2 이하면 pass
    ```java
    int max = entries.get(0).getValue();
    if (max < 2) continue;
    for (Map.Entry<String, Integer> entry : entries) {
        if (entry.getValue() < max) break;
        ans.add(entry.getKey());         
    }
    ```

5. return

<br/>

## 느낀점 🌵  
다시 보니까 어렵다기보다 내 코드가 더럽다?라고 느꼈다. 여러 개념이 한꺼번에 들어가 있고 내가 HashMap, Set, Object[], ArrayList 등 자료구조를 사용해서 더 복잡했다고 느낀 것 같다. 예쁘고 간결하게 코드 작성하고 싶다 😭
