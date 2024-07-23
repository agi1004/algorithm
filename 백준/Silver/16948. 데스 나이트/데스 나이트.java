import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] chess;
	static int[] dx = {-2, -2, 0, 0, 2, 2};
	static int[] dy = {-1, 1, -2, 2, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		chess = new int[N][N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(r1, c1, r2, c2));
	}
	
	public static int bfs(int r1, int c1, int r2, int c2) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int[][] move = new int[N][N];
		
		queue.add(new int[] {r1, c1});
		visited[r1][c1] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
			
			for (int i = 0; i < 6; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				
				if (visited[nx][ny]) {
					continue;
				}
				
				if (nx == r2 && ny == c2) {
					return move[x][y] + 1;
				}
				
				queue.add(new int[] {nx, ny});
				visited[nx][ny] = true;
				move[nx][ny] = move[x][y] + 1;
			}
		}
		
		return -1;
	}
}