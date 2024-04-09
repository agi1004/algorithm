import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	// a의 개수
		int M = sc.nextInt();	// z의 개수
		int K = sc.nextInt();	// K번째 문자열 구하기
		int[][] D = new int[201][201];
		
		// 조합 테이블 초기화
		for (int i = 0; i <= 200; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) {
					D[i][j] = 1;
				} else {
					D[i][j] = D[i - 1][j - 1] +  D[i - 1][j];
					// K 범위가 넘어가면 범위의 최댓값 저장
					if (D[i][j] > 1000000000)
						D[i][j] = 1000000001;
				}
			}
		}
		
		// 사전에 수록되어 있는 문자열의 개수가 K보다 작으면 -1 출력
		if (D[N + M][M] < K) {
			System.out.println(-1);
			return;
		}	
		
		// a의 개수와 z의 개수 둘 다 0이 아닐 때까지 반복
		while (!(N == 0 && M == 0)) {	
			// 처음에는 a를 선택했다고 가정하였으므로 남아있는 문자의 개수: N - 1
			// 왼쪽 인덱스: 남아있는 문자의 개수
			// 오른쪽 인덱스: z의 개수
			// D[N - 1 + M][M] = 남아있는 문자들로 만들 수 있는 모든 경우의 수
			if (D[N - 1 + M][M] >= K) {	// 모든 경우의 수가 K보다 크거나 같다면 a 선택
				System.out.print("a");	// a 선택
				N--;					// a의 개수 감소
			} else {	// 모든 경우의 수가 K보다 작다면 z 선택 후 K = K - 모든 경우의 수
				System.out.print("z");		// z 선택
				K = K - D[N - 1 + M][M];	// K = K - 모든 경우의 수;
				M--;						// z의 개수 1 감소
			}
		}
	}
}