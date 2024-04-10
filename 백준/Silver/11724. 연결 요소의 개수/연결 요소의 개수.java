import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	// 그래프(2차원 ArrayList)
	// 리스트를 사용하는 이유: 각 정점이 방문할 수 있는 정점들의 개수는 각 정점마다 다르기 때문
	static ArrayList<ArrayList<Integer>> graph;
	static Stack<Integer> stack;	
	static boolean[] visited;
	static int count;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		graph = new ArrayList<>();		
		stack = new Stack<>();			// DFS에 사용할 스택
		int N = sc.nextInt();	// 정점 개수
		int M = sc.nextInt();	// 간선 개수
		visited = new boolean[N + 1];	// 방문 체크 배열
		count = 0;				// 연결 요소 개수
				
		// 각 정점이 방문할 수 있는 정점들을 리스트를 만들기 위해 각 정점마다 리스트 객체 추가
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		// 방향이 없는 그래프이므로 양쪽 정점의 리스트에 값 삽입
		for (int i = 1; i <= M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
//			// DFS 탐색 순서 출력
//			for (int i = 1; i <= N; i++) {
//				System.out.print(i + " : ");
//				for (int j = 0; j < graph.get(i).size(); j++) {
//					System.out.print(graph.get(i).get(j) + " ");
//				}
//				System.out.println();
//			}
			
		dfs(1);		// DFS 수행
		
		// 방문을 하지 않은 정점에 대해 다시 DFS 수행
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				dfs(i);
			}
		}
		
		System.out.println(count);	// 연결 요소 개수 출력
	}
	
	// DFS
	public static void dfs(int n) {
		stack.push(n);		// 처음에 들어오는 값은 우선 스택에 push
		visited[n] = true;	// 방문 처리
		
		// 스택이 빌 때까지 반복
		while (!stack.isEmpty()) {
			int x = stack.pop();	// 스택에서 pop 하기
			// pop한 정점이 갖고있는 리스트를 모두 탐색
			for (int i = 0; i < graph.get(x).size(); i++) {
				int y = graph.get(x).get(i);	// pop한 정점이 갈 수 있는 정점
				if (!visited[y]) {		// 방문 처리가 되어있지 않다면
					stack.push(y);		// 스택에 push
					visited[y] = true;	// 방문 처리
				}
			}
		}
		
		count++;	// 스택이 비었으므로 연결 끊어짐. 연결 요소 1개 증가
	}
	
}