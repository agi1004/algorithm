import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 수의 개수
		int M = Integer.parseInt(st.nextToken());	// 수의 변경이 일어나는 횟수
		int K = Integer.parseInt(st.nextToken());	// 구간합을 구하는 횟수
		
		// 인덱스 트리 배열 크기 구하는 과정
		// 2^k >= N을 만족하는 k에 대해, 2^k * 2
		int k = 0;
		while (true) {
			if (Math.pow(2, k) >= N) {
				break;
			}
			k++;
		}
		int size = (int)Math.pow(2, k) * 2;	// 인덱스 트리 배열 크기
		long[] tree = new long[size];		// 인덱스 트리
		
		for (int i = 1; i <= N; i++) {
			// 트리 전용 인덱스로 변경: index + 2^k - 1
			int index = i + (int)Math.pow(2, k) - 1;
			// 트리 배열에 입력받은 수 삽입 (리프 노드에 입력받기)
			tree[index] = Long.parseLong(br.readLine());	
		}
		
		// 트리 배열 값 채워넣기 (구간합)
		// 맨 뒤 인덱스부터 차례로 내려오기. 인덱스는 2씩 감소
		for (int i = size - 1; i >= 2; i -= 2) {
			if (i != 1) {
				// 자식 노드의 값 두개를 더해서 부모 노드에 넣기
				tree[i / 2] = tree[i] + tree[i - 1];
			}
		}
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 	// 질의
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if (a == 1) {
				int index = b + (int)Math.pow(2, k) - 1;
				long diff = c - tree[index];	// 변경해야할 값 - 원래 값
				while (index > 0) {
					tree[index] += diff;	// (변경해야할 값 - 원래 값)을 부모 노드에 모두 업데이트
					index /= 2;
				}
			} else {
				long dSum = 0;	// 구간합
				int start_index = b + (int)Math.pow(2, k) - 1;
				int end_index = (int)c + (int)Math.pow(2, k) - 1;
				
				// end_index가 더 작아지면 종료
				// 즉, start_index가 end_index보다 더 작거나 같을 때까지만 수행
				while (start_index <= end_index) {
					// 노드 선택 (구간합을 구하면 되므로 더해주기)
					if (start_index % 2 == 1) {
						dSum += tree[start_index];
					}
					if (end_index % 2 == 0) {
						dSum += tree[end_index];
					}
					
					// 인덱스 교체 (부모 노드 쪽으로 올라가기)
					start_index = (start_index + 1) / 2;
					end_index = (end_index - 1) / 2;
				}
				
				System.out.println(dSum);
			}
		}		
	}
}