// 출처) https://st-lab.tistory.com/101
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] board;
	static int min = 64;    // 8 * 8
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		board = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < M; j++) {
				if (line.charAt(j) == 'W') {	
					board[i][j] = true;		// W일때는 true
				} else {
					board[i][j] = false;	// B일때는 false
				}
			}
		}
		
		// 시작 인덱스 유효범위
		for (int i = 0; i <= N - 8; i++) {
			for (int j = 0; j <= M - 8; j++) {
				find(i, j);
			}
		}
		
		System.out.println(min);
	}
	
	public static void find(int x, int y) {	// x와 y는 시작 인덱스
		int count = 0;
		boolean TF = board[x][y];	// 첫 번째 칸의 색
		
		for (int i = x; i < x + 8; i++) {
			for (int j = y; j < y + 8; j++) {
				if (board[i][j] != TF) {	// 올바른 색이 아닐 경우
					count++;				// count 1 증가
				}
				
				TF = !TF;	// 다음 칸은 색이 바뀌므로 true라면 false로, false라면 true로 값 변경
			}
			
			TF = !TF;		// 안쪽 for문 끝나면 한번 더 값 변경
		}
		
		// 첫 번째 칸을 기준으로 할 때의 색칠할 개수(count)와
		// 첫 번째 칸의 색의 반대되는 색을 기준으로 할 때의 색칠할 개수(64 - count) 중
		// 최솟값을 count에 저장
		count = Math.min(count, 64 - count);
		
		min = Math.min(min, count);	// 최솟값보다 현재 count가 더 작을 경우 최솟값 갱신
	}
}