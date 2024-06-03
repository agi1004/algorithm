import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;						// 보드의 크기
	static char[][] board;				// 2차원 보드 배열
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static int maxCount;				// 먹을 수 있는 사탕의 최대 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 보드의 크기
		board = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = line.charAt(j);
			}
		}	
		
		// 사탕을 교환하기 전 각 행과 열의 사탕 색깔 별 개수 중 가장 큰 값 찾기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				findMaxCount(i, j);
			}
		}
		
		// 해당 칸과 인접 칸에 들어있는 사탕의 색깔이 다르면 서로 교환
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				swap(i, j);
			}
		}	
		
		System.out.println(maxCount);	// 먹을 수 있는 사탕의 최대 개수 출력
	}
	
	public static void findMaxCount(int x, int y) {		
		// (x, y)칸의 가로 줄을 검사하면서 최대 개수 갱신
		for (int i = 0; i < N - 1; i++) {
			boolean updated = false;		// 최대 개수 갱신 여부
			char now = board[x][i];			// 현재 칸
			
			// 현재 열 인덱스의 다음 인덱스부터 검사
			for (int j = i + 1; j < N; j++) {
				if (now != board[x][j]) {	// 현재 칸과 다음 칸이 다른 색깔이면
					int count = j - i;		// 개수: (다음 칸의 열 인덱스) - (현재 칸의 열 인덱스)
					maxCount = Math.max(maxCount, count);	// 최대 개수 갱신
					updated = true;			// 최대 개수 갱신 여부 true로 바꾸기
					break;					// 개수를 구했으므로 탈출
				}
			}
			
			if (!updated) {					// 최대 개수 갱신을 하지 않았다면
				int count = N - i;			// 개수: (보드의 크기) - (현재 칸의 열 인덱스)
				maxCount = Math.max(maxCount, count);	// 최대 개수 갱신
			}
		}

		// (x, y)칸의 세로 줄을 검사하면서 최대 개수 갱신
		for (int i = 0; i < N - 1; i++) {
			boolean updated = false;		// 최대 개수 갱신 여부
			char now = board[i][y];			// 현재 칸
			
			// 현재 행 인덱스의 다음 인덱스부터 검사
			for (int j = i + 1; j < N; j++) {
				if (now != board[j][y]) {	// 현재 칸과 다음 칸이 다른 색깔이면
					int count = j - i;		// 개수: (다음 칸의 행 인덱스) - (현재 칸의 행 인덱스)
					maxCount = Math.max(maxCount, count);	// 최대 개수 갱신
					updated = true;			// 최대 개수 갱신 여부 true로 바꾸기
					break;					// 개수를 구했으므로 탈출
				}
			}
			
			if (!updated) {					// 최대 개수 갱신을 하지 않았다면
				int count = N - i;			// 개수: (보드의 크기) - (현재 칸의 행 인덱스)
				maxCount = Math.max(maxCount, count);	// 최대 개수 갱신
			}
		}
	}
	
	public static void swap(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];		// 인접 칸의 x좌표
			int ny = y + dy[i];		// 인접 칸의 y좌표
			
			// 인접 칸이 유효한 인덱스가 아니라면 건너뛰기
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}
			
			// 인접한 두 칸의 색이 다르면
			if (board[x][y] != board[nx][ny]) {
				// 인접한 두 칸에 들어있는 사탕을 서로 교환
				char temp = board[x][y];
				board[x][y] = board[nx][ny];
				board[nx][ny] = temp;
				
				findMaxCount(x, y);		// 현재 행과 열의 사탕 색깔 별 개수 중 가장 큰 값 찾기
				
				// 인접한 두 칸에서 교환했던 사탕을 다시 원상복구
				temp = board[x][y];
				board[x][y] = board[nx][ny];
				board[nx][ny] = temp;
			}
		}
	}
}