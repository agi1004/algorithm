import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long[] lans = new long[K];
		long maxLans = 0;
		
		for (int i = 0; i < K; i++) {
			lans[i] = Integer.parseInt(br.readLine());
			maxLans = Math.max(maxLans, lans[i]);
		}
		
		long start = 1, end = maxLans;
		long maxLength = 0;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			long count = 0;
			
			for (long lan : lans) {
				count += lan / mid;
			}
			
			if (count < N) {
				end = mid - 1;
			} else {
				maxLength = Math.max(maxLength, mid);
				start = mid + 1;
			}
		}
		
		System.out.println(maxLength);
	}
}