import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		int n = Integer.parseInt(stringTokenizer.nextToken());
		int m = Integer.parseInt(stringTokenizer.nextToken());
		long[][] D = new long[n + 1][n + 1];
		
		// 구간합으로 2차원 배열 채우기
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 1; j <= n; j++) {
				// 1행 합배열
				if (i == 1) {
					D[i][j] = D[i][j - 1] + Integer.parseInt(stringTokenizer.nextToken());
					continue;
				}
				// 1열 합배열
				if (j == 1) {
					D[i][j] = D[i - 1][j] + Integer.parseInt(stringTokenizer.nextToken());
					continue;
				}
				D[i][j] = D[i - 1][j] + D[i][j - 1] - D[i - 1][j - 1] + Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		// 구간합으로 답 구하기
		for (int q = 0; q < m; q++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int x1 = Integer.parseInt(stringTokenizer.nextToken());
			int y1 = Integer.parseInt(stringTokenizer.nextToken());
			int x2 = Integer.parseInt(stringTokenizer.nextToken());
			int y2 = Integer.parseInt(stringTokenizer.nextToken());
			System.out.println(D[x2][y2] - D[x1 - 1][y2] - D[x2][y1 - 1] + D[x1 - 1][y1 - 1]);
		}
	}
}