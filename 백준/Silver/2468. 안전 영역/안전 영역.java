import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {1, 0, -1, 0};	// 아래, 위 이동
	static int[] dy = {0, 1, 0, -1};	// 오른쪽, 왼쪽 이동
	static int N;						// 2차원 배열의 행과 열의 개수를 나타내는 수
	static int[][] matrix;				// 2차원 높이 배열
	static boolean[][] visited;			// 2차원 방문 배열
	static int[][] count;				// 2차원 각 높이에 따른 물에 잠기지 않는 안전한 영역의 개수 배열
	static int cnt;						// 해당 영역이 몇 번째인지 카운트하는 변수
	static int maxCount;				// 물에 잠기지 않는 안전한 영역의 최대 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		int maxDepth = 0;		// 2차원 높이 배열에 있는 높이 중 최대 높이
		
		// 2차원 높이 배열 초기화
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				maxDepth = Math.max(maxDepth, matrix[i][j]);	// 최대 높이 갱신
			}
		}
		
		// 높이가 0(비가 아예 오지 않는 경우)부터 최대 높이까지 모든 경우를 따져서
		// 물에 잠기지 않는 안전한 영역의 최대 개수 찾기
		for (int depth = 0; depth <= maxDepth; depth++) {
			visited = new boolean[N][N];	// 방문 배열 초기화
			count = new int[N][N];			// 물에 잠기지 않는 안전한 영역의 개수 배열 초기화
			cnt = 0;						// 해당 영역이 몇 번째인지 카운트하는 변수 초기화
			sink(depth);					// 각 높이 당 물에 잠기는 지점 표시
			maxCount = Math.max(maxCount, cnt);	// 물에 잠기지 않는 안전한 영역의 최대 개수 갱신
		}
		
		System.out.println(maxCount);		// 물에 잠기지 않는 안전한 영역의 최대 개수 출력
	}
	
	public static void sink(int depth) {
		// 각 높이 당 물에 잠기는 지점 표시
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 해당 높이가 물에 잠기는 높이보다 작거나 같으면, 물에 잠기므로
				if (matrix[i][j] <= depth) {
					count[i][j] = -1;	// 해당 카운트 영역에 -1 삽입
				}
			}
		}
		
		// 영역은 모두 떨어져있기 때문에 모든 구역에서 bfs 수행
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 해당 영역을 방문하지 않았고, 해당 영역이 물에 잠기지 않았다면
				if (!visited[i][j] && count[i][j] != -1) {
					cnt++;		// 해당 영역이 몇 번째인지 카운트하는 변수 1 증가
					bfs(i, j);	// 해당 영역부터 bfs 수행
				}
			}
		}
	}
	
	public static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();	// bfs 수행을 위한 큐 생성
		queue.add(new int[] {a, b});	// 해당 영역부터 큐에 삽입
		visited[a][b] = true;			// 해당 영역 방문 체크
		count[a][b] = cnt;		// 해당 영역부터 시작이므로 몇 번째인지 카운트하는 변수 대입
		
		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			int[] now = queue.poll();		// 현재 위치
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];		// 현재 좌표 기준으로 위, 아래 이동
				int y = now[1] + dy[i];		// 현재 좌표 기준으로 왼쪽, 오른쪽 이동
				
				// 인덱스 범위를 초과한다면 건너뛰기
				if (x < 0 || y < 0 || x >= N || y >= N) {
					continue;
				}
				
				// 해당 영역을 방문했거나, 해당 영역이 물에 잠겼다면 건너뛰기
				if (visited[x][y] || count[x][y] == -1) {
					continue;
				}
				
				queue.add(new int[] {x, y});	// 해당 영역 큐에 삽입
				visited[x][y] = true;			// 해당 영역 방문 체크
				count[x][y] = cnt;		// 해당 영역에 몇 번째인지 카운트하는 변수 대입
			}
		}
	}	
}