import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int[] A = new int[N + 1];
		
		// 배열의 모든 값을 인덱스 값으로 초기화
		for (int i = 2; i <= N; i++) {	
			A[i] = i;
		}
		
		// 제곱근까지만 수행하기 (시간복잡도를 o(n^2)에서 o(nlog(logn))으로 줄이기 위해
		for (int i = 2; i <= Math.sqrt(N); i++) {	
			// 이미 지운 수를 또 처리할 필요 없으므로 건너뛰기
			if (A[i] == 0) {	
				continue;
			}
			// 배수 지우기
			for (int j = i + i; j <= N; j = j + i) {	
				A[j] = 0;
			}
		}
		
		// M부터 N까지의 소수 출력
		for (int i = M; i <= N; i++) {		
			if (A[i] != 0) {
				System.out.println(A[i]);
			}
		}
		
		sc.close();
	}
}