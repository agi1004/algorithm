import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] S = br.readLine().toCharArray();
		int[] counts = new int[26];
		
		for (int i = 0; i < S.length; i++) {
			counts[S[i] - 'a']++;
		}
		
		for (int count : counts) {
			System.out.print(count + " ");
		}
	}
}