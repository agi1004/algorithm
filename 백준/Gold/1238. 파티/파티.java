import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Node>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	    // 학생 수
		int M = Integer.parseInt(st.nextToken());	// 도로 수
		int X = Integer.parseInt(st.nextToken());	// 목적지
		graph = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			graph[A].add(new Node(B, T));
		}
		
		int maxTime = 0;
		
		for (int i = 1; i <= N; i++) {
			if (N != X) {
				maxTime = Math.max(maxTime, dijkstra(i, X) + dijkstra(X, i));
			}
		}
		
		System.out.println(maxTime);
	}
	
	public static int dijkstra(int start, int end) {
		Queue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		int[] time = new int[N + 1];
		
		Arrays.fill(time, Integer.MAX_VALUE);
		
		time[start] = 0;
		pq.add(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (visited[now.v]) {
				continue;
			}
			
			visited[now.v] = true;
			
			for (Node next : graph[now.v]) {
				if (time[next.v] > time[now.v] + next.w) {
					time[next.v] = time[now.v] + next.w;
					pq.add(new Node(next.v, time[next.v]));
				}
			}
		}
		
		return time[end];
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