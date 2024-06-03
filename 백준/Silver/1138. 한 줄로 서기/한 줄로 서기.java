import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 사람의 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] count = new int[N + 1];	
		ArrayList<Integer> line = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			// 키가 i인 사람보다 큰 사람이 왼쪽에 count[i]명
			count[i] = Integer.parseInt(st.nextToken());	
		}
		
		// 4보다 큰 애는 앞에 0개 => line.add(0, 4) => 4
		// 3보다 큰 애는 앞에 1개 => line.add(1, 3) => 4 3
		// 2보다 큰 애는 앞에 1개 => line.add(1, 2) => 4 2 3
		// 1보다 큰 애는 앞에 2개 => line.add(2, 1) => 4 2 1 3
		for (int i = N; i >= 1; i--) {
			line.add(count[i], i);	// count[i]번째로 키가 i인 사람 줄 세우기
		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(line.get(i) + " ");	// 줄을 선 순서대로 키 출력
		}
	}
}