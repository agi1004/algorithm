import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[] check;		// 0과 1로만 이루어진 이분 집합
	static boolean isEven;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());	// 테스트 케이스의 개수
		
		for (int i = 0; i < K; i++) {
			String[] s = br.readLine().split(" ");
			int V = Integer.parseInt(s[0]);		// 정점의 개수
			int E = Integer.parseInt(s[1]);		// 간선의 개수
			graph = new ArrayList[V + 1];
			visited = new boolean[V + 1];
			check = new int[V + 1];
			isEven = true;
			
			for (int j = 1; j <= V; j++) {
				graph[j] = new ArrayList<Integer>();
			}
			
			// 인접 리스트로 그래프 저장하기
			for (int j = 0; j < E; j++) {
				s = br.readLine().split(" ");
				int start = Integer.parseInt(s[0]);
				int end = Integer.parseInt(s[1]);
				graph[start].add(end);
				graph[end].add(start);
			}
			
			// 주어진 그래프가 1개로 연결돼있다는 보장이 없으므로 모든 노드에서 수행하기
			for (int j = 1; j <= V; j++) {
				if (isEven)
					DFS(j);
				else
					break;
			}
			
			if (isEven) 
				System.out.println("YES");
			else 
				System.out.println("NO");
		}
		
		br.close();
	}
	
	// DFS 구현하기
	public static void DFS(int start) {
		visited[start] = true;
		
		for (int now : graph[start]) {
			// 인접한 노드는 같은 집합이 아니므로 이전 노드와 다른 집합으로 처리하기
			if (!visited[now]) {
				check[now] = (check[start] + 1) % 2;	// 0 or 1
				DFS(now);
			} 
			// 이미 방문한 노드가 현재 내 노드와 같은 집합이면 이분 그래프가 아님
			else if (check[start] == check[now]) {
				isEven = false;
			}
		}
	}
}