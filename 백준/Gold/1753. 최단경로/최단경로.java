import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		List<Node>[] graph = new ArrayList[V + 1];
		boolean[] visited = new boolean[V + 1];
		int[] distance = new int[V + 1];
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= V; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, w));
		}
		
		distance[K] = 0;
		pq.add(new Node(K, 0));
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (visited[now.v]) {
				continue;
			}
			
			visited[now.v] = true;
			
			for (Node next : graph[now.v]) {
				if (distance[next.v] > distance[now.v] + next.w) {
					distance[next.v] = distance[now.v] + next.w;
					pq.add(new Node(next.v, distance[next.v]));
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			if (visited[i]) {
				sb.append(distance[i] + "\n");
			} else {
				sb.append("INF\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static class Node {
		int v, w;
		
		Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}