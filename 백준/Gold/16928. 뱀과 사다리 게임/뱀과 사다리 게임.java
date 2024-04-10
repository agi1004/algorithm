import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 사다리의 수
		int M = Integer.parseInt(st.nextToken());	// 뱀의 수
		board = new int[101];	// 각 인덱스의 배열 값이 0이 아니라면 사다리나 뱀을 타고 이동해야 함
		
		// 사다리 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x] = y;	// 해당 인덱스의 배열 값이 0이 아니면 사다리를 타고 이동해야 함
		}	
		
		// 뱀 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			board[u] = v;	// 해당 인덱스의 배열 값이 0이 아니면 뱀을 타고 이동해야 함
		}
		
		System.out.println(bfs(1));
	}
	
	public static int bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[101];
		int[] roll = new int[101];	// 각 인덱스까지의 주사위를 굴린 횟수 배열
		
		queue.add(start);		// 큐에 첫 번째 위치 삽입
		visited[start] = true;	// 방문 체크
		
		while (!queue.isEmpty()) {
			int now = queue.poll();	// 현재 위치
			
			if (now == 100) {	// 목적지(100)에 도착하면 반환
				return roll[100];
			}
			
			// 주사위 굴리기
			for (int dice = 1; dice <= 6; dice++) {
				int next = now + dice;	// 다음 방문할 위치는 주사위를 굴린 값
				
				if (next > 100) {	// 목적지 범위를 초과하면 건너뛰기
					continue;
				}
				
				// board 배열 값이 0이 아니라면 사다리나 뱀을 타고 이동해야 함
				if (board[next] != 0) {	
					next = board[next];	// 다음 방문할 위치를 사다리나 뱀을 타고 이동한 값으로 바꾸기
				}
				
				if (!visited[next]) {	// 다음 방문할 위치에 한번도 방문하지 않았다면
					queue.add(next);		// 큐에 해당 위치 삽입
					visited[next] = true;	// 방문 체크
					roll[next] = roll[now] + 1;	// 주사위 굴리는 횟수 1 증가
				}
			}		
		}
		
		return -1;	// 목적지에 도착할 수 없는 겨우
	}
}