import java.io.*;
import java.util.*;

public class BOJ1068_v1 {
    public static void main(String[] args) throws Exception{
        //======= 입력 받기 시작 ========//

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 트리 노드 개수
        List<Integer>[] tree = new List[N];         // 트리
        for (int i = 0; i < N; i++) tree[i] = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int root = 0;
        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(st.nextToken());
            if (val == -1) root = i;    // -1은 root
            else tree[val].add(i);      // 아니면 i번째 노드의 부모가 val 이므로 tree[val]에 자식 i 추가
        }
        int disConn = Integer.parseInt(br.readLine());  // 지울 노드

        //======= 입력 받기 끝 ========//

        int count = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(root);
        if (root == disConn) {      // 끊을 노드가 루트라면 리프 노드는 없음
            System.out.println(0);
            return;
        }
        while(!q.isEmpty()) {
            int now = q.poll();
            boolean isLeaf = true;
            for (int next : tree[now]) {        // now의 자식 노드 찾기
                if (next == disConn) continue;  // next값이 disConn 이라면 pass
                q.add(next);
                isLeaf = false;
            }
            if (isLeaf) count++; // 위 루프에 들어가지 않는다는 것은 리프 노드라는 의미
        }
        System.out.println(count);  // leaf 노드 개수 반환
    }
}
