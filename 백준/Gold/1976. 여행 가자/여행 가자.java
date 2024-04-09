import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());	// 도시의 수
		int M = Integer.parseInt(br.readLine());	// 여행 계획에 속한 도시의 수
		int[][] graph = new int[N + 1][N + 1];
		int[] plan = new int[M];
		parent = new int[N + 1];
				
		// 도시 연결 데이터 저장하기
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 여행 도시 정보 저장하기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		
		// 대표 노드를 자기 자신으로 초기화하기
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		// 인접 행렬에서 도시가 연결돼 있으면 union 실행하기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (graph[i][j] == 1) {
					union(i, j);
				}
			}
		}
		
		// 여행 계획 도시들이 1개의 대표 도시로 연결돼 있는지 확인하기
		int index = find(plan[0]);
		for (int i = 1; i < M; i++) {
			if (index != find(plan[i])) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
	}
	
	// union 연산: 대표 노드끼리 연결하기
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) {
			parent[b] = a;
		}
	}
	
	// find 연산
	public static int find(int x) {
		if (x == parent[x]) {
			return x;
		} else {
			// 재귀 함수의 형태로 구현 -> 경로 압축 부분
			return parent[x] = find(parent[x]);
		}
	}
}