import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 도시의 개수
		int M = Integer.parseInt(br.readLine());	// 버스의 개수
		int[][] D = new int[N + 1][N + 1];			// 최단 거리 배열
		
		// 최단 거리 배열 초기화
		// 시작 도시와 도착 도시가 같을 경우 빼고, 나머지는 무한대로 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j) {
					D[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		// 최단 거리 배열 데이터 받기
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	// 시작 도시
			int B = Integer.parseInt(st.nextToken());	// 도착 도시
			int C = Integer.parseInt(st.nextToken());	// 비용
			// 현재 최단 거리 배열의 값보다 새로 들어오는 값(가중치)이 더 작다면 새로 들어오는 값(가중치)으로 업데이트
			D[A][B] = Math.min(D[A][B], C);
		}
		
		// 플로이드 워셜 알고리즘 수행
		for (int K = 1; K <= N; K++) {	// K를 N만큼 반복
			for (int S = 1; S <= N; S++) {	// S를 N만큼 반복
				for (int E = 1; E <= N; E++) {	// E를 N만큼 반복
					// K 인덱스가 포함된 최단 거리 배열의 값이 무한대가 아니라면
					// -> 최솟값을 이것으로 바꿔야 하기 때문에 사전검증 꼭 필요
					if (D[S][K] != Integer.MAX_VALUE && D[K][E] != Integer.MAX_VALUE) {
						// 플로이드 워셜 공식 사용
						D[S][E] = Math.min(D[S][E], D[S][K] + D[K][E]); 
					}
				}
			}
		}
		
		// 최단 거리 출력
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (D[i][j] == Integer.MAX_VALUE)
					System.out.print(0 + " ");
				else
					System.out.print(D[i][j] + " ");
			}
			System.out.println();
		}
	}
}