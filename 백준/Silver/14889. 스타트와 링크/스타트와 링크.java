import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] S;
	static int[] start;
	static int[] link;
	static int minDiff = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		start = new int[N / 2];
		link = new int[N / 2];
				
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		
		System.out.println(minDiff);
	}
	
	public static void dfs(int s, int depth) {
		if (depth == N / 2) {
			boolean[] check = new boolean[N];
			int startResult = 0, linkResult = 0;
			int index = 0;
			
			for (int i = 0; i < N / 2; i++) {
				check[start[i]] = true;
			}
			
			for (int i = 0; i < N; i++) {
				if (!check[i]) {
					link[index++] = i;
				}
			}
			
			for (int i = 0; i < N / 2; i++) {
				for (int j = i + 1; j < N / 2; j++) {
					startResult += S[start[i]][start[j]] + S[start[j]][start[i]];
				}
			}
			
			for (int i = 0; i < N / 2; i++) {
				for (int j = i + 1; j < N / 2; j++) {
					linkResult += S[link[i]][link[j]] + S[link[j]][link[i]];
				}
			}
						
			minDiff = Math.min(minDiff, Math.abs(startResult - linkResult));
			return;
		}
		
		for (int i = s; i < N; i++) {
			start[depth] = i;
			dfs(i + 1, depth + 1);
		}
	}
}