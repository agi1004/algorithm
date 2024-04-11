import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[][][] w = new int[21][21][21];
		
		// 0 ~ 20 사이만 탐색하면 됨
		for (int i = 0; i <= 20; i++) {
			for (int j = 0; j <= 20; j++) {
				for (int k = 0; k <= 20; k++) {
					if (i == 0 || j == 0 || k == 0) {
						w[i][j][k] = 1;
					} else if (i < j && j < k) {
						w[i][j][k] = w[i][j][k - 1] + w[i][j - 1][k - 1] - w[i][j - 1][k];
					} else {
						w[i][j][k] = w[i - 1][j][k] + w[i - 1][j - 1][k] 
									+ w[i - 1][j][k - 1] - w[i - 1][j - 1][k - 1];
					}
				}
			}
		}
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if (a == -1 && b == -1 && c == -1) break;
			
			bw.write("w(" + a + ", " + b + ", " + c + ") = ");
			
			if (a <= 0 || b <= 0 || c <= 0) {			// a or b or c가 0 이하일 때
				bw.write("1\n");
			} else if (a > 20 || b > 20 || c > 20) {	// a or b or c가 21 이상일 때
				bw.write(w[20][20][20] + "\n");
			} else {									// a or b or c가 0 ~ 20 사이일 때
				bw.write(w[a][b][c] + "\n");
			}
			
			bw.flush();
		}
	}	
}