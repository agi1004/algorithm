import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 온도를 측정한 전체 날짜의 수
		int K = Integer.parseInt(st.nextToken());	// 합을 구하기 위한 연속적인 날짜의 수
		int[] S = new int[N + 1];
		int max = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			S[i] = S[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		for (int i = K; i <= N; i++) {
			max = Math.max(max, S[i] - S[i - K]);
		}
		
		System.out.println(max);
	}
}