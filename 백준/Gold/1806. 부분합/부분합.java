import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, S;
	static int[] partSum;
	static int minLength;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 수열의 길이
		S = Integer.parseInt(st.nextToken());	
		partSum = new int[N + 1];		// 부분합 배열
		minLength = Integer.MAX_VALUE;	// 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 최소 길이
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			partSum[i] = partSum[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		twoPointer(1, 1);	// 부분합 투포인터는 s와 e가 모두 첫번째 인덱스에서 시작
		
		System.out.println(minLength);	// 최소 길이 출력
	}
	
	public static void twoPointer(int s, int e) {
		while (s <= e) {
			int length = e - s + 1;	// 해당 부분합의 길이
			
			if (partSum[e] - partSum[s - 1] >= S) {	// 부분합이 S 이상일 때
				minLength = Math.min(minLength, length);	// 최소 길이 갱신
				s++;			// s를 1 증가	
			} else {	// 부분합이 S 미만일 때
				if (e < N) {	// e가 맨 마지막 인덱스가 아닐 경우
					e++;		// e를 1 증가
				} else {		// e가 맨 마지막 인덱스일 경우
					s++;		// s를 1 증가
				}
			}
		}
		
		// 부분합이 S 이상이 되게 만드는 것이 불가능하다면
		if (minLength == Integer.MAX_VALUE) {
			minLength = 0;	// 최소 길이를 0으로 만들기
		}
	}
}