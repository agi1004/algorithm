import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] matrix;
	static boolean[][] visited;
	static Queue<int[]> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());	// 상자의 가로 칸의 수
		N = Integer.parseInt(st.nextToken());	// 상자의 세로 칸의 수
		matrix = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean flag = true;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (matrix[i][j] == 0) {
					flag = false;
					break;
				}
			}
		}
		
		if (flag) {
			System.out.println(0);
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (matrix[i][j] == 1 && !visited[i][j]) {
					queue.add(new int[] {i, j});
					visited[i][j] = true;
				}
			}
		}
		
		bfs();
	}
	
	public static void bfs() {
		int lastX = 0, lastY = 0;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];
				int y = now[1] + dy[i];
				
				if (x < 1 || y < 1 || x > N || y > M) {
					continue;
				}
				
				if (matrix[x][y] == -1 || visited[x][y]) {
					continue;
				}
				
				queue.add(new int[] {x, y});
				visited[x][y] = true;
				matrix[x][y] = matrix[now[0]][now[1]] + 1;
				
				lastX = x;
				lastY = y;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (matrix[i][j] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		System.out.println(matrix[lastX][lastY] - 1);
	}
}