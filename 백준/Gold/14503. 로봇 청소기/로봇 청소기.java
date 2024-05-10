import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;					// 방의 행, 열 개수
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static int[][] room;				// 2차원 방 배열 (N * M 크기)
	
	static class Robot {
		int r;	// 로봇 청소기의 x좌표
		int c;	// 로봇 청소기의 y좌표
		int d;	// 로봇 청소기가 바라보는 방향 (0: 북쪽, 1: 동쪽, 2: 남쪽, 3: 서쪽)
		
		Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];	
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());	// 로봇 청소기의 x좌표
		int c = Integer.parseInt(st.nextToken());	// 로봇 청소기의 y좌표
		// 로봇 청소기가 바라보는 방향 (0: 북쪽, 1: 동쪽, 2: 남쪽, 3: 서쪽)
		int d = Integer.parseInt(st.nextToken());	
		
		// 2차원 방 배열 초기화 (0: 청소되지 않은 빈 칸, 1: 벽)
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(r, c, d);	// 현재 로봇 청소기의 위치, 방향을 시작으로 bfs 수행
		
		int cleanedCount = 0;	// 로봇 청소기가 작동을 멈출 때까지 청소하는 칸의 개수
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (room[i][j] == 2) {		// 현재 칸이 청소된 칸이라면
					cleanedCount++;			// 로봇 청소기가 작동을 멈출 때까지 청소하는 칸의 개수 1 증가
				}
			}
		}
		
		System.out.println(cleanedCount);	// 로봇 청소기가 작동을 멈출 때까지 청소하는 칸의 개수 출력
	}
	
	public static void bfs(int r, int c, int d) {
		Queue<Robot> queue = new LinkedList<>();	// bfs 수행을 위한 큐 생성
		queue.add(new Robot(r, c, d));		// 현재 로봇 청소기 큐에 삽입
		
		while (!queue.isEmpty()) {
			Robot now = queue.poll();		// 현재 로봇 청소기
			
			if (room[now.r][now.c] == 0) {	// 현재 칸이 아직 청소되지 않은 경우
				room[now.r][now.c] = 2;		// 현재 칸을 청소 (2: 청소된 칸)
			}
				
			boolean allCleaned = true;	// 현재 칸의 주변 4칸이 모두 청소되었다면 true, 아니라면 false
			
			for (int i = 0; i < 4; i++) {	// 현재 칸의 주변 4칸 탐색
				int x = now.r + dx[i];		// 현재 칸에서 x좌표 이동
				int y = now.c + dy[i];		// 현재 칸에서 y좌표 이동
				
				// 유효한 인덱스 범위에서 벗어난다면 건너뛰기
				if (x < 0 || y < 0 || x >= N || y >= M) {
					continue;
				}
					
				if (room[x][y] == 0) {	// 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
					allCleaned = false;	// 청소되지 않은 빈 칸이 있으므로 false
					break;
				}
			}
			
			Robot robot = null;
			
			if (allCleaned) {	// 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
				// 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면
				// 한 칸 후진한 로봇청소기 객체 리턴
				robot = goBack(now.r, now.c, now.d);
				
				if (robot != null) {	// 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면
					queue.add(robot);	// 한 칸 후진
				} else {				// 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면
					return;				// 작동을 멈춤
				}
			} else {			// 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
				now.d = (now.d == 0) ? 3 : now.d - 1;	// 반시계 방향으로 90도 회전
				
				// 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우
				// 한 칸 전진한 로봇 청소기 객체 리턴
				robot = goStraight(now.r, now.c, now.d);
				
				if (robot != null) {	// 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우
					queue.add(robot);	// 한 칸 전진
				} else {				// 한 칸 전진할 수 없다면
					queue.add(now);		// 방향만 바꾼 로봇 청소기를 다시 큐에 넣기
				}
			}
		}
	}
	
	// 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면, 한 칸 후진한 로봇청소기 객체 리턴
	public static Robot goBack(int r, int c, int d) {
		Robot robot = null;
		
		switch (d) {
			case 0:
				if (r + 1 < N && room[r + 1][c] != 1) {
					robot = new Robot(r + 1, c, d);
				}
				break;
				
			case 1:
				if (c - 1 >= 0 && room[r][c - 1] != 1) {
					robot = new Robot(r, c - 1, d);
				}
				break;
				
			case 2:
				if (r - 1 >= 0 && room[r - 1][c] != 1) {
					robot = new Robot(r - 1, c, d);
				}
				break;
				
			case 3:
				if (c + 1 < M && room[r][c + 1] != 1) {
					robot = new Robot(r, c + 1, d);
				}
				break;
		}
		
		return robot;
	}
	
	// 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우, 한 칸 전진한 로봇 청소기 객체 리턴
	public static Robot goStraight(int r, int c, int d) {
		Robot robot = null;
		
		switch (d) {
			case 0:
				if (r - 1 >= 0 && room[r - 1][c] == 0) {
					robot = new Robot(r - 1, c, d);
				}
				break;
				
			case 1:
				if (c + 1 < M && room[r][c + 1] == 0) {
					robot = new Robot(r, c + 1, d);
				} 
				break;
				
			case 2:
				if (r + 1 < N && room[r + 1][c] == 0) {
					robot = new Robot(r + 1, c, d);
				} 
				break;
				
			case 3:
				if (c - 1 >= 0 && room[r][c - 1] == 0) {
					robot = new Robot(r, c - 1, d);
				}
				break;
		}
		
		return robot;
	}
}