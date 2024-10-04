import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			String[] tels = new String[N];
			
			for (int i = 0; i < N; i++) {
				tels[i] = br.readLine();
			}
			
			Arrays.sort(tels, (o1, o2) -> o1.length() - o2.length());
			
			Set<String> set = new HashSet<>();
			boolean cons = true;
			
			outer: for (String tel : tels) {
				for (int i = 1; i < tel.length(); i++) {
					if (set.contains(tel.substring(0, i))) {
						cons = false;
						break outer;
					}
				}
				set.add(tel);
			}
			
			if (cons) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
}