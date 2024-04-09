import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Node1>[] graph;
	static int[] distance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 정점의 개수
		int E = Integer.parseInt(st.nextToken());	// 간선의 개수
		graph = new ArrayList[N + 1];
		distance = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	// a번 정점
			int b = Integer.parseInt(st.nextToken());	// b번 정점
			int c = Integer.parseInt(st.nextToken());	// a번 정점과 b번 정점 사이의 거리
			graph[a].add(new Node1(b, c));	// 양방향
			graph[b].add(new Node1(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());	// 반드시 거쳐야 하는 정점1
		int v2 = Integer.parseInt(st.nextToken());	// 반드시 거쳐야 하는 정점2
		
		long res1 = 0, res2 = 0;
		
		res1 += dijkstra(1, v1);	// 1부터 v1까지의 최단거리
		res1 += dijkstra(v1, v2);	// v1부터 v2까지의 최단거리
		res1 += dijkstra(v2, N);	// v2부터 N까지의 최단거리
		
		res2 += dijkstra(1, v2);	// 1부터 v2까지의 최단거리
		res2 += dijkstra(v2, v1);	// v1부터 v2까지의 최단거리
		res2 += dijkstra(v1, N);	// v1부터 N까지의 최단거리
		
		// 결과값 중 둘 중 하나라도 최댓값을 넘는다면 v1과 v2에 접근 불가능한 것이므로
		if (res1 >= Integer.MAX_VALUE || res2 >= Integer.MAX_VALUE) {
			System.out.println(-1);	// -1 리턴
		} else {	// v1과 v2에 접근 가능하다면
			System.out.println(Math.min(res1, res2));	// 결괏값 두개 중 더 작은 값 리턴
		}
	}
	
	public static int dijkstra(int start, int end) {
		Arrays.fill(distance, Integer.MAX_VALUE);	// 최단거리 배열 모두 초기화
		PriorityQueue<Node1> pq = new PriorityQueue<>();
		pq.add(new Node1(start, 0));	// 맨 처음 우선순위 큐에 삽입
		distance[start] = 0;			// 시작 정점까지의 최단거리는 0
		
		while (!pq.isEmpty()) {
			Node1 now = pq.poll();
			
			for (Node1 next : graph[now.vertex]) {	
				// 최단 거리 값으로 초기화 후 우선순위 큐에 삽입
				if (distance[next.vertex] > distance[now.vertex] + next.weight) {
					distance[next.vertex] = distance[now.vertex] + next.weight;
					pq.add(new Node1(next.vertex, distance[next.vertex]));
				}
			}
		}
		
		return distance[end];	// 도착 정점까지의 최단거리 리턴
	}
}

class Node1 implements Comparable<Node1> {
	int vertex, weight;
	
	Node1(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
	
	public int compareTo(Node1 node) {
		return this.weight - node.weight;
	}
}