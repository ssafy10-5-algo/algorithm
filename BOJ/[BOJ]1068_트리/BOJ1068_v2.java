import java.io.*;
import java.util.*;

public class BOJ1068_v2 {
    static int N, disConn, root;
    static int[] children, parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());    // 트리 노드의 개수
        parent = new int[N];                    // 부모 노드 체크, i번째 노드의 부모는 parent[i]
        children = new int[N];                  // 자식 노드 개수 체크
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
            if (parent[i] == -1) root = i;  // 부모가 -1이면 루트 노드
            else children[parent[i]]++;     // 부모 노드가 아니라면 해당 부모의 자식 노드 개수 +1
        }
        disConn = Integer.parseInt(br.readLine());  // 지울 노드 번호
        if (root == disConn) System.out.println(0); // root 노드와 지울 노드가 일치한다면 0 반환
        else {
            int leaf = 0;
            children[parent[disConn]]--;        // 지울 노드 번호의 부모 자식수 -1
            for (int i = 0; i < N; i++) {
                if (children[i] == 0 && isConnect(i)) leaf++;   // 자식 노드가 0개이고 루트 노드와 연결되어 있다면 리프노드
            }
            System.out.println(leaf);
        }
    }

    static boolean isConnect(int node) {
        if (node == disConn) return false;
        if (node == root) return true;
        return isConnect(parent[node]);
    }
}
