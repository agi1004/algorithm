import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();	// 테스트 케이스의 개수
		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();	// 서쪽 사이트 개수
			int M = sc.nextInt();	// 동쪽 사이트 개수 (N <= M)
			System.out.println(combination(N, M));
		}
	}
	
	public static int combination(int N, int M) {
		int[][] D = new int[M + 1][M + 1];
		
		for (int i = 0; i <= M; i++) {
			D[i][1] = i;
			D[i][0] = 1;
			D[i][i] = 1;
		}
		
		for (int i = 2; i <= M; i++) {
			for (int j = 1; j < i; j++) {
				D[i][j] = D[i - 1][j - 1] + D[i - 1][j];
			}
		}
		
		return D[M][N];
	}
}