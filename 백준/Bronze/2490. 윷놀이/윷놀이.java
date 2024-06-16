import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] yut = new int[3][4];
		int[] count = new int[3];
		
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				yut[i][j] = Integer.parseInt(st.nextToken());
				if (yut[i][j] == 0) {
					count[i]++;
				}
			}
		}
		
		for (int i = 0; i < 3; i++) {
			switch (count[i]) {
				case 1:
					System.out.println("A");
					break;
				case 2:
					System.out.println("B");
					break;
				case 3:
					System.out.println("C");
					break;
				case 4:
					System.out.println("D");
					break;
				case 0:
					System.out.println("E");
					break;
			}
		}
	}
}