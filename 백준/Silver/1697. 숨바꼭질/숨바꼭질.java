import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[] depth;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 시작 위치
		int K = Integer.parseInt(st.nextToken());	// 최종 위치
		graph = new ArrayList[100001];
		visited = new boolean[100001];
		depth = new int[100001];		// depth[K]: N에서 K로 가는 가장 빠른 시간
		
		for (int i = 0; i <= 100000; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 인덱스 가능 범위 하에 그래프 초기 세팅
		if (N - 1 >= 0 && N - 1 <= 100000) {
			graph[N].add(N - 1);	// N - 1을 자식 노드로 만들기
		}
		if (N + 1 <= 100000) {
			graph[N].add(N + 1);	// N + 1을 자식 노드로 만들기
		}
		if (N * 2 <= 100000) {
			graph[N].add(N * 2);	// N * 2를 자식 노드로 만들기
		}
		
		bfs(N, K);	
	}
	
	public static void bfs(int start, int target) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);		// 큐에 시작 노드 삽입
		visited[start] = true;	// 방문 체크
		
		while (!queue.isEmpty()) {
			int now = queue.poll();	// 큐에서 노드 뽑기
			
			// 큐에서 뽑은 노드가 찾는 값이라면
			if (now == target) {	
				System.out.println(depth[target]);	// 해당 노드의 depth 출력
				return;		// 탐색 종료
			}
			
			// 자식 노드 탐색
			for (int next : graph[now]) {
				// 해당 노드를 방문하지 않았다면
				if (!visited[next]) {				
					queue.add(next);				// 큐에 삽입
					visited[next] = true;			// 방문 체크
					depth[next] = depth[now] + 1;	// 부모 노드의 depth 값에서 1 증가
					
					// next - 1이 음수가 아닐 때 (음수 인덱스는 사용 불가능하므로),
					// next - 1이 100000 이하일 때 (인덱스 범위가 100000 까지이므로)
					if (next - 1 >= 0 && next - 1 <= 100000) {		
						// next - 1을 방문하지 않았을 때
						if (!visited[next - 1]) {
							graph[next].add(next - 1);	// next - 1을 자식 노드로 만들기
						}
					}
					
					// next + 1이 100000 이하일 때
					if (next + 1 <= 100000) {
						// next + 1을 방문하지 않았을 때
						if (!visited[next + 1]) {		
							graph[next].add(next + 1);	// next + 1을 자식 노드로 만들기
						}
					}
					
					// next * 2가 100000 이하일 때
					if (next * 2 <= 100000) {
						// next * 2를 방문하지 않았을 때
						if (!visited[next * 2]) {		
							graph[next].add(next * 2);	// next * 2를 자식 노드로 만들기
						}
					}
				}
			}
		}
	}
}