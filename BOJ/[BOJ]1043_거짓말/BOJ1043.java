import java.io.*;
import java.util.*;

public class BOJ1043 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 사람 수
        int M = Integer.parseInt(st.nextToken());   // 파티 수

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());   // 진실을 아는 사람의 수

        if (K == 0) {               // 진실을 아는 사람이 없다면 모든 파티에서 과장 가능
            System.out.println(M);
            return;
        }

        Queue<Integer> q = new ArrayDeque<>();  // 진실을 아는 사람들을 담는 큐
        for (int i = 0; i < K; i++) q.add(Integer.parseInt(st.nextToken()));

        List<Integer>[] people = new List[N+1]; // n번째 사람이 참가하는 파티들
        List<Integer>[] party = new List[M+1];  // m번째 파티에 참석하는 사람들
        for (int i = 1; i <= N; i++) people[i] = new ArrayList<>();
        for (int i = 1; i <= M; i++) party[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());   // i번째 파티에 참석하는 인원
            for (int j = 0; j < K; j++) {
                int val = Integer.parseInt(st.nextToken()); // i번째 파티에 참가하는 사람
                party[i+1].add(val);    // i번째 파티에 val이 참가
                people[val].add(i+1);   // val은 i번째 파티에 참가
            }
        }

        boolean[] isKnowTruth = new boolean[N+1];       // n번째 사람이 진실을 알고 있는지 유무
        boolean[] isPossibleParty = new boolean[M+1];   // m번째 파티에 진실을 알고 있는 사람이 있는지 유무
        isKnowTruth[0] = isPossibleParty[0] = true;
        isKnowTruth[q.peek()] = true;

        while(!q.isEmpty()) {
            int knowTruthPerson = q.poll();
            for (int i = 0; i < people[knowTruthPerson].size(); i++) {
                int partyNum = people[knowTruthPerson].get(i);          // 진실을 아는 사람이 참가하는 파티
                if (!isPossibleParty[partyNum]) {
                    for (int j = 0; j < party[partyNum].size(); j++) {
                        int personInParty = party[partyNum].get(j);     // 그 파티에 참가한 모든 사람들 q에 넣기
                        if (!isKnowTruth[personInParty]) {
                            isKnowTruth[personInParty] = true;
                            q.add(personInParty);
                        }
                    }
                    isPossibleParty[partyNum] = true;
                }
            }
        }

        int ans = 0;
        for (boolean check : isPossibleParty) if (!check) ans++;    // 진실을 아는 사람이 없는 파티 갯수 count
        System.out.println(ans);
    }
}