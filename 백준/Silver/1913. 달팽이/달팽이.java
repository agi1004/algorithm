import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int target = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		int range = 0;
		
		arr[N / 2][N / 2] = 1;
		
		while (N / 2 - range != 0) {
			int row = N / 2 - range;
			int col = N / 2 - range;
			
			arr[row - 1][col] = arr[row][col] + 1;
			row--;
			
			// 0 ~ 0 -> 1번 (range == 0)
			// 0 ~ 2 -> 3번 (range == 1) 
			// 0 ~ 4 -> 5번 (range == 2)
			// 0 ~ 6 -> 7번 (range == 3)
			for (int i = 0; i <= range * 2; i++) {
				arr[row][col + 1] = arr[row][col] + 1;
				col++;
			}
			
			// 0 ~ 1 -> 2번 (range == 0)
			// 0 ~ 3 -> 4번 (range == 1)
			// 0 ~ 5 -> 6번 (range == 2)
			// 0 ~ 7 -> 8번 (range == 3)
			for (int i = 0; i <= range * 2 + 1; i++) {
				arr[row + 1][col] = arr[row][col] + 1;
				row++;
			}
			
			// 0 ~ 1 -> 2번 (range == 0)
			// 0 ~ 3 -> 4번 (range == 1)
			// 0 ~ 5 -> 6번 (range == 2)
			// 0 ~ 7 -> 8번 (range == 3)
			for (int i = 0; i <= range * 2 + 1; i++) {
				arr[row][col - 1] = arr[row][col] + 1;
				col--;
			}
			
			// 0 ~ 1 -> 2번 (range == 0)
			// 0 ~ 3 -> 4번 (range == 1)
			// 0 ~ 5 -> 6번 (range == 2)
			// 0 ~ 7 -> 8번 (range == 3)
			for (int i = 0; i <= range * 2 + 1; i++) {
				arr[row - 1][col] = arr[row][col] + 1;
				row--;
			}
			
			range++;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.write(arr[i][j] + " ");
			}
			bw.write("\n");
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == target) {
					bw.write((i + 1) + " " + (j + 1) + "\n");
					bw.flush();
					return;
				}
			}
		}
	}
}