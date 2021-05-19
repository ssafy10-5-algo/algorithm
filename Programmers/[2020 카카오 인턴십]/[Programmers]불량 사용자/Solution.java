import java.util.*;
class Solution {
    List<String>[] list;                    // banned_id[i]와 일치하는 id 리스트
    HashSet<String> ans = new HashSet<>();  // 정답 조합
    TreeSet<String> set = new TreeSet<>();  // 모든 조합
    StringBuilder sb = new StringBuilder();

    public int solution(String[] user_id, String[] banned_id) {
        int N = banned_id.length;
        list = new List[N];

        //========== banned_id[i]와 일치하는 id 찾기 ==========//
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
            String ban = banned_id[i];
            for (String user : user_id) {
                if (ban.length() != user.length()) continue;
                boolean flag = true;
                for (int j = 0, size = ban.length(); j < size; j++) {
                    if (ban.charAt(j) != '*' && ban.charAt(j) != user.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) list[i].add(user);
            }
        }
        //========== 추출 끝 ==========//

        combination(0, new int[N]); // list에서 가능한 조합 찾기
        return ans.size();
    }

    void combination(int index, int[] comb) {
        if (index == list.length) {
            set.clear();
            sb.setLength(0);
            for (int i = 0; i < comb.length; i++) set.add(list[i].get(comb[i]));    // 구한 조합 id에 중복이 있는지 체크
            if (set.size() == list.length) {        // set 크기가 list 크기와 같다는 건 내부에 중복이 없다는 의미
                for (String s : set) sb.append(s);  // set에 있는 모든 id를 이어서 하나의 문자열로 추출
                ans.add(sb.toString());             // HashMap을 사용해 중복 체크
            }
            return;
        }
        for (int i = 0; i < list[index].size(); i++) {  // 조합구하기
            comb[index] = i;
            combination(index+1, comb);
        }
    }
}