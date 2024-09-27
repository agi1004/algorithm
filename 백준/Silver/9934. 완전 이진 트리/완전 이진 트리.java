import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] orders;
	static List<Integer>[] tree;
	static boolean[] visited;
	static int[] nums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		N = (int)Math.pow(2, k) - 1;
		orders = new int[N + 1];
		tree = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		nums = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			orders[i] = Integer.parseInt(st.nextToken());
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N / 2; i++) {
			tree[i].add(i * 2);
			tree[i].add(i * 2 + 1);
		}
		
		bfs(1);
		
		int depth = 0, count = 0;
		
		for (int i = 1; i <= N; i++) {
			System.out.print(nums[i] + " ");
			count++;
			
			if (Math.pow(2, depth) == count) {
				System.out.println();
				depth++;
				count = 0;
			}
		}
	}
	
	public static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		int order = 1;
		
		queue.add(start);
		
		while (order <= N) {
			int now = queue.poll();
			
			if (!visited[now]) {
				if (!tree[now].isEmpty() && !visited[now * 2]) {
					queue.add(now * 2);
				} else {
					queue.add(now);
					visited[now] = true;
					nums[now] = orders[order++];
				}
			} else {
				if (!tree[now].isEmpty()) {
					if (!visited[now * 2 + 1]) {
						queue.add(now * 2 + 1);
					} else if (visited[now * 2]) {
						queue.add(now / 2);
					}
				} else {
					queue.add(now / 2);
				}
			}
		}
	}
}