import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);	// 학생 수
		int M = Integer.parseInt(s[1]);	// 키를 비교한 횟수
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
		int[] D = new int[N + 1];		// 진입 차수 배열
		Queue<Integer> queue = new LinkedList<>();	// 위상 정렬을 위한 큐		
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			s = br.readLine().split(" ");
			int A = Integer.parseInt(s[0]);	// A가 B 앞에 서야 한다
			int B = Integer.parseInt(s[1]);	// 키 작은 순서대로 세우기
			graph[A].add(B);	// 단방향
			D[B]++;				// B에 진입하는 노드의 개수 배열값 (진입 차수 배열값) 1 증가
		}
		
		// 위상 정렬 수행
		for (int i = 1; i <= N; i++) {
			// 진입 차수 배열값이 0일때만 큐에 삽입
			if (D[i] == 0) {
				queue.offer(i);
			}
		}
		
		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			// 큐에 삽입한 값 뽑아서 출력
			int now = queue.poll();	
			System.out.print(now + " ");
			
			// 현재 노드가 진입할 수 있는 정점 둘러보기
			for (int next : graph[now]) {
				D[next]--;		// 진입 차수 배열값 1 감소
				// 진입 차수 배열값이 0일 때 큐에 삽입
				if (D[next] == 0) {	
					queue.offer(next);
				}
			}
		}		
 	}
}