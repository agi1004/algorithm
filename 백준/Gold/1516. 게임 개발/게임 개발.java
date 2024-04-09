import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 건물의 종류 수
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
		int[] inDegree = new int[N + 1];		// 진입 차수 배열
		int[] time = new int[N + 1];	// 건물을 짓는데 걸리는 시간 배열
		int[] answer = new int[N + 1];	// N개의 각 건물이 완성되기까지 걸리는 최소 시간 배열
		Queue<Integer> queue = new LinkedList<>();	// 위상 정렬에 필요한 큐
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i <= N; i++) {
			String[] s = br.readLine().split(" ");
			time[i] = Integer.parseInt(s[0]);
			for (int j = 1; j < s.length - 1; j++) {
				int x = Integer.parseInt(s[j]);
				graph[x].add(i);
				inDegree[i]++;
			}
		}
		
		// 위상정렬
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int next : graph[now]) {
				inDegree[next]--;
				// 시간 업데이트하기
				answer[next] = Math.max(answer[next], answer[now] + time[now]);
				if (inDegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.println(answer[i] + time[i]);
		}
	}
}