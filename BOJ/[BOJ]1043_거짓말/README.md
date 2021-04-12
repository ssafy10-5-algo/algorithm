# [백준 1043번: 거짓말](https://www.acmicpc.net/problem/1043)

### sudo ✍
크크.. 오랜만에 원샷원킬 문제 🔪  

1. 진실을 아는 사람들이 참석하는 파티를 체크한다.
2. 위의 파티에 참석한 모든 사람들을 진실을 아는 사람이라고 체크한다.
3. 1,2번을 반복한다.

끝!

<br>

### algorithm 💻
0. n번째 사람이 참석하는 파티  
m번째 파티에 참석하는 사람들  
위와같이 두 개의 자료구조를 생성
```java
List<Integer>[] people = new List[N+1];
List<Integer>[] party = new List[M+1];
```

1. 진실을 아는 사람들이 참석하는 파티를 체크한다.
2. 위의 파티에 참석한 모든 사람을 진실을 아는 사람이라고 체크한다.
```java
while(!q.isEmpty()) {
    int knowTruthPerson = q.poll();
    for (int i = 0; i < people[knowTruthPerson].size(); i++) {
        // 1. 진실을 아는 사람들이 참석하는 파티 체크
        int partyNum = people[knowTruthPerson].get(i);  

        if (!isPossibleParty[partyNum]) {
            for (int j = 0; j < party[partyNum].size(); j++) {
                // 2. 위의 파티에 참석한 모든 사람을 진실을 아는 사람이라고 체크
                int personInParty = party[partyNum].get(j);     
                if (!isKnowTruth[personInParty]) {
                    isKnowTruth[personInParty] = true;
                    q.add(personInParty);
                }
            }
        isPossibleParty[partyNum] = true;
        }
    }
}
```

끝!