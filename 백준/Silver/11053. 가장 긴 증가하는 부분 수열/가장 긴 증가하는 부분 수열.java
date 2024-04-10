import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] DP = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(DP, 1);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (A[i] > A[j] && DP[i] < DP[j] + 1) {
					DP[i]++;
				}
			}
		}
		
		System.out.println(Arrays.stream(DP).max().getAsInt());
	}
}