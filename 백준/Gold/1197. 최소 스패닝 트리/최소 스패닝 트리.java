import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int v1, v2, weight;
	
	Edge(int v1, int v2, int weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
	
	// 가중치가 작은 순서로 오름차순 정렬해서 우선순위 큐에 넣어야 함
	public int compareTo(Edge e) {
		if (this.weight < e.weight)
			return -1;
		else
			return 1;
	}
}

public class Main {
	static int parent[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());	// 정점의 개수
		int E = Integer.parseInt(st.nextToken());	// 간선의 개수
		// 가중치가 작은 값 우선으로 넣기 위해 우선순위 큐 사용
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();	
		parent = new int[V + 1];
		int edgeCount = 0;	// 에지는 V - 1개까지만 사용
		int result = 0;	// 최소 신장 트리(MST)의 가중치 
						// => 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치 합이 최소여야 함
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	// 정점
			int B = Integer.parseInt(st.nextToken());	// 정점
			int C = Integer.parseInt(st.nextToken());	// 가중치
			pq.add(new Edge(A, B, C));
		}
		
		// 각 정점의 부모 노드는 해당 인덱스로 초기화
		for (int i = 1; i <= V; i++) {
			parent[i] = i;	
		}
		
		// 우선순위 큐가 빌 때까지 반복
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();	// 가중치가 작은 것부터 뽑혀나옴 (우선순위 큐로 정렬했기 때문)
			
			// 정점들의 부모 노드가 같으면 연결하는 순간 사이클이 발생하므로 건너뛰기
			if (find(edge.v1) == find(edge.v2)) {
				continue;
			} else {	// 정점들의 부모 노드가 같지 않다면
				union(edge.v1, edge.v2);	// 부모 노드를 같게 만들기 (정점들을 서로 연결하기)
				result += edge.weight;		// 해당 에지의 가중치를 더해주기
			}
			
			edgeCount++;	// 에지를 사용했으므로 에지 사용 횟수 증가
			
			// 에지를 V - 1(정점의 수 - 1)개 사용했다면 모든 노드가 연결된 것이므로 반복문 탈출
			if (edgeCount == V - 1)	
				break;	
		}
		
		System.out.println(result);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) {
			parent[b] = a;
		}
	}
	
	public static int find(int x) {
		if (x == parent[x]) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	}
}