import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	// N + 1일째 되는 날 퇴사
		int[] D = new int[N + 2];	// DP 점화식에 사용할 배열
		int[] T = new int[N + 1];	// 상담을 완료하는데 걸리는 시간 배열
		int[] P = new int[N + 1];	// 상담을 했을 때 받을 수 있는 금액 배열
		
		for (int i = 1; i <= N; i++) {
			T[i] = sc.nextInt();	
			P[i] = sc.nextInt();
		}
		
		// D[i]: i번째 날부터 퇴사 날까지 벌 수 있는 최대 수입
		for (int i = N; i >= 1; i--) {
			if (i + T[i] > N + 1) {	// 상담이 끝나는 날이 퇴사일보다 뒤라면
				D[i] = D[i + 1];	// i + 1번째 날의 최대 수입을 그대로 대입
			} else {				// 상담이 끝나는 날이 퇴사일보다 앞이라면
				// i + 1번째 날의 최대 수입과 
				// 상담이 끝나는 날의 최대 수입이랑 금액을 더한 값중에 더 큰 값을 대입 
				D[i] = Math.max(D[i + 1], D[i + T[i]] + P[i]);
			}
		}
		
		System.out.println(D[1]);
	}
}