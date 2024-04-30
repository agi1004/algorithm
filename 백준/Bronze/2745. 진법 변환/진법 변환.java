import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String N = st.nextToken();	
		int B = Integer.parseInt(st.nextToken());
		int result = 0;
		
		int n = N.length() - 1;
		for (int i = 0; i < N.length(); i++) {
			if (N.charAt(i) >= 'A') {
				result += (N.charAt(i) - 55) * Math.pow(B, n--); 
			} else {
				result += (N.charAt(i) - '0') * Math.pow(B, n--);
			}
		}
		
		System.out.println(result);
	}
}