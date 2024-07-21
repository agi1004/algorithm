import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] A;
	static int[][] calcs;
	static int[] order;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N + 1][M + 1];
		calcs = new int[K][3];
		order = new int[K];
		visited = new boolean[K];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			calcs[i][0] = r;
			calcs[i][1] = c;
			calcs[i][2] = s;
		}
		
		dfs(0);
		
		System.out.println(min);
	}
	
	public static int[][] copyArray() {
		int[][] copy = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				copy[i][j] = A[i][j];
			}
		}
		
		return copy;
	}
	
	public static void dfs(int depth) {
		if (depth == K) {
			int[][] copy = spin();
			
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				
				for (int j = 1; j <= M; j++) {
					sum += copy[i][j];
				}
				
				min = Math.min(min, sum);
			}
			
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				order[depth] = i;
				dfs(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static int[][] spin() {
		int[][] copy = copyArray();
		
		for (int k = 0; k < K; k++) {
			int r = calcs[order[k]][0];
			int c = calcs[order[k]][1];
			int S = calcs[order[k]][2];
			
			for (int s = S; s > 0; s--) {
				int startX = r - s;
				int startY = c - s;
				int endX = r + s;
				int endY = c + s;
				
				int temp = copy[startX][endY];
				
				for (int i = endY; i > startY; i--) {
					copy[startX][i] = copy[startX][i - 1];
				}
				
				for (int i = startX; i < endX; i++) {
					copy[i][startY] = copy[i + 1][startY];
				}
				
				for (int i = startY; i < endY; i++) {
					copy[endX][i] = copy[endX][i + 1];
				}
				
				for (int i = endX; i > startX; i--) {
					copy[i][endY] = copy[i - 1][endY];
				}
				
				copy[startX + 1][endY] = temp;
			}
		}
		
		return copy;
	}
}