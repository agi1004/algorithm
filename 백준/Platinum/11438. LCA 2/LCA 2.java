import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] tree;
	static boolean[] visited;
	static Nxde[] node;
	static int[][] parent;	
	static int Kmax = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());	// 노드의 개수
		tree = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		node = new Nxde[N + 1];			// 각 노드의 첫번째 부모 노드, 깊이가 저장된 배열
		Kmax = (int)(Math.log(N) / Math.log(2));
		parent = new int[Kmax + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
			node[i] = new Nxde(0, 1);
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		
		BFS(1);
		
//		int maxDepth = node[N].depth;	// 트리의 최대 깊이 = 맨 마지막 노드의 깊이
//		
//		// "2^K < 트리의 최대 깊이"를 만족하는 K의 최댓값 구하기
//		while (true) {
//			// Math.pow(2, Kmax)는 연산 속도가 느리므로 1 << Kmax (비트 연산) 사용
//			if ((1 << Kmax) >= maxDepth) {
//				Kmax--;
//				break;
//			}
//			Kmax++;
//		}
//		
//		parent = new int[Kmax + 1][N + 1];		// 각 노드의 2^K번째 부모 노드 배열
		
		for (int i = 1; i <= N; i++) {
			parent[0][i] = node[i].parent;
		}
		
		for (int k = 1; k <= Kmax; k++) {
			for (int n = 1; n <= N; n++) {
				parent[k][n] = parent[k - 1][parent[k - 1][n]];
			}
		}
				
		int M = Integer.parseInt(br.readLine());	// 가장 가까운 공통 조상을 알고싶은 쌍의 개수
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(LCA(a, b) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void BFS(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		visited[start] = true;
		node[start].parent = 0;
		node[start].depth = 1;
		
		while (!queue.isEmpty()) {
			int parent = queue.poll();
			
			for (int child : tree[parent]) {
				if (!visited[child]) {
					queue.add(child);
					visited[child] = true;
					node[child].parent = parent;
					node[child].depth = node[parent].depth + 1;
				}
			}
		}
	}
	
	public static int LCA(int a, int b) {
		// a 노드의 깊이가 무조건 더 크다는 전제로 코드를 작성할 것이기 때문에
		// 만약 b 노드의 깊이가 더 크다면 a와 b의 값을 변경할 것임
		if (node[a].depth < node[b].depth) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		// 1. 두 노드의 깊이가 다르다면 깊이를 같게 만들어주기
		if (node[a].depth != node[b].depth) {
			int depthDiff = node[a].depth - node[b].depth;	// 두 노드의 깊이 차이
			
			// "2^k == 두 노드의 깊이 차이"가 되는 k값 찾기
			int k = 0;
			// Math.pow(2, k)는 연산 속도가 느리므로 1 << k (비트 연산) 사용
			while ((1 << k) <= depthDiff) {
	            if ((depthDiff & (1 << k)) != 0) {
	                a = parent[k][a];    // a 노드의 2^k(두 노드의 깊이 차이)번째 부모 노드
	            }
	            k++;
	        }
		}
		
		// 2. 최소 공통 조상 찾기
		
		// K를 1씩 줄여가면서 두 노드의 부모 노드가 서로 달라지는 값 찾기
		// 두 노드의 부모 노드가 서로 다르다면 두 노드를 부모 노드로 교체하면서 계속 올라가기
		for (int k = Kmax; k >= 0; k--) {
			if (parent[k][a] != parent[k][b]) {
				a = parent[k][a];
				b = parent[k][b];
			}
		}
	
		if (a == b) {	// 부모 노드가 같다면 
			return a;				// 해당 노드가 최소 공통 조상
		} else {		// 부모 노드가 다르다면
			return parent[0][a];	// 두 노드의 부모 노드가 최소 공통 조상
		}	
	}
}

class Nxde {
	int parent, depth;
	
	Nxde(int parent, int depth) {
		this.parent = parent;
		this.depth = depth;
	}
}