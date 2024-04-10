import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());	// K * K 크기로 잘라내기
		int[][] D = new int[N + 1][M + 1];			// N * M 크기의 보드
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		// 첫 칸은 검은색으로 가정
		// 검은색: false, 흰색: true
		boolean color = false;	
		
		// 첫 칸이 검은색일 때 칠하는 경우 저장
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			
			for (int j = 1; j <= M; j++) {
				if (color && line.charAt(j - 1) == 'B') {	// 흰색이 올 차례인데 검은색일 때
					D[i][j] = 1;	// 1 삽입
				} else if (!color && line.charAt(j - 1) == 'W') {	// 검은색이 올 차례인데 흰색일 때
					D[i][j] = 1;	// 1 삽입
				}
				
				color = !color;	// 색 바꿔주기
			}
			
			if (M % 2 == 0) {	// 열(M)이 짝수일 때는
				color = !color;	// 줄이 바뀔 때 색을 한 번 더 바꿔주기
			}
		}
		
		// 행 누적합
		for (int i = 1; i <= N; i++) {		// 행(N) 까지
			int temp = D[i][1];		// (1, 1), (2, 1), (3, 1), ...
			
			for (int j = 2; j <= M; j++) {	// 열(M) 까지
				temp += D[i][j];	// j = 2: (1, 2), (1, 3), (1, 4), ...
				D[i][j] = temp;
			}
		}
		
		// 열 누적합
		for (int i = 1; i <= M; i++) {		// 열(M) 까지
			int temp = D[1][i];		// (1, 1), (1, 2), (1, 3), ...
			
			for (int j = 2; j <= N; j++) { // 행(N) 까지
				temp += D[j][i];	// j = 2: (2, 1), (3, 1), (4, 1), ...
				D[j][i] = temp;
			}
		}
		
		for (int i = K; i <= N; i++) {
			for (int j = K; j <= M; j++) {
				// 구간합 공식
				int temp = D[i][j] - D[i - K][j] - D[i][j - K] + D[i - K][j - K];
				
				min = Math.min(min, temp);	// 최솟값 저장
				max = Math.max(max, temp);	// 최댓값 저장
			}
		}
		
		// 첫 칸이 검은색일 때: min
		// 첫 칸이 흰색일 때 : K * K - max
		System.out.println(Math.min(min, K * K - max));		// 최솟값
	}
}