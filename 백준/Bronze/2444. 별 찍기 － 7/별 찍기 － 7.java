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
		
		// 첫째 줄부터 N번째 줄까지
		for (int i = 0; i < N; i++) {
			// 공백
			// OOOO
			// OOO
			// OO
			// O
			for (int j = N - 1; j > i; j--) {
				bw.write(" ");
			}
			// 별
		    //     *
		    //    **
		    //   ***
		   	//  ****
		  	// *****
			for (int j = 0; j <= i; j++) {
				bw.write("*");
			}
			// 별
			//
			// *
			// **
			// ***
			// ****
			for (int j = 0; j < i; j++) {
				bw.write("*");
			}
			bw.write("\n");
		}
		
		// N + 1번째 줄부터 2 * N - 1번째 줄까지
		for (int i = 0; i < N - 1; i++) {
			// 공백
			// O
			// OO
			// OOO
			// OOOO
			for (int j = 0; j <= i; j++) {
				bw.write(" ");
			}
			// 별
			// ****
			//  ***
			//   **
			//    *
			for (int j = N - 1; j > i; j--) {
				bw.write("*");
			}
			// 별
			//
			// ***
			// **
			// *
			for (int j = N - 2; j > i; j--) {
				bw.write("*");
			}
			bw.write("\n");
		}
		
		bw.flush();
	}
}