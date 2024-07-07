import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;						// 공간의 크기
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static int[][] space;				// 2차원 공간 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		space = new int[N][N];
		int x = -1, y = -1;		// 아기 상어의 위치
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				// 0: 빈 칸
				// 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
				// 9: 아기 상어의 위치
				space[i][j] = Integer.parseInt(st.nextToken());
				if (space[i][j] == 9) {
					x = i;
					y = j;
					space[i][j] = 0;
				}
			}
		}
		
		System.out.println(findTimeToEatFish(x, y));
	}
	
	public static int findTimeToEatFish(int x, int y) {
		int size = 2;
		int count = 0;
		int time = 0;
		
		while (true) {
			Point fish = findNearestFish(x, y, size);
			if (fish == null) {
				break;
			}
			
			x = fish.x;
			y = fish.y;
			time += fish.time;
			count++;
			space[x][y] = 0;
			
			if (size == count) {
				size++;
				count = 0;
			}
		}
		
		return time;
	}
	
	public static Point findNearestFish(int x, int y, int size) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<Point> fishes = new PriorityQueue<>((p1, p2) -> {
			if (p1.time == p2.time) {
				if (p1.x == p2.x) {
					return Integer.compare(p1.y, p2.y);
				}
				return Integer.compare(p1.x, p2.x);
			}
			return Integer.compare(p1.time, p2.time);
		});
		
		queue.add(new Point(x, y, 0));
		visited[x][y] = true;
		
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				
				if (visited[nx][ny] || space[nx][ny] > size) {
					continue;
				}
				
				queue.add(new Point(nx, ny, now.time + 1));
				visited[nx][ny] = true;
				
				if (space[nx][ny] > 0 && space[nx][ny] < size) {
					fishes.add(new Point(nx, ny, now.time + 1));
				}
			}
		}
		
		if (!fishes.isEmpty()) {
			return fishes.poll();
		} else {
			return null;
		}
	}
	
	static class Point {
		int x, y, time;
		
		Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}