import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[][] matrix;
	static boolean[][][] visited;	// 벽을 부쉈는지 여부까지 고려하기 위해 3차원 배열 사용
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 세로
		M = Integer.parseInt(st.nextToken());	// 가로
		matrix = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1][2];	// 벽을 부수지 않은 경우: 0, 부순 경우: 1
		
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				matrix[i][j] = line.charAt(j - 1) - '0'; 	// char를 int로 변환
			}
		}
		
		System.out.println(bfs(1, 1, 0, 1));	// 시작점 (x좌표, y좌표, 벽을 부순 횟수(0 or 1), 거리)
	}
	
	public static int bfs(int a, int b, int w, int d) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {a, b, w, d});	// 시작점 큐에 삽입
		visited[a][b][w] = true;		    // 시작점 방문 체크
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int wall = now[2];		// 벽을 부순 횟수(0 or 1)
			int distance = now[3];	// 현재까지의 거리
			
			if (now[0] == N && now[1] == M) {	// 목적지에 도달한 경우
				return distance;
			}
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];	
				int y = now[1] + dy[i];	 
				
				
				// 인덱스가 유효한 범위가 아닌 경우 건너뛰기
				if (x < 1 || y < 1 || x > N || y > M) {
					continue;
				}
				
				// 해당 위치가 벽이 아니고 아직 방문하지 않은 곳인 경우
				if (matrix[x][y] == 0 && !visited[x][y][wall]) {
					// 다음 위치와 벽을 부순 횟수와 현재까지의 거리를 큐에 삽입
					queue.add(new int[] {x, y, wall, distance + 1});	
					visited[x][y][wall] = true;			// 다음 위치를 방문 체크
				}
				
				// 해당 위치가 벽이지만 벽을 아직 한 번도 부수지 않았고 아직 방문하지 않은 곳인 경우
				if (matrix[x][y] == 1 && wall == 0 && !visited[x][y][1]) {
					// 벽을 부수고(1) 다음 위치와 벽을 부순 횟수와 현재까지의 거리를 큐에 삽입
					queue.add(new int[] {x, y, 1, distance + 1});	
					visited[x][y][1] = true;		// 다음 위치를 방문 체크
				}
			}
		}
		
		return -1;	// 목적지까지 도달할 수 없는 경우
	}
}