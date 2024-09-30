import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N;
	static int[][] bed;
	static boolean[][] visited;
	static int minCost = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		bed = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				bed[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && validation(i, j)) {
					visited[i][j] = true;
					checkAdj(i, j, true);
					dfs(i, j, 1);
					visited[i][j] = false;
					checkAdj(i, j, false);
				}
			}
		}
		
		System.out.println(minCost);
	}
	
	public static void dfs(int x, int y, int depth) {
		if (depth == 3) {
			int cost = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j]) {
						cost += bed[i][j];
					}
				}
			}
			
			minCost = Math.min(minCost, cost);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && validation(i, j)) {
					visited[i][j] = true;
					checkAdj(i, j, true);
					dfs(i, j, depth + 1);
					visited[i][j] = false;
					checkAdj(i, j, false);
				}
			}
		}
	}
	
	private static boolean validation(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if ((nx < 0 || ny < 0 || nx >= N || ny >= N) || visited[nx][ny]) {
				return false;
			}
		}
		
		return true;
	}
	
	private static void checkAdj(int x, int y, boolean TF) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			visited[nx][ny] = TF;
		}
	}
}