import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		int[] dist = bfs(1);
		
		int num = 20000;	// 1: 숨어야 하는 헛간 번호 (만약 거리가 같은 헛간이 여러개면 가장 작은 헛간 번호)
		int maxDist = 0;	// 2: 그 헛간까지의 거리
		int count = 0;		// 3: 그 헛간과 같은 거리를 갖는 헛간의 개수
		
		for (int i = 1; i <= N; i++) {
			maxDist = Math.max(maxDist, dist[i]);
		}
		
		for (int i = 1; i <= N; i++) {
			if (dist[i] == maxDist) {
				num = Math.min(num, i);
				count++;
			}
		}
		
		System.out.println(num + " " + maxDist + " " + count);
	}
	
	public static int[] bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];
		
		queue.add(start);
		visited[start] = true;
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int next : graph[now]) {
				if (!visited[next]) {
					queue.add(next);
					visited[next] = true;
					dist[next] = dist[now] + 1;
				}
			}
		}
		
		return dist;
	}
}