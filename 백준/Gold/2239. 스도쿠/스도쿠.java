import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int[][] sudoku = new int[9][9];	// 2차원 스도쿠 배열
	static ArrayList<int[]> emptyList = new ArrayList<>();	// 빈칸 좌표 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 2차원 스도쿠 배열 초기화
		for (int i = 0; i < 9; i++) {
			String line = br.readLine();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = line.charAt(j) - '0';
				if (sudoku[i][j] == 0) {	// 해당 칸이 빈 칸이라면
					emptyList.add(new int[] {i, j});	// 빈 칸 리스트에 좌표 삽입
				}
			}
		}
		
		dfs(0);		// 빈 칸 리스트의 0번째 인덱스부터 백트래킹 수행
	}
	
	public static void dfs(int depth) {
		// 빈 칸 리스트의 크기가 depth와 같다는 것은 모든 빈칸을 다 처리했다는 의미
		if (depth == emptyList.size()) {	
			// 빈 칸이 모두 채워진 2차원 스도쿠 배열 출력
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(sudoku[i][j]);
				}
				System.out.println();
			}
			
			// 여러 케이스가 나올 수 있으므로 return 대신 시스템 종료를 통해 
			// 최초로 조합된 경우(사전식으로 앞서는 것)만 출력하고 끝낸다.
			System.exit(0);
		}
		
		int x = emptyList.get(depth)[0];	// depth번째 빈 칸의 x좌표
		int y = emptyList.get(depth)[1];	// depth번째 빈 칸의 y좌표
		
		// 한 칸에 대한 가로, 세로, 네모를 모두 탐색하여 겹치지 않는 숫자 찾기
		
		boolean[] checked = new boolean[10];	// 0 ~ 9 중에 사용된 숫자를 체크하는 배열
		
		// 가로 탐색
		for (int i = 0; i < 9; i++) {
			// 빈 칸과 같은 x좌표의 i번째 열이 빈 칸이 아니고, 해당 칸의 숫자가 사용되지 않았다면
			if (sudoku[x][i] != 0 && !checked[sudoku[x][i]]) {
				checked[sudoku[x][i]] = true;	// 해당 칸의 숫자를 사용 체크
			}
		}
		
		// 세로 탐색
		for (int i = 0; i < 9; i++) {
			// 빈 칸과 같은 y좌표의 i번째 행이 빈 칸이 아니고, 해당 칸의 숫자가 사용되지 않았다면
			if (sudoku[i][y] != 0 && !checked[sudoku[i][y]]) {
				checked[sudoku[i][y]] = true;	// 해당 칸의 숫자를 사용 체크
			}
		}
		
		// 네모 탐색
		// x, y: 0 ~ 2 => squareX, squareY: 0 (첫번째 네모의 시작 인덱스)
		// x, y: 3 ~ 5 => squareX, squareY: 3 (두번째 네모의 시작 인덱스)
		// x, y: 6 ~ 8 => squareX, squareY: 6 (세번째 네모의 시작 인덱스)
		int squareX = (x / 3) * 3;	
		int squareY = (y / 3) * 3;
		
		// 0부터 3 전까지 탐색 (첫번째 행 네모의 시작 인덱스 ~ 끝 인덱스)
		// 3부터 6 전까지 탐색 (두번째 행 네모의 시작 인덱스 ~ 끝 인덱스)
		// 6부터 9 전까지 탐색 (세번째 행 네모의 시작 인덱스 ~ 끝 인덱스)
		for (int i = squareX; i < squareX + 3; i++) {
			// 0부터 3 전까지 탐색 (첫번째 열 네모의 시작 인덱스 ~ 끝 인덱스)
			// 3부터 6 전까지 탐색 (두번째 열 네모의 시작 인덱스 ~ 끝 인덱스)
			// 6부터 9 전까지 탐색 (세번째 열 네모의 시작 인덱스 ~ 끝 인덱스)
			for (int j = squareY; j < squareY + 3; j++) {
				// i번째 행의 j번째 열이 빈 칸이 아니고, 해당 칸의 숫자가 사용되지 않았다면
				if (sudoku[i][j] != 0 && !checked[sudoku[i][j]]) {
					checked[sudoku[i][j]] = true;	// 해당 칸의 숫자를 사용 체크
				}
			}
		}
		
		// 백트래킹
		for (int i = 1; i <= 9; i++) {
			if (!checked[i]) {	// 해당 숫자가 사용되지 않았다면
				sudoku[x][y] = i;	// 현재(depth번째) 빈 칸의 x좌표, y좌표의 칸에 해당 숫자 대입
				dfs(depth + 1);		// depth + 1번째 빈 칸 채우러 가기 (백트래킹)
				sudoku[x][y] = 0;	// 현재(depth번째) 칸을 다시 빈 칸으로 만들기
			}
		}
	}
}