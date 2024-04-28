import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] A = br.readLine().toCharArray();		// 첫번째 문자열을 char 배열로 바꾸기
		char[] B = br.readLine().toCharArray();		// 두번째 문자열을 char 배열로 바꾸기
		int[][] dp = new int[A.length + 1][B.length + 1];
		
		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= B.length; j++) {
				if (A[i - 1] == B[j - 1]) {	// 해당 문자가 같으면
					dp[i][j] = dp[i - 1][j - 1] + 1;	// 왼쪽 대각선 + 1 대입
				} else {					// 해당 문자가 다르면
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);	// 왼쪽과 위 중 큰 값 대입
				}
			}
		}
		
		System.out.println(dp[A.length][B.length]);	// 두 문자열의 LCS 길이 출력
	}
}