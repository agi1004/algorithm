import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] length = new long[N - 1];
		long[] moneyPerLiter = new long[N];
		long result = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			length[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			moneyPerLiter[i] = Integer.parseInt(st.nextToken());
			if (i > 0) {
				if (moneyPerLiter[i] > moneyPerLiter[i - 1]) {
					moneyPerLiter[i] = moneyPerLiter[i - 1];
				}
			}
		}
		
		for (int i = 0; i < N - 1; i++) {
			result += moneyPerLiter[i] * length[i];
		}
		
		System.out.println(result);
	}
}