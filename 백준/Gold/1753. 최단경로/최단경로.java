import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 가중치가 있는 그래프를 담기 위한 클래스 별도 구현하기 (다익스트라에 활용)
class Edge implements Comparable<Edge> {
	int v, w;
	
	Edge(int v, int w) {
		this.v = v;
		this.w = w;
	}
	
	// 가중치가 작은 것이 우선순위 큐에 먼저 들어가게 하도록 설정
	public int compareTo(Edge e) {
		if (this.w > e.w)
			return 1;
		else
			return -1;
	}
}

public class Main { 	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());	// 정점의 개수
		int E = Integer.parseInt(st.nextToken());	// 간선의 개수
		int K = Integer.parseInt(br.readLine());	// 시작 정점의 번호
		ArrayList<Edge>[] graph = new ArrayList[V + 1];
		boolean[] visited = new boolean[V + 1];
		int[] distance = new int[V + 1];	// 최단 거리 배열
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();	// 다익스트라에 활용할 우선순위 큐
		
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i <= V; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		// 가중치가 있는 인접 리스트 초기화하기
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());	// 가중치
			graph[u].add(new Edge(v, w));
		}
		
		// 출발 노드는 우선순위 큐에 넣고 시작하기
		// 자동으로 거리가 최소인 노드를 선택하게 함
		pq.add(new Edge(K, 0));	// K를 시작점으로 설정하기
		distance[K] = 0;
		
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			int now_v = now.v;
			
			// 이미 방문한 적이 있는 노드는 다시 큐에 넣지 않음
			if (visited[now_v]) 
				continue;
			visited[now_v] = true;
			
			for (int i = 0; i < graph[now_v].size(); i++) {
				Edge next = graph[now_v].get(i);
				int next_v = next.v;
				int next_w = next.w;
				
				// 최소 거리로 업데이트하기
				if (distance[next_v] > distance[now_v] + next_w) {
					distance[next_v] = distance[now_v] + next_w;
					pq.add(new Edge(next_v, distance[next_v]));
				}
			}
		}
		
		// 거리 배열 출력하기
		for (int i = 1; i <= V; i++) {
			if (visited[i])
				System.out.println(distance[i]);
			else
				System.out.println("INF");
		}
	}
}