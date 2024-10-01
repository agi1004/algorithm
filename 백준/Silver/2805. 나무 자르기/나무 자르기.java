import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] trees = new int[N];
		int maxTree = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			maxTree = Math.max(maxTree, trees[i]);
		}
		
		int start = 1, end = maxTree;
		int maxHeight = 0;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			long sum = 0;
			
			for (int tree : trees) {
				sum += (long)Math.max(tree - mid, 0);
			}
			
			if (sum < M) {
				end = mid - 1;
			} else {
				maxHeight = Math.max(maxHeight, mid);
				start = mid + 1;
			}
		}
		
		System.out.println(maxHeight);
	}
}