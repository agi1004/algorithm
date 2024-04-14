import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] ball = new int[N + 1];
		
		for (int n = 1; n <= N; n++) {
			ball[n] = n;
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int temp = ball[i];
			ball[i] = ball[j];
			ball[j] = temp;
		}
		
		for (int n = 1; n <= N; n++) {
			System.out.print(ball[n] + " ");
		}
	}
}