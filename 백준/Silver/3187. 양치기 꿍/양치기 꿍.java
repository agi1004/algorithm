import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int livenSheep, livenWolf;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				// 빈 공간: '.', 울타리: '#', 양: 'k', 늑대: 'v'
				map[i][j] = line.charAt(j);
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j] && map[i][j] != '#') {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(livenSheep + " " + livenWolf);
	}
	
	public static void bfs(int startX, int startY) {
		Queue<int[]> queue = new LinkedList<>();
		int sheep = 0, wolf = 0;
		
		if (map[startX][startY] == 'k') {
			sheep++;
		} else if (map[startX][startY] == 'v') {
			wolf++;
		}
		
		queue.add(new int[] {startX, startY});
		visited[startX][startY] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}
				
				if (visited[nx][ny] || map[nx][ny] == '#') {
					continue;
				}
				
				if (map[nx][ny] == 'k') {
					sheep++;
				} else if (map[nx][ny] == 'v') {
					wolf++;
				}
				
				queue.add(new int[] {nx, ny});
				visited[nx][ny] = true;
			}
		}

		if (sheep > wolf) {
			livenSheep += sheep;
		} else {
			livenWolf += wolf;
		}
	}
}