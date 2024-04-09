import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] sudoku = new int[9][9];
	static boolean[][] rowCheck = new boolean[9][10];	// 행 체크 배열
	static boolean[][] colCheck = new boolean[9][10];	// 열 체크 배열
	static boolean[][][] squareCheck = new boolean[3][3][10];	// 3x3 체크 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 스도쿠 입력 받기
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
				if (sudoku[i][j] != 0) {	// 스도쿠 값이 0이 아닐 경우에만
					rowCheck[i][sudoku[i][j]] = true;	// 해당 행 인덱스에 스도쿠 값 체크 
					colCheck[j][sudoku[i][j]] = true;	// 해당 열 인덱스에 스도쿠 값 체크
					squareCheck[i / 3][j / 3][sudoku[i][j]] = true;	// 해당 3x3 인덱스에 스도쿠 값 체크
				}
			}
		}
		
		dfs(0, 0);	// 첫 번째 칸부터 검사
		
		// 최종 스도쿠 출력
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(sudoku[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// 다음 칸으로 이동이 가능한지 여부를 판단하기 위해 함수 리턴값을 boolean으로 선언
	public static boolean dfs(int row, int col) {
		if (row == 9) {		// 모든 행을 검사했다면 종료
			return true;
		}
		
		if (col == 9) {		// 한 행을 모두 검사했다면 다음 행으로 이동
			return dfs(row + 1, 0);	// dfs(다음 행, 첫번째 열) 
		}
		
		if (sudoku[row][col] != 0) {	// 현재 칸이 채워져 있는 경우 다음 칸으로 이동
			return dfs(row, col + 1);	// dfs(해당 행, 다음 열)
		}
		
		for (int i = 1; i <= 9; i++) {
			// 행 인덱스에도 해당 값이 없고, 열 인덱스에도 해당 값이 없고, 3x3 인덱스에도 해당 값이 없다면
			if (!rowCheck[row][i] && !colCheck[col][i] && !squareCheck[row / 3][col / 3][i]) {
				rowCheck[row][i] = true;	// 행 인덱스에 해당 값 체크
				colCheck[col][i] = true;	// 열 인덱스에 해당 값 체크
				squareCheck[row / 3][col / 3][i] = true;	// 3x3 인덱스에 해당 값 체크
				sudoku[row][col] = i;		// 해당 좌표에 스도쿠 값 삽입
				
				if (dfs(row, col + 1)) {	// 다음 칸으로 넘어가기
					return true;
				}
				
				// * 백트래킹 핵심
				rowCheck[row][i] = false;	// 행 인덱스에 해당 값 체크 해제
				colCheck[col][i] = false;	// 열 인덱스에 해당 값 체크 해제
				squareCheck[row / 3][col / 3][i] = false;	// 3x3 인덱스에 해당 값 체크 해제
				sudoku[row][col] = 0;		// 해당 좌표에 값을 0으로 만들기
			}
		}
		
		return false;
	}
}