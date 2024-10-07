import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static List<Node>[] graph;
	static int[] items;
	static int maxCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	// 지역 개수
		m = Integer.parseInt(st.nextToken());	// 수색 범위
		int r = Integer.parseInt(st.nextToken());	// 길 개수
		graph = new ArrayList[n + 1];
		items = new int[n + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, l));
			graph[b].add(new Node(a, l));
		}
		
		for (int i = 1; i <= n; i++) {
			maxCount = Math.max(maxCount, dijkstra(i));
		}
		
		System.out.println(maxCount);
	}
	
	public static int dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n + 1];
		int[] dist = new int[n + 1];
		
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
				}
				
				if (dist[next.v] <= m) {
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
		
		int count = 0;
		
		for (int i = 1; i <= n; i++) {
			if (visited[i]) {
				count += items[i];
			}
		}
		
		return count;
	}
	
	static class Node implements Comparable<Node> {
		int v, w;
		
		Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Node node) {
			return this.w - node.w;
		}
	}
}