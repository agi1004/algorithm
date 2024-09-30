import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N, M;
	static char[][] room;
	static boolean[][] area;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new char[N + 1][M + 1];
		
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());	// 주난 x좌표
		int y1 = Integer.parseInt(st.nextToken());	// 주난 y좌표
		int x2 = Integer.parseInt(st.nextToken());	// 범인 x좌표
		int y2 = Integer.parseInt(st.nextToken());	// 범인 y좌표
		
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				// 0: 빈 공간, 1: 친구, *: 주난, #: 범인
				room[i][j] = line.charAt(j - 1);
			}
		}
		
		while (true) {
			count++;
			
			setJunanArea(x1, y1);
			
			if (findCriminal(x1, y1)) {
				break;
			}
		}
		
		System.out.println(count);
	}
	
	public static void setJunanArea(int startX, int startY) {
		Queue<int[]> queue = new LinkedList<>();
		area = new boolean[N + 1][M + 1];
		
		queue.add(new int[] {startX, startY});
		area[startX][startY] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 1 || ny < 1 || nx > N || ny > M) {
					continue;
				}
				
				if (area[nx][ny] || room[nx][ny] == '1') {
					continue;
				}
				
				queue.add(new int[] {nx, ny});
				area[nx][ny] = true;
			}
		}
	}
	
	public static boolean findCriminal(int startX, int startY) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N + 1][M + 1];
		
		queue.add(new int[] {startX, startY});
		visited[startX][startY] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 1 || ny < 1 || nx > N || ny > M) {
					continue;
				}
				
				if (visited[nx][ny]) {
					continue;
				}
				
				if (room[nx][ny] == '1') {
					room[nx][ny] = '0';
				} else if (room[nx][ny] == '#') {
					return true;
				}
				
				if (area[nx][ny]) {
					queue.add(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
		
		return false;
	}
}