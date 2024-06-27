import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String N = br.readLine();
		
		for (int i = 0; i < N.length(); i++) {
			bw.write(N.charAt(i));
			if (i % 10 == 9) {
				bw.write("\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}