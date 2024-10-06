import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] cave;
	static int[][] counts;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		
		while (true) {
			int N = Integer.parseInt(br.readLine());
			
			if (N == 0) {
				break;
			}
			
			cave = new int[N][N];
			counts = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
					counts[i][j] = Integer.MAX_VALUE;
				}
			}
			
			sb.append("Problem " + tc++ + ": " + dijkstra(N) + "\n");
		}
		
		System.out.println(sb);
	}
	
	public static int dijkstra(int N) {
		Queue<Point> pq = new PriorityQueue<>();
		
		counts[0][0] = cave[0][0];
		pq.add(new Point(0, 0, counts[0][0]));
		
		while (!pq.isEmpty()) {
			Point now = pq.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				
				if (counts[nx][ny] > counts[now.x][now.y] + cave[nx][ny]) {
					counts[nx][ny] = counts[now.x][now.y] + cave[nx][ny];
					pq.add(new Point(nx, ny, counts[nx][ny]));
				}
			}
		}
		
		return counts[N - 1][N - 1];
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