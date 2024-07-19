import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int w, h;		// 지도의 너비(열), 높이(행)
	// 현재 위치(0, 0)을 기준으로
	// 위(-1, 0), 왼쪽(0, -1), 아래(1, 0), 오른쪽(0, 1) 이동
	// 왼쪽 위 대각선(-1, -1), 오른쪽 위 대각선(-1, 1) 이동
	// 왼쪽 아래 대각선(1, -1), 오른쪽 아래 대각선(1, 1) 이동
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};	
	static int[][] map;				// 2차원 지도 배열
	static boolean[][] visited;		// 2차원 방문 배열
	
	static class Point {
		int x, y;
		
		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 입력받는 테스트 케이스의 개수가 정해지지 않았으므로 무한 반복
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());	// 지도의 너비(열) 입력받기
			h = Integer.parseInt(st.nextToken());	// 지도의 높이(행) 입력받기
			
			// 위에서 입력받은 값이 둘 다 0이라면 무한 반복문 탈출
			if (w == 0 && h == 0) {
				break;
			}
			
			map = new int[h][w];			// 지도 배열 크기 지정
			visited = new boolean[h][w];	// 방문 배열 크기 지정
			
			// 2차원 지도 배열 초기화
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					// 1: 땅, 0: 바다
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;	// 섬의 개수
			
			// 모든 칸을 탐색
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					// 해당 칸을 아직 방문하지 않았고, 해당 칸이 땅이라면
					// 새로운 섬을 찾은 것임
					if (!visited[i][j] && map[i][j] == 1) {
						count++;	// 섬의 개수 1 증가
						bfs(i, j);	// 이 섬에 속하는 칸들을 해당 칸부터 모두 bfs로 탐색
					}
				}
			}
			
			bw.write(count + "\n");		// 섬의 개수 출력
			bw.flush();
		}
	}
	
	public static void bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();	// bfs 사용을 위한 큐
		queue.add(new Point(x, y));		// 시작 칸을 큐에 삽입
		visited[x][y] = true;			// 시작 칸을 방문 체크
		
		// 큐가 빌 때까지(현재 섬에 속하는 모든 칸들을 탐색할 때까지) 반복
		while (!queue.isEmpty()) {
			Point now = queue.poll();	// 현재 칸을 큐에서 뽑기
			
			// 현재 위치(x, y)을 기준으로
			// 위(x - 1, y), 왼쪽(x, y - 1), 아래(x - 1, y), 오른쪽(x, y - 1) 이동
			// 왼쪽 위 대각선(x - 1, y - 1), 오른쪽 위 대각선(x - 1, y + 1) 이동
			// 왼쪽 아래 대각선(x + 1, y - 1), 오른쪽 아래 대각선(x + 1, y + 1) 이동
			for (int i = 0; i < 8; i++) {
				int nx = now.x + dx[i];		// 이동한 칸의 x좌표
				int ny = now.y + dy[i];		// 이동한 칸의 y좌표
				
				// 이동한 칸의 x좌표나 y좌표가 유효한 인덱스가 아니라면 건너뛰기
				if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
					continue;
				}
				
				// 이동한 칸을 이미 방문했거나, 이동한 칸이 바다라면 건너뛰기
				if (visited[nx][ny] || map[nx][ny] == 0) {
					continue;
				}
				
				queue.add(new Point(nx, ny));	// 이동한 칸을 큐에 삽입
				visited[nx][ny] = true;			// 이동한 칸을 방문 체크
			}
		}
	}
}