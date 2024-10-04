import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		
		while (S.length() < T.length()) {
			// 1. 문자열의 뒤에 A를 빼기
			if (T.charAt(T.length() - 1) == 'A') {
				T = T.substring(0, T.length() - 1);
				continue;
			}
			
			// 2. 문자열을 뒤집고 앞에 B를 빼기
			if (new StringBuilder(T).reverse().charAt(0) == 'B') {
				T = new StringBuilder(T).reverse().substring(1);
			}
		}
		
		if (S.equals(T)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
}