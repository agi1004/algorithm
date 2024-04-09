import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// 양수는 큰 수부터 정렬 (절댓값이 큰 수 우선)  
		PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
		// 음수는 작은 수부터 정렬 (절댓값이 큰 수 우선)
		PriorityQueue<Integer> minus = new PriorityQueue<>();
		int zero = 0, one = 0;	// 0과 1의 개수
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			int n = sc.nextInt();
			if (n == 0) {
				zero++;
			} else if (n == 1) {
				one++;
			} else if (n > 0) {
				plus.offer(n);
			} else {
				minus.offer(n);
			}
		}
		
		// 양수 처리하기
		while (plus.size() > 1) {
			int x = plus.poll();
			int y = plus.poll();
			result += x * y;
		}
		
		// 음수 처리하기
		while (minus.size() > 1) {
			int x = Math.abs(minus.poll());
			int y = Math.abs(minus.poll());
			result += x * y;
		}
		
		// 양수 큐에 하나 남은 값 처리
		if (plus.size() == 1) {
			// 그대로 더하기
			result += plus.poll();
		}
		
		// 음수 큐에 하나 남은 값 처리
		if (minus.size() == 1) {
			if (zero > 0) {	// 수열에 0이 있다면
				// 하나 남은 값을 0과 곱해서 더하기
				result += minus.poll() * 0;
			} else {	// 수열에 0이 없다면
				// 그대로 더하기
				result += minus.poll();	
			}
		}
		
		// 1 처리
		result += one;
		
		System.out.println(result);
	}
}