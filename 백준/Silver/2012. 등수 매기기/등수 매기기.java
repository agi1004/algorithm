import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] ranks = new int[N + 1];
		long sum = 0;
		
		for (int i = 1; i <= N; i++) {
			ranks[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(ranks);
		
		for (int i = 1; i <= N; i++) {
			sum += Math.abs(ranks[i] - i);
		}
		
		System.out.println(sum);
	}
}