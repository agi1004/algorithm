import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;						// 그리드의 가로, 세로 크기
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static char[][] grid;				// 2차원 그리드 배열
	static boolean[][] visited;			// 2차원 방문 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		grid = new char[N][N];
		visited = new boolean[N][N];
		
		// 2차원 그리드 배열 초기화
		for (int i = 0; i < N; i++) {
			String line = br.readLine();	// 한 줄 받아오기
			for (int j = 0; j < N; j++) {
				// 'R': 빨간색, 'G': 초록색, 'B': 파란색
				grid[i][j] = line.charAt(j);
			}
		}
		
		int areaCount = 0;	// 적록색약이 아닌 사람이 봤을 때의 구역의 개수
		
		// 모든 칸을 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 해당 칸을 방문하지 않았거나, 해당 칸이 빨간색이라면
				if (!visited[i][j] && grid[i][j] == 'R') {
					areaCount++;	// 적록색약이 아닌 사람이 봤을 때의 구역 개수 1 증가
					bfs(i, j, 'R');	// 이 구역에 해당하는 빨간색 칸을 모두 방문 체크하는 bfs 수행
				}
				// 해당 칸을 방문하지 않았거나, 해당 칸이 초록색이라면
				else if (!visited[i][j] && grid[i][j] == 'G') {
					areaCount++;	// 적록색약이 아닌 사람이 봤을 때의 구역 개수 1 증가
					bfs(i, j, 'G');	// 이 구역에 해당하는 초록색 칸을 모두 방문 체크하는 bfs 수행
				} 
				// 해당 칸을 방문하지 않았거나, 해당 칸이 파란색이라면
				else if (!visited[i][j] && grid[i][j] == 'B') {
					areaCount++;	// 적록색약이 아닌 사람이 봤을 때의 구역 개수 1 증가
					bfs(i, j, 'B');	// 이 구역에 해당하는 파란색 칸을 모두 방문 체크하는 bfs 수행
				}
			}
		}
		
		// 모든 칸을 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 모든 초록색 칸을 빨간색 칸으로 바꾸기 => 빨간색과 초록색을 같은 색으로 취급
				if (grid[i][j] == 'G') {
					grid[i][j] = 'R';
				}
			}
		}
		
		visited = new boolean[N][N];	// 구역을 다시 나눠야 하므로 방문 배열 초기화
		int areaCountOfRedGreen = 0;	// 적록색약인 사람이 봤을 때의 구역의 수
		
		// 모든 칸을 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 해당 칸을 방문하지 않았거나, 해당 칸이 빨간색이라면
				if (!visited[i][j] && grid[i][j] == 'R') {
					areaCountOfRedGreen++;	// 적록색약인 사람이 봤을 때의 구역 개수 1 증가
					bfs(i, j, 'R');	// 이 구역에 해당하는 빨간색 칸을 모두 방문 체크하는 bfs 수행
				} 
				// 해당 칸을 방문하지 않았거나, 해당 칸이 파란색이라면
				else if (!visited[i][j] && grid[i][j] == 'B'){
					areaCountOfRedGreen++;	// 적록색약인 사람이 봤을 때의 구역 개수 1 증가
					bfs(i, j, 'B');	// 이 구역에 해당하는 파란색 칸을 모두 방문 체크하는 bfs 수행
				}
			}
		}
		
		// 적록색약이 아닌 사람이 봤을 때의 구역의 개수, 적록색약인 사람이 봤을 때의 구역의 개수 출력
		System.out.println(areaCount + " " + areaCountOfRedGreen);
	}
	
	public static void bfs(int x, int y, char color) {
		Queue<Point> queue = new LinkedList<>();	// bfs 수행을 위한 큐
		queue.add(new Point(x, y));		// 큐에 시작 칸 삽입
		visited[x][y] = true;	// 매개변수 color와 같은 색인 시작 칸을 방문 체크
		
		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			Point now = queue.poll();	// 큐에서 현재 칸 뽑기
			
			// 현재 칸 기준 상, 하, 좌, 우 탐색
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];		// 인접 칸의 x좌표
				int ny = now.y + dy[i];		// 인접 칸의 y좌표
				
				// 인접 칸의 x좌표, y좌표가 유효 인덱스 범위를 벗어난다면 건너뛰기
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				
				// 인접 칸을 이미 방문했거나,
				// 인접 칸의 색이 매개변수 color의 색과 다르다면 건너뛰기
				if (visited[nx][ny] || grid[nx][ny] != color) {
					continue;
				}
				
				queue.add(new Point(nx, ny));	// 인접 칸을 큐에 삽입
				visited[nx][ny] = true;		// 매개변수의 color와 같은 색인 인접 칸을 방문 체크
			}
		}
	}
	
	static class Point {
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}