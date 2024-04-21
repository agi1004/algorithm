import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		int[] increaseDP = new int[N];
		int[] decreaseDP = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		
		// 증가하는 부분 수열 계산
		for (int i = 0; i < N; i++) {
			increaseDP[i] = 1;
			for (int j = 0; j < i; j++) {
				if (S[i] > S[j] && increaseDP[i] < increaseDP[j] + 1) {
					increaseDP[i] = increaseDP[j] + 1;
				}
			}
		}
		
		// 감소하는 부분 수열 계산
		for (int i = N - 1; i >= 0; i--) {
			decreaseDP[i] = 1;
			for (int j = N - 1; j > i; j--) {
				if (S[i] > S[j] && decreaseDP[i] < decreaseDP[j] + 1) {
					decreaseDP[i] = decreaseDP[j] + 1;
				}
			}
		}
		
		// 바이토닉 부분 수열의 길이 계산
		int maxLength = 0;
		for (int i = 0; i < N; i++) {
			maxLength = Math.max(maxLength, increaseDP[i] + decreaseDP[i] - 1);
		}
		
		System.out.println(maxLength);
	}
}