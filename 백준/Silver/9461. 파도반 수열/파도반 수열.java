import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			bw.write(P(N) + "\n");
			bw.flush();
		}
	}
	
	public static long P(int n) {
		long[] dp = new long[n + 1];
		
		if (n == 1 || n == 2 || n == 3) {
			return 1;
		}
		if (n == 4 || n == 5) {
			return 2;
		}
		
		dp[1] = dp[2] = dp[3] = 1;
		dp[4] = dp[5] = 2;
		
		for (int i = 6; i <= n; i++) {
			dp[i] = dp[i - 5] + dp[i - 1];
		}
		
		return dp[n];
	}
}