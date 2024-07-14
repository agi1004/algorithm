import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;					// 마당의 가로, 세로 크기
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static char[][] yard;				// 2차원 마당 배열
	static boolean[][] visited;			// 2차원 방문 배열
	static int livenSheep, livenWolf;	// 살아있는 양의 수, 살아있는 늑대의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		yard = new char[R][C];			// 마당 배열 크기 지정
		visited = new boolean[R][C];	// 방문 배열 크기 지정
		
		// 2차원 마당 배열 초기화
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				// '.': 빈 필드
				// '#': 울타리
				// 'o': 양
				// 'v': 늑대
				yard[i][j] = line.charAt(j);
			}
		}
		
		// 모든 칸 탐색
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 해당 칸을 방문하지 않았거나, 울타리가 아니라면 새로운 구역 발견
				if (!visited[i][j] && yard[i][j] != '#') {
					bfs(i, j);	// 해당 칸부터 bfs 수행
				}
			}
		}
		
		// 살아있는 양과 살아있는 늑대의 수 출력
		System.out.println(livenSheep + " " +  livenWolf);
	}
	
	public static void bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();	// bfs 수행을 위한 큐
		int sheep = 0;	// 이 구역 양의 수
		int wolf = 0;	// 이 구역 늑대의 수
		
		queue.add(new Point(x, y));		// 큐에 시작 칸 삽입
		visited[x][y] = true;			// 시작 칸을 방문 체크
		
		if (yard[x][y] == 'o') {		// 시작 칸에 양이 있다면
			sheep++;	// 이 구역 양의 수 1 증가
		} else if (yard[x][y] == 'v') {	// 시작 칸에 늑대가 있다면	
			wolf++;		// 이 구역 늑대의 수 1 증가
		}
		
		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			Point now = queue.poll();	// 현재 칸을 큐에서 뽑기
			
			// 현재 칸 기준으로 상, 하, 좌, 우 탐색
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];		// 인접 칸의 x좌표
				int ny = now.y + dy[i];		// 인접 칸의 y좌표
				
				// 인접 칸의 x좌표나 y좌표가 유효한 인덱스 범위가 아니라면 건너뛰기
				if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}
				
				// 인접 칸을 이미 방문했거나, 인접 칸이 울타리라면 건너뛰기
				if (visited[nx][ny] || yard[nx][ny] == '#') {
					continue;
				}
				
				queue.add(new Point(nx, ny));	// 큐에 인접 칸 삽입
				visited[nx][ny] = true;			// 인접 칸을 방문 체크
				
				if (yard[nx][ny] == 'o') {		// 인접 칸에 양이 있다면
					sheep++;	// 이 구역 양의 수 1 증가
				} else if (yard[nx][ny] == 'v') {	// 인접 칸에 늑대가 있다면
					wolf++;		// 이 구역 늑대의 수 1 증가
				}
			}
		}
		
		if (sheep > wolf) {
			// 영역 안의 양의 수가 늑대의 수보다 많다면 이기고, 늑대를 우리에서 쫓아낸다. 
			// => 살아있는 양의 수에 이 구역 양의 수를 모두 더하기
			livenSheep += sheep;
		} else {	
			// 그렇지 않다면 늑대가 그 지역 안의 모든 양을 먹는다.
			// => 살아있는 늑대의 수에 이 구역 늑대의 수를 모두 더하기
			livenWolf += wolf;
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