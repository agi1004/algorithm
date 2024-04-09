import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] D = new int[N + 1];
		
		// D[i] :  i를 1로 만드는 데 걸리는 최소 연산의 횟수
		for (int i = 2; i <= N; i++) {
			D[i] = D[i - 1] + 1;	// 1을 뺀다.
			if (i % 2 == 0)			// X가 2로 나누어 떨어지면
				D[i] = Math.min(D[i], D[i / 2] + 1);	// 2로 나눈다.
			if (i % 3 == 0)			// X가 3으로 나누어 떨어지면
				D[i] = Math.min(D[i], D[i / 3] + 1);	// 3으로 나눈다.
		}
		
		System.out.println(D[N]);
	}
}