import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); 	
		int k = sc.nextInt();
		int start = 1, end = k;	// k <= N * N
		
		// B[k] = x: x보다 작거나 같은 자연수의 개수는 k개
		
		// Lower-Bound 사용: 찾고자 하는 값과 같거나 큰 수가 있는 첫 번째 인덱스 찾기
		// => k == count인 경우의 수가 여러개이기 때문
		while (start < end) {
			int mid = (start + end) / 2;
			int count = 0;
			
			// B[mid] = count: count보다 작거나 같은 자연수의 개수는 mid개
			for (int i = 1; i <= N; i++) {
				// mid가 N보다 크면 한 행에 대한 열의 개수를 초과하기 때문에
				// N보다 크지 않은 개수가 들어가게 하기
				count += Math.min(mid / i, N);
			}
			
			if (k <= count) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		
		// 찾고자 하는 값과 같거나 큰 수가 있는 첫 번째 인덱스 출력
		System.out.println(start);
	}
}