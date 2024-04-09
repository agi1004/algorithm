import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	// 자릿수
		long[] D = new long[N + 1];
		
		if (N == 1) {	// 1자리 이친수는 1밖에 없으므로 개수는 무조건 1개
			System.out.println(1);
			return;
		}
		
		if (N == 2) {	// 2자리 이친수는 10밖에 없으므로 개수는 무조건 1개
			System.out.println(1);
			return;
		}
		
		D[1] = 1;	// 1자리 이친수는 1개: 1
		D[2] = 1;	// 2자리 이친수는 1개: 10
		
		for (int i = 3; i <= N; i++) {
			D[i] = D[i - 1] + D[i - 2];
		}
				
		System.out.println(D[N]);	// N자리 이친수의 개수
	}
}