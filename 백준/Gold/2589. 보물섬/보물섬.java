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
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		int maxDistance = 0;
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') {
					int distance = bfs(i, j);
					maxDistance = Math.max(maxDistance, distance);
				}
			}
		}
		
		System.out.println(maxDistance);
	}
	
	public static int bfs(int a, int b) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		int[][] distance = new int[N][M];
		
		queue.offer(new Point(a, b));
		visited[a][b] = true;
		
		int maxDist = 0;
		
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = now.x + dx[i];
				int y = now.y + dy[i];
				
				if (x < 0 || y < 0 || x >= N || y >= M) {
					continue;
				}
				
				if (visited[x][y] || map[x][y] == 'W') {
					continue;
				}
				
				queue.offer(new Point(x, y));
				visited[x][y] = true;
				distance[x][y] = distance[now.x][now.y] + 1;
				maxDist = Math.max(maxDist, distance[x][y]);
			}
		}
		
		return maxDist;
	}
	
	static class Point {
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}