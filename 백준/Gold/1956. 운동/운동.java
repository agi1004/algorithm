import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());	// 마을 개수
		int E = Integer.parseInt(st.nextToken());	// 도로 개수
		long[][] distance = new long[V + 1][V + 1];
		
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i != j) {
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			distance[a][b] = c;
		}
		
		for (int k = 1; k <= V; k++) {
			for (int s = 1; s <= V; s++) {
				for (int e = 1; e <= V; e++) {
					if (s == e) {
						continue;
					}
					if (distance[s][e] > distance[s][k] +  distance[k][e]) {
						distance[s][e] = distance[s][k] + distance[k][e];
					}
				}
			}
		}
		
		long result = Integer.MAX_VALUE;
		
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j) {
					continue;
				}
				if (distance[i][j] != Integer.MAX_VALUE && distance[i][j] != Integer.MAX_VALUE) {
					result = Math.min(result, distance[i][j] + distance[j][i]);
				}
			}
		}
		
		if (result != Integer.MAX_VALUE) {
			System.out.println(result);
		} else {
			System.out.println(-1);
		}
	}
}