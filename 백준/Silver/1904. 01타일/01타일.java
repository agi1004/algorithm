import java.util.Scanner;

public class Main {
	static int MOD = 15746;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] DP = new int[N + 1];
		
		if (N == 1) {
			System.out.println(1);
			return;
		}
		
		DP[1] = 1;
		DP[2] = 2;
		
		for (int i = 3; i <= N; i++) {
			DP[i] = (DP[i - 1] + DP[i - 2]) % MOD;
		}
		
		System.out.println(DP[N]);
	}
}