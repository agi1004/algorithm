import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 상(-1, 0), 하(1, 0), 좌(0, -1), 우(0, 1) 탐색 
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int M, N;			// 격자의 세로, 가로 크기
	static int[][] grid;		// 2차원 격자 배열
	static boolean[][] visited;	// 2차원 방문 배열(전류 전달 배열)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		grid = new int[M][N];			// 2차원 격자 배열의 크기 설정
		visited = new boolean[M][N];	// 2차원 방문 배열의 크기 설정
		
		// 2차원 격자 배열 초기화
		for (int i = 0; i < M; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				// 0: 전류가 잘 통하는 흰색 격자
				// 1: 전류가 통하지 않는 검은색 격자
				grid[i][j] = line.charAt(j) - '0';
			}
		}
		
		// 첫번째 줄(0번째 행)만 탐색
		for (int i = 0; i < N; i++) {
			if (grid[0][i] == 0) {	// 해당 칸이 전류가 통한다면
				bfs(0, i);	// 해당 칸부터 bfs 수행
			}
		}
		
		// 마지막 줄(M - 1번째 행)만 탐색
		for (int i = 0; i < N; i++) {
			// 해당 칸이 방문 체크되었다면(전류가 전달되었다면) YES 출력 후 메인 메서드 종료
			if (visited[M - 1][i]) {
				System.out.println("YES");
				return;
			}
		}
		
		System.out.println("NO");	// 마지막 줄까지 전류가 전달되지 않았으면 NO 출력
	}
	
	public static void bfs(int startX, int startY) {
		Queue<int[]> queue = new LinkedList<>();	// bfs 수행을 위한 큐
		queue.add(new int[] {startX, startY});	// 첫번째 칸을 큐에 삽입
		visited[startX][startY] = true;			// 첫번째 칸을 방문 체크(전류 전달)
		
		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			int[] now = queue.poll();	// 현재 칸을 큐에서 뽑기
			int x = now[0];				// 현재 칸의 x좌표
			int y = now[1];				// 현재 칸의 y좌표
			
			// 현재 칸을 기준으로 상, 하, 좌, 우 탐색
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];		// 인접 칸의 x좌표
				int ny = y + dy[i];		// 인접 칸의 y좌표
				
				// 인접 칸의 x좌표나 y좌표가 유효 인덱스 범위에서 벗어난다면 건너뛰기
				if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
					continue;
				}
				
				// 인접 칸을 이미 방문했거나(전류가 전달되었거나), 인접 칸이 전류가 통하지 않는다면 건너뛰기
				if (visited[nx][ny] || grid[nx][ny] == 1) {
					continue;
				}
				
				queue.add(new int[] {nx, ny});	// 인접 칸을 큐에 삽입
				visited[nx][ny] = true;			// 인접 칸을 방문 체크(전류 전달)
			}
		}
	}
}