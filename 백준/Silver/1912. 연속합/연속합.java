import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			int num = Integer.parseInt(st.nextToken());
			// 이전까지의 합과 현재 수를 비교하여 최대값 선택
			dp[i] = Math.max(dp[i - 1] + num, num);		
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
}