import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] predRanks = new int[N];
		int[] ranks = new int[N];
		Queue<Integer> queue = new LinkedList<>();
		long sum = 0;
		
		for (int i = 0; i < N; i++) {
			predRanks[i] = Integer.parseInt(br.readLine());
			queue.add(i + 1);
		}
		
		Arrays.sort(predRanks);
		
		for (int i = 0; i < N; i++) {
			ranks[i] = queue.poll();
			sum += Math.abs(predRanks[i] - ranks[i]);
		}
		
		System.out.println(sum);
	}
}