import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static char[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C];
		visited = new boolean[C];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr);
		dfs(0, 0);
	}
	
	public static void dfs(int depth, int index) throws IOException {
		if (depth == L) {
			char[] temp = new char[L];
			int j = 0;
			
			for (int i = 0; i < C; i++) {
				if (visited[i]) {
					temp[j++] = arr[i];
				}
			}
			
			boolean vowel = false;
			int consonant = 0;
			
			for (int i = 0; i < L; i++) {
				if (temp[i] == 'a' || temp[i] == 'e' || temp[i] == 'i'
					|| temp[i] == 'o' || temp[i] == 'u') {
					vowel = true;
				} else {
					consonant++;
				}
			}
			
			if (vowel && consonant >= 2) {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				String result = new String(temp);
				bw.write(result + "\n");
				bw.flush();
			}
			
			return;
		}
		
		for (int i = index; i < C; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(depth + 1, i + 1);
				visited[i] = false;
			}
		}
	}
}