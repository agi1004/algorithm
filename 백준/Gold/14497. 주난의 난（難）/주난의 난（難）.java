import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N, M;
	static char[][] room;
	static boolean[][] visited;
	static List<int[]> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new char[N][M];
		visited = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken()) - 1;
		int y1 = Integer.parseInt(st.nextToken()) - 1;
		
		for (int i = 0; i < N; i++) {
			room[i] = br.readLine().toCharArray();
		}
		
		list.add(new int[] {x1, y1});
		visited[x1][y1] = true;
		
		int count = 0;
		
		while (true) {
			count++;
			if (bfs()) break;
		}
		
		System.out.println(count);
	}
	
	public static boolean bfs() {
		Queue<int[]> queue = new LinkedList<>(list);
		list.clear();
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				
				if (visited[nx][ny]) {
					continue;
				}
				
				if (room[nx][ny] == '#') {
					return true;
				} else if (room[nx][ny] == '1') {
					list.add(new int[] {nx, ny});
					visited[nx][ny] = true;
				} else {
					queue.add(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
		
		return false;
	}
}