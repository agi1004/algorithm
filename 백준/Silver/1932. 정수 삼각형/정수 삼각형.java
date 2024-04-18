import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] triangle = new int[n + 1][n + 1];
		int[][] dp = new int[n + 1][n + 1];
		
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= i; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][1] = triangle[1][1];
		
		if (n >= 2) {
			dp[2][1] = dp[1][1] + triangle[2][1];
			dp[2][2] = dp[1][1] + triangle[2][2];
		}
		
		for (int i = 3; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				if (i == 1) {
					dp[i][j] = dp[i - 1][j] + triangle[i][j];
				} else if (i == j) {
					dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
				}
			}
		}
		
		int maxSum = Arrays.stream(dp[n]).max().getAsInt();
		System.out.println(maxSum);
	}
}