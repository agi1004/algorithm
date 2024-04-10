import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] S = new long[N + 1];	// 합 배열
		long[] C = new long[M];		// 나머지 배열. 나머지 인덱스: 0 ~ M - 1
		long count = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			S[i] = S[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		// 합 배열 순찰
		for (int i = 1; i <= N; i++) {
			int remainder = (int)(S[i] % M);	// 나머지 구하기
			if (remainder == 0)		// 나머지가 0이라면
				count++;			// 갯수에 추가
			C[remainder]++;			// 나머지 갯수에 추가. 인덱스: 나머지
		}
		
		// 나머지 배열 순찰
		for (int i = 0; i < M; i++) {
			if (C[i] > 1) {	// 나머지 갯수가 1개 이상이면
				count += (C[i] * (C[i] - 1)) / 2;		// 2개 선택하여 조합 가능. 조합 공식: n(n-1)/2
			}
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		bw.write(count + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}