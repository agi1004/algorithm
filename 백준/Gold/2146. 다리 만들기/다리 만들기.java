import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;						// 지도의 크기
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static int[][] map;					// 2차원 지도 배열
	static boolean[][] visited;			// 2차원 방문 체크 배열
	static int[][] area;				// 2차원 구역 배열
	static ArrayList<Point> edges = new ArrayList<>();	// 가장자리 좌표 리스트
	static int minLength = Integer.MAX_VALUE;			// 가장 짦은 다리의 길이
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	
		map = new int[N][N];
		visited = new boolean[N][N];
		area = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int areaNum = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					areaNum++;
					divideArea(i, j, areaNum);
				}
			}
		}
		
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					findEdge(i, j);
				}
			}
		}

		for (Point edge : edges) {
			visited = new boolean[N][N];
			bfs(edge.x, edge.y);
		}
		
		System.out.println(minLength);
	}
	
	public static void divideArea(int a, int b, int areaNum) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(a, b));
		visited[a][b] = true;
		area[a][b] = areaNum;
		
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = now.x + dx[i];
				int y = now.y + dy[i];
				
				if (x < 0 || y < 0 || x >= N || y >= N) {
					continue;
				}
				
				if (visited[x][y] || map[x][y] == 0) {
					continue;
				}
				
				queue.add(new Point(x, y));
				visited[x][y] = true;
				area[x][y] = areaNum;
			}
		}
	}
	
	public static void findEdge(int a, int b) {
		for (int i = 0; i < 4; i++) {
			int x = a + dx[i];
			int y = b + dy[i];
			
			if (x < 0 || y < 0 || x >= N || y >= N) {
				continue;
			}
			
			if (map[x][y] == 0) {
				edges.add(new Point(a, b));
				break;
			}
		}
	}
	
	public static void bfs(int a, int b) {
		Queue<Point> queue = new LinkedList<>();	
		int[][] distance = new int[N][N];
		queue.add(new Point(a, b));
		visited[a][b] = true;
		
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = now.x + dx[i];
				int y = now.y + dy[i];
				
				if (x < 0 || y < 0 || x >= N || y >= N) {
					continue;
				}
				
				if (visited[x][y]) {
					continue;
				}
				
				if (map[x][y] == 1 && area[x][y] != area[a][b]) {
					minLength = Math.min(minLength, distance[now.x][now.y]);
					return;
				}
				
				queue.add(new Point(x, y));
				visited[x][y] = true;
				distance[x][y] = distance[now.x][now.y] + 1;
			}
		}
	}
	
	static class Point {
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}