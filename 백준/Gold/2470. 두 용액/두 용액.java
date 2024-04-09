import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] A;
	static int[] pair;
	static int minAbs;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 전체 용액의 수
		A = new int[N];		// 용액의 특성값을 나타내는 배열
		pair = new int[2];	// 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값
		minAbs = Integer.MAX_VALUE;	// 두 용액 특성값의 합의 절댓값
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);		// 투포인터 알고리즘 수행 전에는 무조건 오름차순 정렬을 해야 함
		
		twoPointer(0, N - 1);
		
		System.out.println(pair[0] + " " + pair[1]);
	}
	
	public static void twoPointer(int s, int e) {
		while (s < e) {
			int sum = A[s] + A[e];

			if (sum == 0) {
				pair[0] = A[s];
				pair[1] = A[e];
				break;
				
			} else if (sum < 0) {
				int temp = minAbs;
				minAbs = Math.min(Math.abs(minAbs), Math.abs(sum));
				// 만약 minAbs 값이 갱신되었다면
				if (minAbs != temp) {
					// 두 용액의 특성값 갱신
					pair[0] = A[s];
					pair[1] = A[e];
				}
				s++;
				
			} else {
				int temp = minAbs;
				minAbs = Math.min(Math.abs(minAbs), Math.abs(sum));
				// 만약 minAbs 값이 갱신되었다면
				if (minAbs != temp) {
					// 두 용액의 특성값 갱신
					pair[0] = A[s];
					pair[1] = A[e];
				}
				e--;
			}
		}
	}
}