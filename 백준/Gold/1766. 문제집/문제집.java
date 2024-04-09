import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
		int[] D = new int[N + 1];
		// 쉬운 문제(낮은 번호)부터 풀게 하기 위해 우선순위 큐 사용
		PriorityQueue<Integer> pq = new PriorityQueue<>();	
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph[A].add(B);
			D[B]++;		// 진입차수 1 증가
		}
		
		for (int i = 1; i <= N; i++) {
			if (D[i] == 0) {	// 해당 번호의 진입차수가 0이면
				pq.add(i);		// 우선순위 큐에 해당 번호 삽입
			}
		}
		
		while (!pq.isEmpty()) {
			int now = pq.poll();	// 우선순위 큐에서 뽑은 값을 출력
			System.out.print(now + " ");
			
			for (int next : graph[now]) {
				D[next]--;	// 그래프에 연결된 번호의 진입차수를 1 감소
				if (D[next] == 0) {	// 그래프에 연결된 번호의 진입차수가 0이 되면
					pq.add(next);	// 그 값을 우선순위 큐에 삽입
				}
			}
		}
	}
}