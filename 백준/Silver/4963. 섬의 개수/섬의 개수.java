import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int w, h;
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) {
				break;
			}
			
			map = new int[h][w];
			visited = new boolean[h][w];
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (!visited[i][j] && map[i][j] == 1) {
						count++;
						bfs(i, j);
					}
				}
			}
			
			bw.write(count + "\n");
			bw.flush();
		}
	}
	
	public static void bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));
		visited[x][y] = true;
		
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			
			for (int i = 0; i < 8; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
					continue;
				}
				
				if (visited[nx][ny] || map[nx][ny] == 0) {
					continue;
				}
				
				queue.add(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}
	
	static class Point {
		int x, y;
		
		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}