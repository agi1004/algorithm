import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] dp = new int[41];
		StringBuilder sb = new StringBuilder();
		
		dp[1] = dp[2] = 1;
		
		for (int i = 3; i <= 40; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			
			if (N == 0) {
				sb.append("1 0\n");
				continue;
			}
			
			sb.append(dp[N - 1] + " " + dp[N] + "\n");
		}
		
		System.out.println(sb);
	}
}