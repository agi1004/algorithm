import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		Arrays.sort(A);
		int start = 0, end = N - 1;
		int count = 0;
		while (start < end) {
			if (A[start] + A[end] < M) {
				start++;
			} else if (A[start] + A[end] > M) {
				end--;
			} else {
				count++;
				start++;
				end--;
			}
		}
		System.out.println(count);
		sc.close();
	}
}
