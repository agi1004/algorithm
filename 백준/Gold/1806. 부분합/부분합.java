import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0, end = 0;
		int sum = nums[start];
		int minLength = 100_000_001;
		
		while (start <= end) {
			if (sum < S) {
				if (end + 1 == N) {
					break;
				}
				sum += nums[++end];
			} else {
				minLength = Math.min(minLength, end - start + 1);
				sum -= nums[start++];
			}
		}
		
		if (minLength == 100_000_001) {
			System.out.println(0);
		} else {
			System.out.println(minLength);
		}
	}
}