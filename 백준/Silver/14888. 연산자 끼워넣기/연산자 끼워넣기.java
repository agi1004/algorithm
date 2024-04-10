import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;	
	static int[] A, op, order;
	static int max = -1000000001, min = 1000000001;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 수의 개수
		A = new int[N];		// 수열(A1, A2, ..., AN)
		op = new int[4];	// 0: +의 개수, 1: -의 개수, 2: ×의 개수, 3: ÷의 개수 (합이 N-1)	
		order = new int[N - 1];	// 연산자 순서
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void dfs(int depth) {		
		if (depth == N - 1) {
			int result = A[0];	// 초깃값은 무조건 수열의 첫번째 값
			int i = 1;
			
			// 저장한 연산자 순서대로 연산 수행
			for (int od : order) {
				switch (od) {
					case 0:
						result += A[i++];
						break;
					case 1:
						result -= A[i++];
						break;
					case 2:
						result *= A[i++];
						break;
					case 3:
						result /= A[i++];
						break;
				}
			}
			
			max = Math.max(max, result);	// 최댓값 갱신
			min = Math.min(min, result);	// 최솟값 갱신
			return;
		}
		
		for (int i = 0; i < op.length; i++) {
			if (op[i] != 0) {
				order[depth] = i;	// 현재 깊이에 해당 연산자 추가
				op[i]--;			// 해당 연산자를 하나 사용했으므로 1 감소
				dfs(depth + 1);		// 깊이 1 증가하여 재귀호출
				op[i]++;			// 백트래킹의 핵심: 다시 되돌려놓기
			}
		}
	}
}