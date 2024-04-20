import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		String reverse = "";
		
		for (int i = word.length() - 1; i >= 0; i--) {
			reverse += word.charAt(i);
		}
		
		if (word.equals(reverse)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
}