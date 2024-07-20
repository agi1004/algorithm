import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N;
	static int[] amazingPrime;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		amazingPrime = new int[N];
		
		for (int i = 2; i <= 9; i++) {
			if (isPrime(i)) {
				amazingPrime[0] = i;
				dfs(1);
			}
		}
	}
	
	public static void dfs(int depth) throws IOException {
		if (depth == N) {
			int result = 0;
			String resultStr = "";
			
			for (int i = 0; i < N; i++) {
				resultStr += String.valueOf(amazingPrime[i]);
			}
			
			result = Integer.parseInt(resultStr);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			bw.write(result + "\n");
			bw.flush();
			
			return;
		}
		
		int nowNum = 0;
		String nowNumStr = "";
		
		for (int i = 0; i < depth; i++) {
			nowNumStr += String.valueOf(amazingPrime[i]);
		}
		
		nowNum = Integer.parseInt(nowNumStr);
		
		int nextNum = 0;
		String nextNumStr = new String(nowNumStr);
		
		for (int i = 1; i <= 9; i++) {
			nextNumStr += String.valueOf(i);
			nextNum = Integer.parseInt(nextNumStr);
			if (isPrime(nextNum)) {
				amazingPrime[depth] = i;
				dfs(depth + 1);
			}
			nextNumStr = new String(nowNumStr);
		}
	}
	
	public static boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}