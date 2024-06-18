import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	// 지도의 세로 크기
		int m = Integer.parseInt(st.nextToken());	// 지도의 가로 크기
		int[] dx = {-1, 0, 1, 0};					// 상, 하 이동
		int[] dy = {0, -1, 0, 1};					// 좌, 우 이동
		int[][] map = new int[n][m];				// 2차원 지도 배열
		boolean[][] visited = new boolean[n][m];	// 2차원 방문 배열
		int[] goal = new int[2];					// 목표 지점의 땅 좌표
		
		// 2차원 지도 배열 초기화
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {	// 해당 땅이 목표 지점이라면
					goal[0] = i;	// 목표 지점의 x좌표 저장
					goal[1] = j;	// 목표 지점의 y좌표 저장
				}
			}
		}
		
		Queue<int[]> queue = new LinkedList<>();	// bfs 수행을 위한 큐 생성
		queue.add(goal);							// 큐에 목표 지점의 땅 삽입
		visited[goal[0]][goal[1]] = true;			// 목표 지점을 방문 체크
		map[goal[0]][goal[1]] = 0;					// 목표 지점의 거리는 0으로 설정
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();		// 큐에서 현재 땅 뽑기
			
			for (int i = 0; i < 4; i++) {	
				int x = now[0] + dx[i];		// 인접한 땅의 x좌표
				int y = now[1] + dy[i];		// 인접한 땅의 y좌표
				
				// 인접한 땅의 인덱스가 유효하지 않다면 건너뛰기
				if (x < 0 || y < 0 || x >= n || y >= m) {
					continue;
				}
				
				// 인접한 땅을 이미 방문했거나, 갈 수 없는 땅이라면 건너뛰기
				if (visited[x][y] || map[x][y] == 0) {
					continue;
				}
				
				queue.add(new int[] {x, y});			// 큐에 인접한 땅 삽입
				visited[x][y] = true;					// 인접한 땅을 방문 체크
				map[x][y] = map[now[0]][now[1]] + 1;	// 인접한 땅의 거리 = 현재 땅의 거리 + 1
			}
		}
		
		// 각 지점에서 목표 지점까지의 거리를 모두 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치라면
				if (!visited[i][j] && map[i][j] == 1) {	
					bw.write(-1 + " ");			// -1 출력
				} else {	// 그 외에는
					bw.write(map[i][j] + " ");	// 저장된 거리를 그대로 출력
				}
			}
			bw.write("\n");
		}
		
		bw.flush();		// 버퍼에 저장했던 것을 한 번에 출력
	}
}