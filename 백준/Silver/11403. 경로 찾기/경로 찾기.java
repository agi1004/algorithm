import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 정점의 개수
		int[][] distance = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				distance[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int K = 1; K <= N; K++) {
			for (int S = 1; S <= N; S++) {
				for (int E = 1; E <= N; E++) {
					// S에서 K로 갈 수 있는 경로와 K에서 E로 갈 수 있는 경로가 둘 다 있다면
					if (distance[S][K] == 1 && distance[K][E] == 1) 
						distance[S][E] = 1;	// S에서 E로 갈 수 있으므로 1로 업데이트
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(distance[i][j] + " ");
			}
			System.out.println();
		}
	}
}