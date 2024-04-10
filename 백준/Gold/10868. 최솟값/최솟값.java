import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 데이터 수
		int M = Integer.parseInt(st.nextToken());	// 쌍 (질의 수)
		
		int k = 0;
		while (true) {
			if (Math.pow(2, k) >= N) {
				break;
			}
			k++;
		}
		
		int size = (int)Math.pow(2, k) * 2;
		int[] tree = new int[size];
		
		for (int i = 1; i <= N; i++) {
			int index = i + (int)Math.pow(2, k) - 1;
			tree[index] = Integer.parseInt(br.readLine()); 
		}
		
		for (int i = size - 1; i >= 2; i -= 2) {
			if (i != 1) {
				if (tree[i] == 0) {
					tree[i / 2] = tree[i - 1];
				} else if (tree[i - 1] == 0){
					tree[i / 2] = tree[i];
				} else {
					tree[i / 2] = Math.min(tree[i], tree[i - 1]);
				}
			}
		}
				
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int start_index = a + (int)Math.pow(2, k) - 1;
			int end_index = b + (int)Math.pow(2, k) - 1;
			
			int min = Integer.MAX_VALUE;	// 구해야할 값
			
			while (start_index <= end_index) {
				if (start_index % 2 == 1) {
					min = Math.min(min, tree[start_index]);
				}
				if (end_index % 2 == 0) {
					min = Math.min(min, tree[end_index]);
				}
				
				start_index = (start_index + 1) / 2;
				end_index = (end_index - 1) / 2;
			}
			
			System.out.println(min);
		}	
	}
}