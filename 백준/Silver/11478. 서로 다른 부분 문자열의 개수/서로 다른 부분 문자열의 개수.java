import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		HashSet<String> partStr = new HashSet<>();
		
		int size = 1;
		while (size <= S.length()) {
			for (int i = 0; i < S.length() - size; i++) {
				partStr.add(S.substring(i, i + size));
			}
			partStr.add(S.substring(S.length() - size));
			size++;
		}
		
		System.out.println(partStr.size());
	}
}