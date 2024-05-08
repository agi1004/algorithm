import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;					// 행렬의 세로 크기, 가로 크기
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static int[][] matrix;				// 2차원 행렬 배열
	static boolean[][] visited;			// 2차원 방문 체크 배열
	static int maxCount;				// 안전 영역의 최대 크기
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());	
		matrix = new int[N][M];
		
		// 2차원 행렬 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// N과 M의 크기가 작다. 
		// 이럴 경우에는 시간 복잡도 상관 없이 모든 경우의 수를 다 확인해 봐도 된다.
		// 브루트포스 알고리즘을 사용하자.
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][j] = 1;
					//System.out.print("1번째: " + (i + 1) + ", " + (j + 1) + " / ");
					wall2(i, j);
					matrix[i][j] = 0;
				}
			}
		}
		
		System.out.println(maxCount);
	}
	
	public static void wall2(int x1, int y1) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][j] = 1;
					//System.out.print("2번째: " + (i + 1) + ", " + (j + 1) + " / ");
					wall3(i, j);
					matrix[i][j] = 0;
				}
			}
		}
	}
	
	public static void wall3(int x2, int y2) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][j] = 1;
					//System.out.print("3번째: " + (i + 1) + ", " + (j + 1));
					searchVirusZone();
					int count = getCountOfSafetyZone();
					maxCount = Math.max(maxCount, count);
					matrix[i][j] = 0;
					//System.out.println(" => " + count);
				}
			}
		}
	}
	
	public static void searchVirusZone() {
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && matrix[i][j] == 2) {
					bfs(i, j);
				}
			}
		}
	}
	
	public static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {a, b});
		visited[a][b] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];
				int y = now[1] + dy[i];
				
				if (x < 0 || y < 0 || x >= N || y >= M) {
					continue;
				}
				
				if (visited[x][y] || matrix[x][y] != 0) {
					continue;
				}
				
				queue.add(new int[] {x, y});
				visited[x][y] = true;
				matrix[x][y] = 3;
			}
		}
	}
	
	public static int getCountOfSafetyZone() {
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] == 0) {
					count++;
				} else if (matrix[i][j] == 3) {
					matrix[i][j] = 0;
				}
			}
		}
		
		return count;
	}
}