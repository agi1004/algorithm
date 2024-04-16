import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] stairs = new int[n + 1];
		int[] dp = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		// 첫 번째 계단까지의 최대 점수는 첫 번째 계단의 점수와 같음
		dp[1] = stairs[1];	
		
		// 두 번째 계단까지의 최대 점수는 첫 번째, 두 번째 계단의 점수를 합친 것
		if (n >= 2) {
			dp[2] = stairs[1] + stairs[2];
		}
		
		for (int i = 3; i <= n; i++) {
			// 현재 계단으로부터 한 칸 혹은 두 칸 오를 수 있으므로,
			// i번째 계단까지 도달할 때의 최대 점수는 
			// i - 1번째 계단에서 올라온 경우와 i - 2번째 계단에서 올라온 경우 중 큰 값에
			// i번째 계단의 점수를 더한 것
			dp[i] = Math.max(dp[i - 2], dp[i - 3] + stairs[i - 1]) + stairs[i];
		}
		
		System.out.println(dp[n]);	// 마지막 계단까지의 최대 점수 출력
	}
}