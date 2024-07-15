import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dy = {0, -1, 0, 1, 1, -1, -1, 1};
	static int[][] space;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		space = new int[N][M];
		int maxDist = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (space[i][j] == 0) {
					maxDist = Math.max(maxDist, bfs(i, j));
				}
			}
		}
		
		System.out.println(maxDist);
	}
	
	public static int bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		int[][] distance = new int[N][M];
		
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for (int i = 0; i < 8; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				
				if (visited[nx][ny]) {
					continue;
				}
				
				if (space[nx][ny] == 1) {
					return distance[now[0]][now[1]] + 1;
				}
				
				queue.add(new int[] {nx, ny});
				visited[nx][ny] = true;
				distance[nx][ny] = distance[now[0]][now[1]] + 1;
			}
		}
		
		return 0;
	}
}