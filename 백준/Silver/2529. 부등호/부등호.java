import java.io.*;
import java.util.*;

public class Main {
	static int k;
	static char[] signs;
	static int[] nums;
	static boolean[] visited;
	static long max = 0, min = 10_000_000_000L;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		k = Integer.parseInt(br.readLine());
		signs = new char[k];
		nums = new int[k + 1];
		visited = new boolean[10];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			signs[i] = st.nextToken().charAt(0);
		}
		
		for (int i = 0; i <= 9; i++) {
			visited[i] = true;
			nums[0] = i;
			dfs(1);
			visited[i] = false;
		}
		
		String maxStr = String.valueOf(max);
		String minStr = String.valueOf(min);
		
		if (maxStr.length() == k + 1) {
			bw.write(max + "\n");
		} else {
			bw.write(0 + maxStr + "\n");
		}
		
		if (minStr.length() == k + 1) {
			bw.write(min + "\n");
		} else {
			bw.write(0 + minStr + "\n");
		}
		
		bw.flush();
	}
	
	public static void dfs(int depth) {
		if (depth == k + 1) {
			long n = 0;
			
			for (int num : nums) {
				n = n * 10 + num;
			}
			
			max = Math.max(max, n);
			min = Math.min(min, n);
			return;
		}
		
		for (int i = 0; i <= 9; i++) {
			if (!visited[i]) {
				if (signs[depth - 1] == '<' && nums[depth - 1] < i) {
					nums[depth] = i;
				} else if (signs[depth - 1] == '>' && nums[depth - 1] > i) {
					nums[depth] = i;
				} else {
					continue;
				}
				
				visited[i] = true;
				dfs(depth + 1);
				visited[i] = false;
			}
		}
	}
}