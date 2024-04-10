import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 도시의 개수
		int M = Integer.parseInt(st.nextToken());	// 버스 노선의 개수
		ArrayList<Edge> edges = new ArrayList<>();
		long[] distance = new long[N + 1];
		
		// 최단 거리 배열 초기화하기
		for (int i = 0; i <= N; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		// 에지 리스트에 데이터 저장하기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	// 시작 도시
			int B = Integer.parseInt(st.nextToken());	// 도착 도시
			int C = Integer.parseInt(st.nextToken());	// 이동 시간 (양수가 아닐 수도 있음)
			edges.add(new Edge(A, B, C));
		}
		
		// 벨만 포드 알고리즘 수행하기
		distance[1] = 0;
		for (int i = 0; i < N - 1; i++) {	// 엣지를 모두 사용하는 것을 N - 1번 반복
			for (int j = 0; j < M; j++) {
				Edge edge = edges.get(j);
				// 더 작은 최단 거리가 있을 때 업데이트하기
				if (distance[edge.start] != Integer.MAX_VALUE
						&& distance[edge.end] > distance[edge.start] + edge.time) {
					distance[edge.end] = distance[edge.start] + edge.time;
				}
			}
		}
		
		// 음수 사이클 찾는 방법:
		// 엣지를 모두 사용하는 것을 1번만 더 해보고
		// distance 배열의 값이 변경되면 음수 사이클
		for (int i = 0; i < M; i++) {
			Edge edge = edges.get(i);
			if (distance[edge.start] != Integer.MAX_VALUE
					&& distance[edge.end] > distance[edge.start] + edge.time) {
				System.out.println(-1);
				return;	// 음수 사이클 발견되면 메인함수 바로 종료
			}
		}
		
		// 음수 사이클이 없을 때는 2번 도시부터 최단거리 출력
		for (int i = 2; i <= N; i++) {
			if (distance[i] != Integer.MAX_VALUE) {
				System.out.println(distance[i]);
			} else {
				System.out.println(-1);
			}
		}
	}
}

class Edge {
	int start, end, time;
	
	Edge(int start, int end, int time) {
		this.start = start;
		this.end = end;
		this.time = time;
	}
}