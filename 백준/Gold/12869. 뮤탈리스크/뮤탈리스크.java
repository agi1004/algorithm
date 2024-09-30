import java.io.*;
import java.util.*;

public class Main {
	static int[][] attack = {{-9, -3, -1}, {-9, -1, -3}, 
							 {-3, -9, -1}, {-3, -1, -9},
							 {-1, -9, -3}, {-1, -3, -9}};
	static int[][][] dp = new int[61][61][61];
	static int minCount = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] scv = new int[3];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			scv[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(scv, 0);
		
		System.out.println(minCount);
	}
	
	public static void dfs(int[] scv, int count) {
		int s1 = scv[0];
		int s2 = scv[1];
		int s3 = scv[2];
		
		// 공격 횟수 최솟값보다 현재 공격 횟수가 같거나 클 경우 중단
		if (minCount <= count) {
			return;
		}
		
		// 이미 방문했는데 기존 공격 횟수가 더 작을 경우 중단
		if (dp[s1][s2][s3] != 0 && dp[s1][s2][s3] <= count) {
			return;
		}
		
		dp[s1][s2][s3] = count;
		
		// 모든 scv가 죽을 경우 최솟값 갱신 및 중단
		if (s1 == 0 && s2 == 0 && s3 == 0) {
			minCount = Math.min(minCount, count);
			return;
		}
		
		// 6가지 공격 패턴으로 현재 scv를 공격한 후 넘김
		for (int i = 0; i < 6; i++) {
			dfs(new int[] {Math.max(s1 + attack[i][0], 0), 
						   Math.max(s2 + attack[i][1], 0), 
					       Math.max(s3 + attack[i][2], 0)}, count + 1);
		}
	}
}