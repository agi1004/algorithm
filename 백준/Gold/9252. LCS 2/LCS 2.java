import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static char[] A;
	static char[] B;
	static int[][] DP;
	static ArrayList<Character> path;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine().toCharArray();			// 첫번째 문자열을 char 배열로 저장
		B = br.readLine().toCharArray();			// 두번째 문자열을 char 배열로 저장
		DP = new int[A.length + 1][B.length + 1];	// DP에 사용할 2차원 배열
		path = new ArrayList<>();					// LCS 경로 저장할 리스트
		
		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= B.length; j++) {
				if (A[i - 1] == B[j - 1]) {	// 같은 문자일 때는 왼쪽 대각선 값 + 1
					DP[i][j] = DP[i - 1][j - 1] + 1;
				} else {					// 다른 문자일 때는 왼쪽과 위의 값 중 큰 수
					DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
				}
			}
		}
		
		System.out.println(DP[A.length][B.length]);	// LCS 길이 출력
		getLCS(A.length, B.length);					// LCS 경로 저장하는 함수 호출
		
		// LCS 값이 맨 뒤부터 저장됐으므로 거꾸로 출력
		for (int i = path.size() - 1; i >= 0; i--) {
			System.out.print(path.get(i));
		}
	}
	
	// LCS 경로 저장하는 함수 - 맨 끝부터 살펴보기
	public static void getLCS(int r, int c) {
		if (r == 0 || c == 0)		// 인덱스 값이 둘 중 하나라도 0이라면
			return;					// 함수 종료
		
		if (A[r - 1] == B[c - 1]) {	// 같은 문자일 때는 LCS 경로 저장하고 왼쪽 대각선 위로 이동
			path.add(A[r - 1]);					// LCS 경로 저장
			getLCS(r - 1, c - 1);				// 왼쪽 대각선 위로 이동
		} else {					// 다른 문자일 때는 왼쪽과 위 중 큰 수로 이동
			if (DP[r - 1][c] > DP[r][c - 1]) {	// 위가 크다면
				getLCS(r - 1, c);				// 위로 이동
			} else {							// 왼쪽이 크다면
				getLCS(r, c - 1);				// 왼쪽으로 이동
			}
		}
	}
}