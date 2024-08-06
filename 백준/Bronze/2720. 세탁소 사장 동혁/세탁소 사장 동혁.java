import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		int[] coin = new int[4];
		
		for (int t = 0; t < T; t++) {
			int C = Integer.parseInt(br.readLine());
			
			coin[0] = C / 25;
			C %= 25;
			
			coin[1] = C / 10;
			C %= 10;
			
			coin[2] = C / 5;
			C %= 5;
			
			coin[3] = C / 1;
			
			for (int i = 0; i < 4; i++) {
				bw.write(coin[i] + " ");
			}
			bw.write("\n");
		}
		
		bw.flush();
	}
}