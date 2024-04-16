import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int MOD = 1000000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[10][N + 1];
		long count = 0;
		
		for (int num = 1; num <= 9; num++) {
			dp[num][1] = 1;
			if (N >= 2) {
				if (num == 9) {
					dp[num][2] = 1;
				} else {
					dp[num][2] = 2;
				}
			}
		}
		
		for (int i = 3; i <= N; i++) {
			for (int num = 1; num <= 9; num++) {
				if (num == 1) {
					dp[num][i] = (dp[num][i - 2] + dp[num + 1][i - 1]) % MOD;
				} else if (num == 9) {
					dp[num][i] = dp[num - 1][i - 1] % MOD;
				} else {
					dp[num][i] = (dp[num - 1][i - 1] + dp[num + 1][i - 1]) % MOD;
				}
			}
		}
		
		for (int num = 1; num <= 9; num++) {
			count += dp[num][N] % MOD;
		}
		
		System.out.println(count % MOD);
	}
}