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
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		queue.add(new Point(x, y, 0));
		visited[x][y] = true;
		
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			
			for (int i = 0; i < 8; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				
				if (visited[nx][ny]) {
					continue;
				}
				
				if (space[nx][ny] == 1) {
					return now.dist + 1;
				}
				
				queue.add(new Point(nx, ny, now.dist + 1));
				visited[nx][ny] = true;
			}
		}
		
		return 0;
	}
	
	static class Point {
		int x, y, dist;
		
		Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}