import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw;
	static int N, M;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		// 중복을 허용하므로 굳이 visited 배열을 만들 필요 없음
		
		dfs(0);
		bw.flush();
	}
	
	public static void dfs(int depth) throws IOException {
		if (depth == M) {
			for (int num : arr) {
				bw.write(num + " ");
			}
			bw.write("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			// 중복을 허용하므로 굳이 방문 체크를 할 필요 없음
			arr[depth] = i;
			dfs(depth + 1);
		}
	}
}