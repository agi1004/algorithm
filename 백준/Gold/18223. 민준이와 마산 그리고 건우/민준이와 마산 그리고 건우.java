import java.io.*;
import java.util.*;

public class Main {
	static int V;
	static List<Node>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		graph = new ArrayList[V + 1];
		
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}
		
		if (dijkstra(1, P) + dijkstra(P, V) == dijkstra(1, V)) {
			System.out.println("SAVE HIM");
		} else {
			System.out.println("GOOD BYE");
		}
	}
	
	public static int dijkstra(int start, int end) {
		Queue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		int[] dist = new int[V + 1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		pq.add(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (visited[now.v]) {
				continue;
			}
			
			visited[now.v] = true;
			
			for (Node next : graph[now.v]) {
				if (dist[next.v] > dist[now.v] + next.w) {
					dist[next.v] = dist[now.v] + next.w;
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
		
		return dist[end];
	}
	
	static class Node implements Comparable<Node> {
		int v, w;
		
		Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
}