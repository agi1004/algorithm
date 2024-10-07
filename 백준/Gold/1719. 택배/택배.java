import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static List<Node>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		graph = new ArrayList[n + 1];
		
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, w, null));
			graph[b].add(new Node(a, w, null));
		}
		
		int[][] table = new int[n + 1][n + 1];
		
		for (int i = 1; i <= n; i++) {
			int[] miniTable = dijkstra(i);
			
			for (int j = 1; j <= n; j++) {
				table[i][j] = miniTable[j];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (table[i][j] == 0) {
					sb.append("- ");
				} else {
					sb.append(table[i][j] + " ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int[] dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		boolean[] visited = new boolean[n + 1];
		int[] miniTable = new int[n + 1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		pq.add(new Node(start, 0, null));
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (visited[now.v]) {
				continue;
			}
			
			visited[now.v] = true;
			
			miniTable[now.v] = getFirstNode(now);
			
			for (Node next : graph[now.v]) {
				if (dist[next.v] > dist[now.v] + next.w) {
					dist[next.v] = dist[now.v] + next.w;
					pq.add(new Node(next.v, dist[next.v], now));
				}
			}
		}
		
		return miniTable;
	}
	
	private static int getFirstNode(Node node) {
		int firstNode = 0;
		
		while (node.before != null) {
			firstNode = node.v;
			node = node.before;
		}
		
		return firstNode;
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