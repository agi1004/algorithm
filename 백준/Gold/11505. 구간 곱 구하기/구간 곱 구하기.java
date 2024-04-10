import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] tree;
	static int MOD;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 수의 개수
		int M = Integer.parseInt(st.nextToken());	// 수의 변경이 일어나는 횟수
		int K = Integer.parseInt(st.nextToken());	// 구간의 곱을 구하는 횟수
		
		int k = 0;
		while (true) {
			if (Math.pow(2, k) >= N) {
				break;
			}
			k++;
		}
		
		int size = (int)Math.pow(2, k) * 2;
		tree = new long[size];
		MOD = 1000000007;
		
		for (int i = 1; i < size; i++) {
			tree[i] = 1;
		}
		
		for (int i = 1; i <= N; i++) {
			int index = i + (int)Math.pow(2, k) - 1;
			tree[index] = Integer.parseInt(br.readLine());
		}
		
		for (int i = size - 1; i >= 2; i -= 2) {
			if (i != 1) {
				tree[i / 2] = tree[i] * tree[i - 1] % MOD;
			}
		}
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if (a == 1) {
				int index = b + (int)Math.pow(2, k) - 1;
				changeVal(index, c);
			} else {
				int start_index = b + (int)Math.pow(2, k) - 1;
				int end_index = c + (int)Math.pow(2, k) - 1;
				System.out.println(getDMul(start_index, end_index));
			}
		}
	}
	
	// 모든 함수에서 곱셈이 발생할 때마다 MOD 연산 수행하기
	public static void changeVal(int index, int val) {
		tree[index] = val;
		while (index > 1) {		// 현재 노드의 양쪽 자식 노드를 찾아 곱하는 로직
			index /= 2;
			tree[index] = tree[index * 2] * tree[index * 2 + 1] % MOD;
		}
	}
	
	public static long getDMul(int s, int e) {
		long dMul = 1;
		
		while (s <= e) {
			if (s % 2 == 1) {
				dMul = dMul * tree[s] % MOD;
			}	
			if (e % 2 == 0) {
				dMul = dMul * tree[e] % MOD;
			}
			
			s = (s + 1) / 2;
			e = (e - 1) / 2;
		}
		
		return dMul;
	}
}