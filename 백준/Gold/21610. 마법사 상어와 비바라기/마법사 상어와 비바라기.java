import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] water;
	static boolean[][] visited;
	static Queue<Cloud> clouds = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// N * N 크기의 격자
		int M = Integer.parseInt(st.nextToken());	// 이동 개수
		water = new int[N + 1][N + 1];	// 물의 양을 갖고 있는 격자	
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				// (i, j)에 있는 바구니에 저장되어 있는 물의 양
				water[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다.
		clouds.add(new Cloud(N, 1));
		clouds.add(new Cloud(N, 2));
		clouds.add(new Cloud(N - 1, 1));
		clouds.add(new Cloud(N - 1, 2));
		
		// 이제 구름에 이동을 M번 명령하려고 한다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			visited = new boolean[N + 1][N + 1];	// true: 구름이 이미 사라진 칸
			// i번째 이동 명령은 방향 d과 거리 s로 이루어져 있다.
			int d = Integer.parseInt(st.nextToken()) - 1;	// 인덱스는 0부터 시작하므로 1 빼주기
			int s = Integer.parseInt(st.nextToken());
			int cloudCount = clouds.size();		// 현재 구름 개수
			
			// 1. 모든 구름이 di 방향으로 si칸 이동한다.
			// 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
			// 3. 구름이 모두 사라진다.
			moveAndRemoveCloud(d, s, cloudCount);
			
			// 4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 
			//    물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 
			//    (r, c)에 있는 바구니의 물이 양이 증가한다.
			//  - 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
			//  - 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, 
			//    (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
			copyWater();
			
			// 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 
			//    이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
			makeCloud();
		}
		
		int waterSum = 0;
		
		// M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				waterSum += water[i][j];
			}
		}
		
		System.out.println(waterSum);
	}
	
	public static void moveAndRemoveCloud(int d, int s, int cloudCount) {
		// 방향은 총 8개의 방향이 있으며, 8개의 정수로 표현한다. 
		// 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 이다. (인덱스는 0부터 시작)
		int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
		int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
		
		// 1. 현재 모든 구름이 
		for (int i = 0; i < cloudCount; i++) {
			Cloud now = clouds.poll();
			
			// 1. d 방향으로 s칸 이동한다.
			for (int j = 0; j < s; j++) {
				now.x = now.x + dx[d];	// 현재 x좌표에서 dx[d]만큼 이동
				now.y = now.y + dy[d];	// 현재 y좌표에서 dx[d]만큼 이동
				
				if (now.x < 1) {
					now.x = N;
				} else if (now.x > N) {
					now.x = 1;
				}
				
				if (now.y < 1) {
					now.y = N;
				} else if (now.y > N) {
					now.y = 1;
				}
			}
			
			// 이동한 구름 좌표를 구름 큐에 삽입
			clouds.add(new Cloud(now.x, now.y));
			
			// 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
			water[now.x][now.y]++;
			
			// 3. 구름이 모두 사라진다.
			visited[now.x][now.y] = true;
		}
	}
	
	public static void copyWater() {
		// 대각선 (↖, ↗, ↙, ↘) 이동
		int[] dx = {-1, -1, 1, 1};
		int[] dy = {-1, 1, -1, 1};
		
		// 4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다.
		while (!clouds.isEmpty()) {
			Cloud now = clouds.poll();
			int count = 0;
			
			// 4. 대각선 방향으로 거리가 1인 칸에 
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				// 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
				// 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, 
				// (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
				if (nx < 1 || ny < 1 || nx > N || ny > N) {
					continue;
				}
				
				// 4. 물이 있는 바구니의 수만큼
				if (water[nx][ny] > 0) {
					count++;
				}
			}
			
			// 4. (r, c)에 있는 바구니의 물이 양이 증가한다.
			water[now.x][now.y] += count;
		}
	}
	
	public static void makeCloud() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
				//    이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
				if (water[i][j] >= 2 && !visited[i][j]) {
					clouds.add(new Cloud(i, j));
					water[i][j] -= 2;
				}
			}
		}
	}
	
	static class Cloud {
		int x, y;
		
		Cloud(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}