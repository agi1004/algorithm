import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
	int parent, depth;	// 부모 노드, 깊이
	
	Node(int parent, int depth) {
		this.parent = parent;
		this.depth = depth;
	}
}

public class Main {
	static ArrayList<Integer>[] tree;
	static boolean[] visited;
	static Node[] node;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	// 노드의 개수
		tree = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		node = new Node[N + 1];
		
		// 트리와 노드 배열을 초기화 해주기
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
			node[i] = new Node(0, 1);	// 모든 배열 값을 부모 노드: 0, 깊이: 1로 초기화		
		}
		
		// 트리의 간선 수 = 노드 수 - 1
		for (int i = 0; i < N - 1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			tree[a].add(b);		// 트리는 양방향
			tree[b].add(a);
		}
		
		BFS(1);
		
		int M = sc.nextInt();   // 가장 가까운 공통 조상을 알고싶은 쌍의 개수
		
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
						
			while (node[a].parent != node[b].parent) {				
				if (node[a].depth > node[b].depth) {
					a = node[a].parent;
				} else {
					b = node[b].parent;
				}
			}
			
			if (a == b) {
				System.out.println(a);
			} else {
				System.out.println(node[a].parent);
			}	
		}
	}
	
	public static void BFS(int start) {
		Queue<Integer> queue = new LinkedList<>();
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
}