import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] cards = new int[N + 1];
		int[] dp = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		// 카드 i개 구매 방법
		// 카드 1개가 들어있는 카드팩 구매 후, 카드 i - 1개가 들어있는 카드팩 구매
		// 카드 2개가 들어있는 카드팩 구매 후, 카드 i - 2개가 들어있는 카드팩 구매
		// 카드 3개가 들어있는 카드팩 구매 후, 카드 i - 3개가 들어있는 카드팩 구매
		// 카드 j개가 들어있는 카드팩 구매 후, 카드 i - j개가 들어있는 카드팩 구매
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i] = Math.max(dp[i], dp[i - j] + cards[j]);
			}
		}
		
		System.out.println(dp[N]);
	}
}