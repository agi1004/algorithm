import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[] order;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);	// 정점의 수
		int M = Integer.parseInt(s[1]);	// 간선의 수
		int R = Integer.parseInt(s[2]);	// 시작 정점
		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		order = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			graph[u].add(v);
			graph[v].add(u);
		}
		
		BFS(R);
		
		for (int i = 1; i <= N; i++) {
			System.out.println(order[i]);
		}
	}
	
	public static void BFS(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;
		
		int count = 0;
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			order[now] = ++count;
			
			Collections.sort(graph[now]);
			
			for (int next : graph[now]) {
				if (!visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}
			}
		}
	}
}