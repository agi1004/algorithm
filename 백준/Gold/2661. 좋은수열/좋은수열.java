import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dfs("");
	}
	
	public static void dfs(String seq) {
		if (seq.length() == N) {
			System.out.println(seq);
			System.exit(0);
		}
		
		for (int i = 1; i <= 3; i++) {
			if (isGoodSequence(seq + i)) {
				dfs(seq + i);
			}
		}
	}
	
	public static boolean isGoodSequence(String seq) {
		int length = seq.length();
		
		for (int i = 1; i <= length / 2; i++) {
			if (seq.substring(length - i).equals(seq.substring(length - 2 * i, length - i))) {
				return false;
			}
		}
		
		return true;
	}
}