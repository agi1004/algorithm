import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] areas = new int[N];
		int all = 0, max = 0;
		int answer = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			areas[i] = Integer.parseInt(st.nextToken());
			all += areas[i];
			max = Math.max(max, areas[i]);
		}
		
		int M = Integer.parseInt(br.readLine());
		
		if (all <= M) {
			System.out.println(max);
			return;
		}
		
		int start = 1, end = max;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			int sum = 0;
			
			for (int area : areas) {
				sum += Math.min(area, mid);
			}
			
			if (sum > M) {
				end = mid - 1;
			} else {
				answer = Math.max(answer, mid);
				start = mid + 1;
			}
		}
		
		System.out.println(answer);
	}
}