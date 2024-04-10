import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, 1, -2, 2, -2, 2, -1, 1};
	static int[] dy = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int l, startX, startY, endX, endY;
	static int[][] matrix;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스의 개수
		
		for (int i = 0; i < T; i++) {
			l = Integer.parseInt(br.readLine());	// 체스판의 한 변의 길이
			matrix = new int[l][l];
			visited = new boolean[l][l];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());	// 현재 x좌표
			startY = Integer.parseInt(st.nextToken());	// 현재 y좌표
			
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());	// 최종 x좌표
			endY = Integer.parseInt(st.nextToken());	// 최종 y좌표
			
			System.out.println(bfs());
		}
	}
	
	public static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {startX, startY});
		visited[startX][startY] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			if (now[0] == endX && now[1] == endY) {
				return matrix[endX][endY];
			}
			
			for (int i = 0; i < 8; i++) {
				int x = now[0] + dx[i];
				int y = now[1] + dy[i];
				
				if (x < 0 || y < 0 || x >= l || y >= l) {
					continue;
				}
				
				if (visited[x][y]) {
					continue;
				}
				
				queue.add(new int[] {x, y});
				visited[x][y] = true;
				matrix[x][y] = matrix[now[0]][now[1]] + 1;
			}
		}
		
		return -1;
	}
}