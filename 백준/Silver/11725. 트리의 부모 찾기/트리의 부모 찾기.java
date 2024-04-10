import java.util.ArrayList;
import java.util.Scanner;

public class Main {	
	static ArrayList<Integer>[] tree;
	static boolean[] visited;
	static int[] answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	// 노드의 개수
		tree = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		answer = new int[N + 1];	// 각 노드의 부모 배열
		
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		// 간선의 개수 (트리는 모든 정점이 연결되어있고, 사이클이 없으므로, 간선의 개수는 노드의 개수 - 1)
		int edge = N - 1;			
		for (int i = 0; i < edge; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			tree[u].add(v);	// 트리는 양방향
			tree[v].add(u);
		}
		
		DFS(1);	// 루트노드: 1
		
		for (int i = 2; i <= N; i++) {
			System.out.println(answer[i]);
		}
	}
	
	public static void DFS(int parent) {
		visited[parent] = true;		// 방문 체크부터 하기
		
		// parent와 연결된 노드 탐색
		for (int next : tree[parent]) {
			if (!visited[next]) {		// 방문하지 않았다면
				answer[next] = parent;	// 해당 노드의 정답 배열값에 parent 삽입	
				DFS(next);				// 해당 노드로 DFS 계속 수행
			}
		}
	}
}