import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 집의 수
		int[][] cost = new int[N + 1][3];	// 각 집을 빨강, 초록, 파랑으로 칠하는 비용을 저장하는 배열
		int[][] dp = new int[N + 1][3];		// 각 집까지의 최소 비용을 저장하는 배열
		
		// 각 집마다 빨강, 초록, 파랑으로 칠하는 비용을 입력받음
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());	// 빨강
			cost[i][1] = Integer.parseInt(st.nextToken());	// 초록
			cost[i][2] = Integer.parseInt(st.nextToken());	// 파랑
		}
		
		// 첫 번째 집까지의 최소 비용 초기화
		dp[1][0] = cost[1][0];
		dp[1][1] = cost[1][1];
		dp[1][2] = cost[1][2];
		
		// 두 번째 집부터 동적 계획법을 이용하여 각 집까지의 최소 비용을 계산
		for (int i = 2; i <= N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];	// 빨강으로 칠할 경우
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];	// 초록으로 칠할 경우
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];	// 파랑으로 칠할 경우
		}
		
		// 마지막 집까지의 최소 비용 중 최솟값을 출력
		int minCost = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
		System.out.println(minCost);
	}
}