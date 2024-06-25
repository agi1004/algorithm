import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;						// 유저의 수
	static ArrayList<Integer>[] graph;	// 그래프를 나타낼 리스트(1차원) 배열(1차원) => 2차원
	static int[] kevin;					// 케빈 베이컨의 수를 저장하는 1차원 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());		
		int M = Integer.parseInt(st.nextToken());	// 친구 관계의 수
		graph = new ArrayList[N + 1];
		kevin = new int[N + 1];			
		
		// 1부터 N까지 모두 리스트 공간을 만들어주어야 함
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 친구 관계를 그래프에 연결하기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph[A].add(B);	// 양방향 연결
			graph[B].add(A);
		}
		
		// 1 ~ N까지 모두 bfs 수행
		for (int i = 1; i <= N; i++) {
			bfs(i);
		}
		
		int minIndex = 1;	// 케빈 베이컨의 수가 가장 작은 인덱스(사람)
		
		for (int i = 1; i <= N; i++) {
			// 케빈 배이컨의 수가 가장 작은 인덱스의 배열 값보다 지금 인덱스의 배열 값이 더 작다면
			if (kevin[i] < kevin[minIndex]) {
				minIndex = i;	// 케빈 베이컨의 수가 가장 작은 인덱스 갱신
			}
		}
		
		System.out.println(minIndex);	// 케빈 베이컨의 수가 가장 작은 인덱스(사람) 출력
	}
	
	public static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();	// bfs 수행을 위한 큐
		boolean[] visited = new boolean[N + 1];		// 1차원 방문 체크 배열
		int[] stage = new int[N + 1];				// 1차원 단계 저장 배열

		queue.add(start);			// 큐에 맨 처음 숫자 삽입
		visited[start] = true;		// 맨 처음 숫자를 방문 체크
		
		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			int now = queue.poll();	// 큐에서 현재 숫자 뽑기
			
			// 현재 숫자와 연결된 숫자들 탐색
			for (int next : graph[now]) {
				if (!visited[next]) {		// 연결된 숫자를 방문하지 않았다면
					queue.add(next);		// 큐에 연결된 숫자 삽입
					visited[next] = true;	// 연결된 숫자를 방문 체크
					stage[next] = stage[now] + 1;	// 연결된 숫자의 단계 = 현재 숫자의 단계 + 1
				}
			}
		}
		
		// 1부터 N까지 단계의 수를 모두 더해서 맨 처음 숫자의 케빈 베이컨의 수로 저장
		for (int i = 1; i <= N; i++) {
			kevin[start] += stage[i];
		}
	}
}