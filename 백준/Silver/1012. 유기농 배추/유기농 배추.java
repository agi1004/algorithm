import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {1, 0, -1, 0};	// x좌표 오른쪽, 왼쪽 조작
	static int[] dy = {0, 1, 0, -1};	// y좌표 아래, 위 조작
	static int M, N;
	static int[][] matrix;
	static boolean[][] visited;
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());		// 테스트 케이스의 개수
		
		// 테스트 케이스의 개수만큼 반복
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());		// 배추밭의 가로길이
			N = Integer.parseInt(st.nextToken());		// 배추밭의 세로길이
			int K = Integer.parseInt(st.nextToken());	// 배추가 심어져 있는 위치의 개수
			matrix = new int[M][N];						// 2차원 배추밭 (M x N)
			visited = new boolean[M][N];				// 2차원 방문 배열
			count = 0;		// 각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수
			
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());	// 배추의 X 좌표
				int Y = Integer.parseInt(st.nextToken());	// 배추의 Y 좌표
				matrix[X][Y] = 1;	// 배추가 심어져 있는 곳이므로 1로 만들기
			}
			
			// 배추 영역들이 띄엄띄엄 있으므로 모두 방문하기 위해 이중 반복문 돌려서 bfs 수행
			for (int x = 0; x < M; x++) {
				for (int y = 0; y < N; y++) {
					// 해당 좌표 값이 0이 아니고(배추가 심어져 있고), 방문하지 않았다면
					if (matrix[x][y] != 0 && !visited[x][y]) {
						bfs(x, y);	// 해당 영역 bfs 수행
					}
				}
			}
			
			System.out.println(count);	// 최소의 배추흰지렁이 마리 수 출력
		}
	}
	
	public static void bfs(int a, int b) {
		// 2차원 영역이므로 큐에 배열을 삽입해야 함
		Queue<int[]> queue = new LinkedList<>();	
		queue.add(new int[] {a, b});		// 방문했으므로 해당 좌표를 큐에 삽입
		visited[a][b] = true;				// 방문 체크
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();		// 방문했던 좌표를 큐에서 꺼내기
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];		// 해당 x좌표 기준으로 좌우 탐색
				int y = now[1] + dy[i];		// 해당 y좌표 기준으로 상하 탐색
				
				// 해당 인덱스가 유효하지 않는다면 건너뛰기
				if (x < 0 || y < 0 || x >= M || y >= N) {
					continue;
				}
				
				// 해당 좌표에 배추가 없거나, 방문을 이미 했다면 건너뛰기
				if (matrix[x][y] == 0 || visited[x][y]) {
					continue;
				}
				
				queue.add(new int[] {x, y});	// 방문했으므로 해당 좌표를 큐에 삽입
				visited[x][y] = true;			// 방문 체크
			}
		}
		
		count++;	// 해당 영역을 모두 탐색했으므로 해당 영역에 필요한 배추흰지렁이 마리 수 1 증가
	}
}