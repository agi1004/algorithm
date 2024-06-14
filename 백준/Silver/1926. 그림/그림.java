import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;					// 도화지의 세로, 가로 크기
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static int[][] paper;				// 2차원 도화지 배열
	static boolean[][] visited;			// 2차원 방문 배열
	static int maxArea = 0;				// 가장 넓은 그림의 넓이
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	
		m = Integer.parseInt(st.nextToken());
		paper = new int[n][m];
		visited = new boolean[n][m];
		int count = 0;	// 그림의 개수
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && paper[i][j] != 0) {
					count++;
					bfs(i, j);
				}
			}
		}
		
		System.out.println(count);
		System.out.println(maxArea);
	}
	
	public static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {a, b});
		visited[a][b] = true;
		int area = 2;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];
				int y = now[1] + dy[i];
				
				if (x < 0 || y < 0 || x >= n || y >= m) {
					continue;
				}
				
				if (visited[x][y] || paper[x][y] == 0) {
					continue;
				}
				
				queue.add(new int[] {x, y});
				visited[x][y] = true;
				paper[x][y] = area++;
				a = x;
				b = y;
			}
		}
		
		maxArea = Math.max(maxArea, paper[a][b]);
	}
}