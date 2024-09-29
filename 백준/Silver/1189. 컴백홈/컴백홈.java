import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int R, C, K;
	static char[][] map;
	static boolean[][] visited;
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R + 1][C + 1];
		visited = new boolean[R + 1][C + 1];
		
		for (int i = 1; i <= R; i++) {
			String line = br.readLine();
			for (int j = 1; j <= C; j++) {
				map[i][j] = line.charAt(j - 1);
			}
		}
		
		visited[R][1] = true;
		dfs(1, R, 1);	// 왼쪽 아래: (R, 1)
		
		System.out.println(count);
	}
	
	public static void dfs(int depth, int x, int y) {
		if (depth == K) {
			if (x == 1 && y == C) {		// 오른쪽 위: (1, C)
				count++;
			}
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 1 || ny < 1 || nx > R || ny > C) {
				continue;
			}
			
			if (visited[nx][ny] || map[nx][ny] == 'T') {
				continue;
			}
			
			visited[nx][ny] = true;
			dfs(depth + 1, nx, ny);
			visited[nx][ny] = false;
		}
	}
}