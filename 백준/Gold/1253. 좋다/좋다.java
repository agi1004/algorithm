import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		for (int i = 0; i < N; i++) {
			twoPointerSum(A, 0, N - 1, i);
		}
		
		System.out.println(count);
	}
	
	public static void twoPointerSum(int[] A, int S, int E, int index) {
		int target = A[index];
		
		while (S < E) {			
			if (A[S] + A[E] == target) {
				if (S != index && E != index) {
					count++;
					return;
				} else if (S == index) {
					S++;
				} else if (E == index) {
					E--;
				}
			} else if (A[S] + A[E] < target) {
				S++;
			} else {
				E--;
			}
		}
	}
}