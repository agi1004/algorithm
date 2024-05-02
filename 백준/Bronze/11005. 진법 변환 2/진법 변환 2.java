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
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int N = Integer.parseInt(st.nextToken());	// 10진법 수
		int B = Integer.parseInt(st.nextToken());	// B진법
		String result = "";
		
//		N = 0;
//		B = 8;
//		N % B = 3;
//		result = "053";
//		
//		N = 0;
//		B = 16;
//		N % B = 14;
//		result = "8E";
//		
//		N = 0;
//		B = 2;
//		N % B = 1;
//		result = "00010111";
        
		while (N != 0) {
			if (B >= 11 && N % B >= 10){
				result += (char)(N % B - 10 + 'A');
			} else {
				result += (char)(N % B + '0');
			}
			N /= B;
		}
		
		for (int i = result.length() - 1; i >= 0; i--) {
			bw.write(result.charAt(i));
		}
		
		bw.flush();
	}
}