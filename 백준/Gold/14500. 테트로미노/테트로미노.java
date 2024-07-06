import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;					// 종이의 세로, 가로 크기
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static int[][] paper;				// 2차원 종이 배열
	static boolean[][] visited;			// 2차원 방문 체크 배열
	static int maxSum;					// 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		paper = new int[N][M];
		visited = new boolean[N][M];
		
		// 2차원 종이 배열 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 모든 칸을 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;	// 현재 칸을 방문 체크
				// 첫 번째 인자: 현재 칸의 x좌표
				// 두 번째 인자: 현재 칸의 y좌표
				// 세 번째 인자: 현재까지 계산된 칸에 쓰인 수들의 합 => 현재 칸에 적힌 수로 초기화
				// 네 번째 인자: 현재까지 계산된 칸 개수 => 첫 번째 칸이므로 1
				dfs(i, j, paper[i][j], 1);
				visited[i][j] = false;	// 현재 칸을 방문 해제 => 다음 칸으로 이동
				// ㅗ, ㅜ, ㅓ, ㅏ 모양은 백트래킹으로 탐색이 불가능하므로 따로 처리
				checkFuckyouShapes(i, j);		
			}
		}
		
		System.out.println(maxSum);	// 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값 출력
	}
	
	// sum: 현재까지 계산된 칸에 쓰인 수들의 합
	// depth: 현재까지 계산된 칸 개수
	public static void dfs(int x, int y, int sum, int depth) {
		// 깊이(칸 개수)가 4가 되면 그동안 구했던 합과 최댓값 변수 중에서
		// 더 큰 값을 최댓값 갱신 후 함수 종료
		if (depth == 4) {
			maxSum = Math.max(maxSum, sum);
			return;
		}
		
		// 현재 칸의 상, 하, 좌, 우 탐색
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];		// 인접 칸의 x좌표
			int ny = y + dy[i];		// 인접 칸의 y좌표
			
			// 인접 칸의 좌표가 유효 인덱스 범위에서 벗어난다면 건너뛰기
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				continue;
			}
			
			// 인접 칸을 방문하지 않았다면
			if (!visited[nx][ny]) {
				visited[nx][ny] = true;		// 인접 칸을 방문 체크
				// 첫 번째 인자: 인접 칸의 x좌표
				// 두 번째 인자: 인접 칸의 y좌표
				// 세 번째 인자: 현재까지 계산된 칸에 쓰인 수들의 합 => 인접 칸에 적힌 수를 더하기
				// 네 번째 인자: 현재까지 계산된 칸 개수 => 1 증가
				dfs(nx, ny, sum + paper[nx][ny], depth + 1);
				visited[nx][ny] = false;	// 인접 칸을 방문 해제 => 다음 칸으로 이동
			}
		}
	}
	
	// ㅗ, ㅜ, ㅓ, ㅏ 모양에 속하는 칸들의 수를 더하여 최댓값 변수 갱신
	public static void checkFuckyouShapes(int x, int y) {
		// ㅗ 모양
		if (x >= 1 && y >= 1 && x < N && y < M - 1) {
			int sum = paper[x][y] + paper[x - 1][y] + paper[x][y - 1] + paper[x][y + 1];
			maxSum = Math.max(maxSum, sum);
		}
		
		// ㅜ 모양
		if (x >= 0 && y >= 1 && x < N - 1 && y < M - 1) {
			int sum = paper[x][y] + paper[x + 1][y] + paper[x][y - 1] + paper[x][y + 1];
			maxSum = Math.max(maxSum, sum);
		}
		
		// ㅓ 모양
		if (x >= 1 && y >= 1 && x < N - 1 && y < M) {
			int sum = paper[x][y] + paper[x][y - 1] + paper[x - 1][y] + paper[x + 1][y];
			maxSum = Math.max(maxSum, sum);
		}
		
		// ㅏ 모양
		if (x >= 1 && y >= 0 && x < N - 1 && y < M - 1) {
			int sum = paper[x][y] + paper[x][y + 1] + paper[x - 1][y] + paper[x + 1][y];
			maxSum = Math.max(maxSum, sum);
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