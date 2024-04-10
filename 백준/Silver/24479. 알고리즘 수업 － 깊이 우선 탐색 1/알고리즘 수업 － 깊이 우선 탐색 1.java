import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[] order;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 정점의 수
		int M = Integer.parseInt(st.nextToken());	// 간선의 수
		int R = Integer.parseInt(st.nextToken());	// 시작 정점
		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		order = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);	// 양방향
			graph[v].add(u);	
		}
		
		DFS(R);
		
		for (int i = 1; i <= N; i++) {
			System.out.println(order[i]);
		}
	}
	
	public static void DFS(int node) {
		visited[node] = true;
		order[node] = ++count;
		
		Collections.sort(graph[node]);
		
		for (int next : graph[node]) {
			if (!visited[next]) {
				DFS(next);
			}
		}
	}
}