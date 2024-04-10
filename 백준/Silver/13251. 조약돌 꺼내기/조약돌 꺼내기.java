import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();	// 조약돌의 색상 (1부터 M까지)
		int[] N = new int[M];
		int sum = 0;
		for (int i = 0; i < M; i++) {
			N[i] = sc.nextInt();	// 각 색상의 조약돌이 몇 개 있는지
			sum += N[i];
		}
		int K = sc.nextInt();	// K개 뽑기
		double[][] D = new double[sum + 1][sum + 1];
		
		for (int i = 0; i <= sum; i++) {
			D[i][1] = i;
			D[i][0] = 1;
			D[i][i] = 1;
		}
		
		for (int i = 2; i <= sum; i++) {
			for (int j = 1; j < i; j++) {
				D[i][j] = D[i - 1][j - 1] + D[i - 1][j];
			}
		}
		
		double same = 0;
		for (int i = 0; i < M; i++) {
			same += D[N[i]][K];
		}
		
		System.out.println(same / D[sum][K]);
	}
}