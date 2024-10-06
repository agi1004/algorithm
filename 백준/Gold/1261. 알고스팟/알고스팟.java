import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N, M;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = line.charAt(j - 1) - '0';
			}
		}
		
		System.out.println(bfs(1, 1));
	}
	
	public static int bfs(int startX, int startY) {
		Queue<Point> pq = new PriorityQueue<>((o1, o2) -> o1.count - o2.count);
		boolean[][] visited = new boolean[N + 1][M + 1];
		
		pq.add(new Point(startX, startY, 0));
		visited[startX][startY] = true;
		
		while (!pq.isEmpty()) {
			Point now = pq.poll();
			
			if (now.x == N && now.y == M) {
				return now.count;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if (nx < 1 || ny < 1 || nx > N || ny > M) {
					continue;
				}
				
				if (visited[nx][ny]) {
					continue;
				}
				
				if (map[nx][ny] == 1) {
					pq.add(new Point(nx, ny, now.count + 1));
				} else {
					pq.add(new Point(nx, ny, now.count));
				}
				
				visited[nx][ny] = true;
			}
		}
		
		return -1;
	}
	
	static class Point {
		int x, y, count;
		
		Point(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
}