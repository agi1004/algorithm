import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;					// 통로의 세로, 가로 길이
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static int[][] area;				// 2차원 통로 배열
	static boolean[][] visited;			// 2차원 방문 배열
	static int max;						// 가장 큰 음식물의 크기
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());	
		int K = Integer.parseInt(st.nextToken());	// 음식물 쓰레기의 개수
		area = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());	// 음식물의 x좌표
			int c = Integer.parseInt(st.nextToken());	// 음식물의 y좌표
			area[r][c] = 1;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (!visited[i][j] && area[i][j] != 0) {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(max);
	}
	
	public static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {a, b});
		visited[a][b] = true;
		int count = 2;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];
				int y = now[1] + dy[i];
				
				if (x < 1 || y < 1 || x > N || y > M) {
					continue;
				}
				
				if (visited[x][y] || area[x][y] == 0) {
					continue;
				}
				
				queue.add(new int[] {x, y});
				visited[x][y] = true;
				area[x][y] = count++;
				a = x;
				b = y;
			}
		}
		
		max = Math.max(max, area[a][b]);
	}
}