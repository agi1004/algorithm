import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Integer>[] tree;
	static boolean[] visited;
	static int remove;		// 지울 노드의 번호
	static int reaf = 0;	// 리프 노드의 개수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();		// 노드의 개수		
		tree = new ArrayList[N];
		visited = new boolean[N];
		int root = 0;	// 루트 노드
		
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int child = 0; child < N; child++) {
			int parent = sc.nextInt();
			if (parent != -1) {	// 부모 노드가 -1이 아닐 경우에만 트리에 넣기
				tree[child].add(parent);
				tree[parent].add(child);
			} else {	
				root = child;	// 부모 노드가 -1이면 자식 노드를 루트 노드로 설정
			}
		}
		
		remove = sc.nextInt();	// 지울 노드의 번호
		
		if (remove == root) {	// 지울 노드의 번호가 루트 노드라면
			reaf = 0;			// 모든 노드가 사라지므로 리프 노드는 0
		} else {
			DFS(root);			
		}
		
		System.out.println(reaf);
		
	}

	public static void DFS(int node) {
		visited[node] = true;
		int child = 0;	// 자식 노드의 수
		
		for (int next : tree[node]) {
			// 인접 노드에 방문을 하지 않았고, 지울 노드의 번호도 아니라면 방문할 수 있음
			if (!visited[next] && next != remove) {
				child++;	// "방문 가능 = 자식 노드" 이므로 자식노드 1 증가
				DFS(next);	// 계속 DFS 진행
			} 
		}
		
		if (child == 0) {	// 인접 노드를 다 돌았는데도 자식 노드가 하나도 없다면
			reaf++;			// 해당 노드는 리프 노드이므로 1 증가
		}
	}
}