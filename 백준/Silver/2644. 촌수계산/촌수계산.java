import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[] degree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());	// 전체 사람의 수
		graph = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		degree = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
			degree[i] = -1;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());	// 첫 번째 사람 번호
		int b = Integer.parseInt(st.nextToken());	// 두 번째 사람 번호
		int m = Integer.parseInt(br.readLine());	// 부모 자식들 간의 관계 수
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());	// 부모 번호
			int y = Integer.parseInt(st.nextToken());	// 자식 번호
			graph[x].add(y);
			graph[y].add(x);
		}
		
		bfs(a);
		
		System.out.println(degree[b]);
	}
	
	public static void bfs(int a) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(a);
		visited[a] = true;
		degree[a] = 0;
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int next : graph[now]) {
				if (!visited[next]) {
					queue.add(next);
					visited[next] = true;
					degree[next] = degree[now] + 1;
				}
			}
		}
	}
}