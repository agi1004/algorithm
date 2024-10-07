import java.io.*;
import java.util.*;

public class Main {
	static List<Node>[] graph;
	static int[] cost;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());	// 도시 개수
		int m = Integer.parseInt(br.readLine());	// 버스 개수
		graph = new ArrayList[n + 1];
		cost = new int[n + 1];
		visited = new boolean[n + 1];
		
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
			cost[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, cost, null));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		Node node = dijkstra(start, end);
		
		sb.append(cost[end] + "\n");
		
		int count = 0;
		List<Integer> path = new ArrayList<>();
		
		while (node != null) {
			count++;
			path.add(node.v);
			node = node.before;
		}
		
		sb.append(count + "\n");
		
		for (int i = count - 1; i >= 0; i--) {
			sb.append(path.get(i) + " ");
		}
		
		System.out.println(sb);
	}
	
	public static Node dijkstra(int start, int end) {
		Queue<Node> pq = new PriorityQueue<>();
		
		cost[start] = 0;
		pq.add(new Node(start, 0, null));
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (now.v == end) {
				return now;
			}
			
			if (visited[now.v]) {
				continue;
			}
			
			visited[now.v] = true;
			
			for (Node next : graph[now.v]) {
				if (cost[next.v] > cost[now.v] + next.w) {
					cost[next.v] = cost[now.v] + next.w;
					pq.add(new Node(next.v, cost[next.v], now));
				}
			}
		}
		
		return null;
	}
	
	static class Node implements Comparable<Node> {
		int v, w;
		Node before;
		
		Node(int v, int w, Node before) {
			this.v = v;
			this.w = w;
			this.before = before;
		}
		
		public int compareTo(Node node) {
			return this.w - node.w;
		}
	}
}