import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N;
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};
	static int[][] banner;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		banner = new int[M][N];
		visited = new boolean[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				banner[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && banner[i][j] == 1) {
					count++;
					bfs(i, j);
				}
			}
		}
		
		System.out.println(count);
	}
	
	public static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {a, b});
		visited[a][b] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
			
			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
					continue;
				}
				
				if (visited[nx][ny] || banner[nx][ny] == 0) {
					continue;
				}
				
				queue.add(new int[] {nx, ny});
				visited[nx][ny] = true;
			}
		}
	}
}