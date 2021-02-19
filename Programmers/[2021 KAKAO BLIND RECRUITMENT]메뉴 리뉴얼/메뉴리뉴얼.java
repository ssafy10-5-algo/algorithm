import java.util.*;

public class 메뉴리뉴얼 {

    // test 를 위한 main
    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        String[] solution = solution(orders, course);
        System.out.println(Arrays.toString(solution));
    }

    static HashMap<String, Integer> map;                // 조합 (추가하고 싶은 코스요리, 주문된 횟수)
    static ArrayList<String> ans = new ArrayList<>();   // 정답받을 배열
    static Set<Character> set = new TreeSet<>();        // orders에 사용된 알파벳만 저장
    static Set<String> subset;                          // course 수에 만큼의 코스요리들 저장 (조합)
    static Object[] objects;                            // set 를 array 변환하기 위한 배열
    public static String[] solution(String[] orders, int[] course) {
        for (String order : orders) {   // orders 에 존재하는 알파벳만 추출
            for (int i = 0; i < order.length(); i++) set.add(order.charAt(i));
        }
        objects = set.toArray();

        for (int end : course) {
            map = new HashMap<>();
            subset = new TreeSet<>();
            combination(end, 0, 0, new StringBuilder());    // end개로 이루어진 코스요리 구하기
            for (String sub : subset) {
                for (String order : orders) {
                    if (order.length() < end) continue;     // 주문받은 요리가 추가하고 싶은 코스요리보다 개수가 적은 경우 pass
                    if (isContain(order, sub)) {            // 추가하고 싶은 코스요리를 모두 주문한 경우 map에 저장
                        if (map.containsKey(sub)) map.put(sub, map.get(sub)+1);
                        else map.put(sub, 1);
                    }
                }
            }
            if (map.size() == 0) continue;      // map 에 아무것도 없으면 pass
            List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
            Collections.sort(entries, ((o1, o2) -> o2.getValue() - o1.getValue())); // 많이 주문된 순서대로 정렬
            int max = entries.get(0).getValue();    // 가장 많이 주문된 코스요리의 주문수
            if (max < 2) continue;                  // 최소 2명 이상 손님으로 주문되어야함
            for (Map.Entry<String, Integer> entry : entries) {
                if (entry.getValue() < max) break;
                ans.add(entry.getKey());            // 가장 많이 주문된 코스요리를 담음
            }
        }
        int size = ans.size();
        String[] answer = new String[size];         // 정답 return을 위해 배열로 전환
        for (int i=0; i<size; i++) answer[i] = ans.get(i);
        Arrays.sort(answer);

        return answer;
    }

    // end개 만큼 알파벳 조합
    static void combination(int end, int count, int index, StringBuilder sb) {
        if (end == count) {
            subset.add(sb.toString());
            return;
        }
        for (int i = index, size = objects.length; i < size; i++) {
            sb.append(objects[i]);
            combination(end, count+1, i+1, sb);
            sb.setLength(sb.length()-1);
        }
    }

    // 손님 주문한 요리에 추가하고 싶어하는 코스 요리가 모두 주문 되었는지 확인
    static boolean isContain(String order, String menu) {
        for (int i = 0; i < menu.length(); i++) {
            if (order.indexOf(menu.charAt(i)) == -1) return false;
        }
        return true;
    }
}
