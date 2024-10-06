import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N, M;
	static int[][] map;
	static int[][] counts;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		counts = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = line.charAt(j - 1) - '0';
				counts[i][j] = Integer.MAX_VALUE;
			}
		}
		
		System.out.println(dijkstra());
	}
	
	public static int dijkstra() {
		Queue<Point> pq = new PriorityQueue<>();
		
		counts[1][1] = 0;
		pq.add(new Point(1, 1, 0));
		
		while (!pq.isEmpty()) {
			Point now = pq.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				int count = now.count;
				
				if (nx < 1 || ny < 1 || nx > N || ny > M) {
					continue;
				}
				
				if (map[nx][ny] == 1) {
					count++;
				}
				
				if (counts[nx][ny] > count) {
					counts[nx][ny] = count;
					pq.add(new Point(nx, ny, count));
				}
			}
		}
		
		return counts[N][M];
	}
	
	static class Point implements Comparable<Point> {
		int x, y, count;
		
		Point(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
		
		public int compareTo(Point p) {
			return this.count - p.count;
		}
	}
}