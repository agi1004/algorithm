import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 전깃줄의 개수
		int[][] wire = new int[N][2];	// 전깃줄 2차원 배열
		int[] dp = new int[N];	
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			wire[i][0] = Integer.parseInt(st.nextToken());	// A 전봇대의 번호
			wire[i][1] = Integer.parseInt(st.nextToken());	// B 전봇대의 번호
		}
		
		// 첫번째 열(A 전봇대) 기준으로 오름차순 정렬
		Arrays.sort(wire, new Comparator<int[]>() {
			@Override
			public int compare(int[] A, int[] B) {
				if (A[0] == B[0]) {		// 첫번째 열(A 전봇대)의 값이 같다면
					return A[1] - B[1];	// 두번째 열(B 전봇대) 기준으로 오름차순 정렬
				}
				return A[0] - B[0];		// 첫번째 열(A 전봇대) 기준으로 오름차순 정렬
			}
		});
		
		// LIS 알고리즘
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (wire[i][1] > wire[j][1] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
		}
		
		// 최대한 겹치지 않게 설치 가능한 개수
		int LIS = Arrays.stream(dp).max().getAsInt();
		// 없애야 하는 전선의 최소 개수 = 전체 전선 개수 - 최대한 겹치지 않게 설치 가능한 개수
		System.out.println(N - LIS);	
	}
}