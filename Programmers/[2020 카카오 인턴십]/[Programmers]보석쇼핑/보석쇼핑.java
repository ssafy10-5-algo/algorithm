import java.util.*;

class 보석쇼핑 {
    Set<String> set = new HashSet<>();
    HashMap<String, Integer> map = new HashMap<>();
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        set.addAll(Arrays.asList(gems));                // 보석 종류 count
        if (map.size() == 1) return new int[]{1, 1};

        int start = 0;
        int len = Integer.MAX_VALUE;

        for (int end = 0, size = gems.length; end < size; end++) {
            // 1. map에 보석을 하나씩 넣는다.
            if (map.containsKey(gems[end])) map.put(gems[end], map.get(gems[end]) + 1);
            else map.put(gems[end], 1);

            // 2. map에 모든 보석이 다 들어있다면 시작 지점을 움직인다.
            while (set.size() == map.size()) {
                // 2-1. 현재 구간이 앞에서 나왔던 구간보다 짧다면 갱신
                if (end - start < len) {
                    len = end - start;
                    answer[0] = start + 1;
                    answer[1] = end + 1;
                }
                // 2-2. 맨 앞의 보석을 제거한다.
                map.put(gems[start], map.get(gems[start]) - 1);
                if (map.get(gems[start]) == 0) map.remove(gems[start]);
                start++;
            }
        }
        return answer;
    }
}