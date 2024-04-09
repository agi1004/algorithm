import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] A = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		long[] myArr = new long[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			myArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		for (int i = 0; i < M; i++) {
			binarySearch(A, 0, N - 1, myArr[i]);
		}
		
		br.close();
	}
	
	public static void binarySearch(long[] A, int start, int end, long target) {
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (target == A[mid]) {
				System.out.println(1);
				return;
			} else if (target < A[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		System.out.println(0);
	}
} 