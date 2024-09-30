import java.io.*;

public class Main {
	static int N;
	static int[][] video;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		video = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				video[i][j] = line.charAt(j) - '0';
			}
		}
		
		compress(0, 0, N);
		
		System.out.println(sb);
	}
	
	public static void compress(int x, int y, int size) {
		if (isSameColor(x, y, size)) {
			sb.append(video[x][y]);
			return;
		}
		
		sb.append("(");
		
		int newSize = size / 2;
		
		compress(x, y, newSize);						// 왼쪽 위
		compress(x, y + newSize, newSize);				// 오른쪽 위
		compress(x + newSize, y, newSize);				// 왼쪽 아래
		compress(x + newSize, y + newSize, newSize);	// 오른쪽 아래
		
		sb.append(")");
	}
	
	public static boolean isSameColor(int x, int y, int size) {
		int color = video[x][y];
		
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (video[i][j] != color) {
					return false;
				}
			}
		}
		
		return true;
	}
}