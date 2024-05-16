import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;					// 판의 세로, 가로 길이
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static int[][] board;				// 2차원 사각형 판 배열
	static boolean[][] air;				// 2차원 공기 배열
	static boolean[][] visited;			// 2차원 방문 배열
	static int allMeltingTime = 0;		// 치즈가 모두 녹아서 없어지는 데 걸리는 시간
	static Queue<int[]> queue = new LinkedList<>();	// bfs 수행을 위한 큐
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		int beforeMeltingCount = 0;	// 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수
		
		// 2차원 사각형 판 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				// 0: 치즈가 없는 칸, 1: 치즈가 있는 칸
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) {	// 해당 칸에 치즈가 있다면
					// 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수 1 증가
					// 치즈가 한 시간만에 녹을 수도 있으므로 처음부터 개수를 세어야 함
					beforeMeltingCount++;	
				}
			}
		}
		
		// 치즈가 모두 녹을 때까지 반복
		while (true) {
			air = new boolean[N][M];		// 공기 배열 초기화
			visited = new boolean[N][M];	// 방문 배열 초기화
			
			searchAir();	// 첫번째 공기 칸 찾기
			int count = searchMeltingCheese();	// 치즈가 녹고 남아있는 치즈조각 개수
			
			if (count == -1) {	// count 리턴 값이 -1이라면
				break;			// 치즈가 모두 녹은 것이므로 반복문 탈출
			} else {			// count 리턴 값이 -1이 아니라면
				// 치즈가 녹고 남아있는 치즈조각 개수를 
				// 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수 변수에 대입
				beforeMeltingCount = count;		
			}
		}
		
		// 치즈가 모두 녹아서 없어지는 데 걸리는 시간 출력
		System.out.println(allMeltingTime);		
		// 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수 출력
		System.out.println(beforeMeltingCount);
	}
	
	// 첫번째 공기 칸을 찾는 메소드
	public static void searchAir() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 0) {		// 0인 칸이면 공기 칸
					searchAirBfs(i, j);		// 해당 칸부터 모든 공기 칸을 찾기 
					return;					// 첫번째 공기 칸을 찾았으면 함수 종료
				}
			}
		}
	}
	
	// 모든 공기 칸을 찾는 메소드
	public static void searchAirBfs(int a, int b) {	
		queue.add(new int[] {a, b});	// 첫번째 공기 칸을 큐에 삽입	
		air[a][b] = true;				// 공기 배열 체크
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();		// 현재 공기 칸
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];		// 현재 칸을 기준으로 x좌표 이동
				int y = now[1] + dy[i];		// 현재 칸을 기준으로 y좌표 이동
				
				// 인덱스가 유효한 범위가 아니라면 건너뛰기
				if (x < 0 || y < 0 || x >= N || y >= M) {
					continue;
				}
				
				// 이미 공기 배열이 체크되어있거나, 치즈가 있는 칸이라면 건너뛰기
				if (air[x][y] || board[x][y] == 1) {
					continue;
				}
				
				queue.add(new int[] {x, y});	// 이동 가능한 공기 칸을 큐에 삽입
				air[x][y] = true;				// 공기 배열 체크
			}
		}
	}
	
	// 공기인 칸을 찾아서 그 칸부터 bfs를 수행하고, 치즈가 녹고 남아있는 치즈조각 개수를 리턴하는 메소드
	public static int searchMeltingCheese() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (air[i][j]) {		// 공기 배열이 체크되어있다면 첫번째 공기 칸
					// 공기인 칸을 기준으로 치즈를 녹이고, 치즈가 녹고 남아있는 치즈조각 개수를 리턴
					return searchMeltingCheeseBfs(i, j);	
				}
			}
		}
		return -1;	// return 문을 2개 써주어야 해서 의미없이 작성 (쓸모없는 코드)
	}
	
	// 공기인 칸 기준에 치즈가 있으면 녹이고, 치즈가 녹고 남아있는 치즈조각 개수를 리턴하는 메소드
	public static int searchMeltingCheeseBfs(int a, int b) {
		queue.add(new int[] {a, b});	// 첫번째 공기 배열 체크된 칸을 큐에 삽입	
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();		// 현재 공기 배열 체크된 칸
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];		// 현재 칸을 기준으로 x좌표 이동
				int y = now[1] + dy[i];		// 현재 칸을 기준으로 y좌표 이동
				
				// 인덱스가 유효한 범위가 아니라면 건너뛰기
				if (x < 0 || y < 0 || x >= N || y >= M) {
					continue;
				}
				
				// 치즈가 있는 칸이고, 방문이 체크되어있지 않다면
				if (board[x][y] == 1 && !visited[x][y]) {
					board[x][y] = 0;		// 치즈를 녹이기
					visited[x][y] = true;	// 방문 체크
				}
				
				// 공기 배열 체크된 칸이고, 방문이 체크되어있지 않다면
				if (air[x][y] && !visited[x][y]) {
					queue.add(new int[] {x, y});	// 이동 가능한 공기 칸을 큐에 삽입 
					visited[x][y] = true;			// 방문 체크
				}
			}
		}
		
		allMeltingTime++;	// 치즈를 녹이고 1시간 경과되었으므로 시간 1 증가
		
		boolean isAllMelting = true;	// 치즈가 모두 녹았는지의 여부
		int count = 0;					// 치즈가 녹고 남아있는 치즈조각 개수
        
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1) {		// 해당 칸에 치즈가 있다면
					count++;				// 치즈가 녹고 남아있는 치즈조각 개수 1 증가
					isAllMelting = false;	// 치즈가 모두 녹지 않았으므로 false로 바꾸기
				}
			}
		}
		
		if (isAllMelting) {	// 치즈가 모두 녹았다면
			return -1;		// -1 리턴
		} else {			// 치즈가 모두 녹지 않았다면
			return count;	// 치즈가 녹고 남아있는 치즈조각 개수 리턴
		}
	}
}
