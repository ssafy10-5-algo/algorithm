import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1715 {
    public static void main(String[] args) throws Exception{
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 우선순위 큐로 작은 묶음들끼리 합치기
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < N; i++) q.add(Integer.parseInt(br.readLine()));
        int sum = 0;
        
        // 한 묶음이 남을 때 까지 합치기
        while (q.size() != 1) {
            int tmp = q.poll() + q.poll();
            q.add(tmp);
            sum += tmp;
        }
        
        // 답 도출
        System.out.println(sum);
    }
}
