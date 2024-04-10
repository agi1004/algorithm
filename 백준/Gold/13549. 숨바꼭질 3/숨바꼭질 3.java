import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[] distance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 시작 지점
		int K = Integer.parseInt(st.nextToken());	// 도착 지점
		distance = new int[100001];		// 최단거리 배열
		
		if (N > K) {		// 시작 지점이 도착 지점보다 크면 -1로밖에 갈 방법이 없으므로
			System.out.println(N - K);	// 최소 시간은 N - K 출력
		} else {			// 시작 지점이 도착 지점보다 작다면
			dijkstra(N);	// 다익스트라 수행
			System.out.println(distance[K]);	// K로 가는 최단거리 출력
		}
	}
	
	public static void dijkstra(int start) {
		Arrays.fill(distance, Integer.MAX_VALUE);		// 최단거리 배열은 최댓값으로 초기화
		PriorityQueue<Nd> pq = new PriorityQueue<>();	// 다익스트라 수행을 위한 우선순위 큐
		pq.add(new Nd(start, 0));	// 우선순위 큐에 초깃값 넣기
		distance[start] = 0;		// 시작 지점으로 가는 최단 거리는 0
		
		while (!pq.isEmpty()) {
			Nd now = pq.poll();
			
			// 해당 지점의 최단거리가 해당 지점으로 가는 시간 가중치보다 작다면
			// 즉, 이미 값이 갱신되었으면 건너뛰기
			if (distance[now.vertex] < now.time) {
				continue;
			}
			
			int minus1 = now.vertex - 1;
			// 해당 지점 - 1로 가는 최단거리가 해당 지점으로 가는 시간 가중치 + 1보다 크다면
			if (validation(minus1) && distance[minus1] > now.time + 1) {
				distance[minus1] = now.time + 1;	// 최단거리 갱신 후
				pq.add(new Nd(minus1, distance[minus1]));	// 우선순위 큐에 삽입
			}
			
			int plus1 = now.vertex + 1;
			// 해당 지점 + 1로 가는 최단거리가 해당 지점으로 가는 시간 가중치 + 1보다 크다면
			if (validation(plus1) && distance[plus1] > now.time + 1) {
				distance[plus1] = now.time + 1;		// 최단거리 갱신 후
				pq.add(new Nd(plus1, distance[plus1]));		// 우선순위 큐에 삽입
			}
			
			int mul2 = now.vertex * 2;
			// 해당 지점 * 2로 가는 최단거리가 해당 지점으로 가는 시간 가중치보다 크다면
			if (validation(mul2) && distance[mul2] > now.time) {
				distance[mul2] = now.time;			// 최단거리 갱신 후
				pq.add(new Nd(mul2, distance[mul2]));	// 우선순위 큐에 삽입
			}
		}
	}
	
	// 인덱스 값 유효성 검사 (범위는 0 ~ 100000)
	public static boolean validation(int n) {
		if (n >= 0 && n <= 100000) {
			return true;
		} else {
			return false;
		}
	}
}

class Nd implements Comparable<Nd> {
	int vertex, time;
	
	Nd(int vertex, int time) {
		this.vertex = vertex;
		this.time = time;
	}
	
	public int compareTo(Nd node) {
		return this.time - node.time;
	}
}