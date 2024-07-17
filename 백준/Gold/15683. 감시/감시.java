import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Point> cctvs = new ArrayList<>();
	static int minSize = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] office = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				// 0: 빈 칸
				// 6: 벽
				// 1 ~ 5: CCTV
				office[i][j] = Integer.parseInt(st.nextToken());
				if (office[i][j] >= 1 && office[i][j] <= 5) {
					cctvs.add(new Point(i, j));
				}
			}
		}
		
		dfs(0, office);
		System.out.println(minSize);
	}
	
	public static void dfs(int depth, int[][] office) {
		if (depth == cctvs.size()) {
			minSize = Math.min(minSize, getSize(office));
			return;
		}
		
		Point cctv = cctvs.get(depth);
		int x = cctv.x;
		int y = cctv.y;
		
		switch (office[x][y]) {
			case 1:
				for (int i = 0; i < 4; i++) {
					int[][] tempOffice = deepCopy(office);
					monitor1(x, y, i, tempOffice);
					dfs(depth + 1, tempOffice);
				}
				break;
			case 2:
				for (int i = 0; i < 2; i++) {
					int[][] tempOffice = deepCopy(office);
					monitor2(x, y, i, tempOffice);
					dfs(depth + 1, tempOffice);
				}
				break;
			case 3:
				for (int i = 0; i < 4; i++) {
					int[][] tempOffice = deepCopy(office);
					monitor3(x, y, i, tempOffice);
					dfs(depth + 1, tempOffice);
				}
				break;
			case 4:
				for (int i = 0; i < 4; i++) {
					int[][] tempOffice = deepCopy(office);
					monitor4(x, y, i, tempOffice);
					dfs(depth + 1, tempOffice);
				}
				break;
			case 5:
				int[][] tempOffice = deepCopy(office);
				monitor5(x, y, tempOffice);
				dfs(depth + 1, tempOffice);
				break;
		}
	}
	
	public static int[][] deepCopy(int[][] original) {
		int[][] copy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			System.arraycopy(original[i], 0, copy[i], 0, M);
		}
		
		return copy;
	}
	
	public static void monitor1(int x, int y, int direction, int[][] tempOffice) {		
		switch (direction) {
			case 0: 
				right(x, y, tempOffice); 
				break;
			case 1:
				down(x, y, tempOffice);
				break;
			case 2:
				left(x, y, tempOffice);
				break;
			case 3:
				up(x, y, tempOffice);
				break;
		}
	}
	
	public static void monitor2(int x, int y, int direction, int[][] tempOffice) {		
		switch (direction) {
			case 0: 
				right(x, y, tempOffice);
				left(x, y, tempOffice);
				break;
			case 1:
				up(x, y, tempOffice);
				down(x, y, tempOffice);
				break;
		}
	}
	
	public static void monitor3(int x, int y, int direction, int[][] tempOffice) {		
		switch (direction) {
			case 0: 
				up(x, y, tempOffice);
				right(x, y, tempOffice);
				break;
			case 1:
				right(x, y, tempOffice);
				down(x, y, tempOffice);
				break;
			case 2:
				down(x, y, tempOffice);
				left(x, y, tempOffice);
				break;
			case 3:
				left(x, y, tempOffice);
				up(x, y, tempOffice);
				break;
		}
	}
	
	public static void monitor4(int x, int y, int direction, int[][] tempOffice) {		
		switch (direction) {
			case 0: 
				left(x, y, tempOffice);
				up(x, y, tempOffice);
				right(x, y, tempOffice);
				break;
			case 1:
				up(x, y, tempOffice);
				right(x, y, tempOffice);
				down(x, y, tempOffice);
				break;
			case 2:
				right(x, y, tempOffice);
				down(x, y, tempOffice);
				left(x, y, tempOffice);
				break;
			case 3:
				down(x, y, tempOffice);
				left(x, y, tempOffice);
				up(x, y, tempOffice);
				break;
		}
	}
	
	public static void monitor5(int x, int y, int[][] tempOffice) {		
		right(x, y, tempOffice);
		down(x, y, tempOffice);
		up(x, y, tempOffice);
		left(x, y, tempOffice);
	}
	
	public static void right(int x, int y, int[][] tempOffice) {
		for (int i = y + 1; i < M; i++) {
			if (tempOffice[x][i] == 6) {
				break;
			} else if (tempOffice[x][i] == 0) {
				tempOffice[x][i] = 7;
			}
		}
	}
	
	public static void left(int x, int y, int[][] tempOffice) {
		for (int i = y - 1; i >= 0; i--) {
			if (tempOffice[x][i] == 6) {
				break;
			} else if (tempOffice[x][i] == 0) {
				tempOffice[x][i] = 7;
			}
		}
	}
	
	public static void up(int x, int y, int[][] tempOffice) {
		for (int i = x - 1; i >= 0; i--) {
			if (tempOffice[i][y] == 6) {
				break;
			} else if (tempOffice[i][y] == 0) {
				tempOffice[i][y] = 7;
			}
		}
	}
	
	public static void down(int x, int y, int[][] tempOffice) {
		for (int i = x + 1; i < N; i++) {
			if (tempOffice[i][y] == 6) {
				break;
			} else if (tempOffice[i][y] == 0) {
				tempOffice[i][y] = 7;
			}
		}
	}
	
	public static int getSize(int[][] tempOffice) {
		int size = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tempOffice[i][j] == 0) {
					size++;
				}
			}
		}
		
		return size;
	}
	
	static class Point {
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}