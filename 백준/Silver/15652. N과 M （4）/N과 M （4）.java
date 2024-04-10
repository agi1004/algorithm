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
		
		dfs(1, 0);
		bw.flush();
	}
	
	public static void dfs(int start, int depth) throws IOException {
		if (depth == M) {
			for (int num : arr) {
				bw.write(num + " ");
			}
			bw.write("\n");
			return;
		}
		
		// 비내림차순(A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK) 정렬을 시키기 위해
		// i는 무조건 이전 깊이의 값보다 큰 값부터 탐색
		for (int i = start; i <= N; i++) {
			arr[depth] = i;
			dfs(i, depth + 1);	// 같은 값을 허용하기 때문에 start는 현재 값으로 재귀호출
		}
	}
}