import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, K, X;
	static ArrayList<ArrayList<Integer>> graph;
	static Queue<Integer> queue;
	static boolean[] visited;
	static int[] distance;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	// 도시의 개수
		M = sc.nextInt();	// 도로의 개수
		K = sc.nextInt();	// 거리 정보
		X = sc.nextInt();	// 출발 도시의 번호
		graph = new ArrayList<>();
		queue = new LinkedList<>();
		visited = new boolean[N + 1];
		distance = new int[N + 1];
		
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			graph.get(A).add(B);	// 단방향 그래프이므로 한 번만 삽입
		}
		
		distanceOfCity(X);
		
		boolean isEqualToK = false;
		for (int i = 1; i <= N; i++) {
			if (distance[i] == K) {
				System.out.println(i);
				isEqualToK = true;
			}
		}
		
		if (!isEqualToK) {
			System.out.println(-1);
		}
		
		sc.close();
	}
	
	public static void distanceOfCity(int city) {
		queue.add(city);
		visited[city] = true;
		
		while (!queue.isEmpty()) {
			int x = queue.poll();
			
			for (int i = 0; i < graph.get(x).size(); i++) {
				int y = graph.get(x).get(i);
				
				if (!visited[y]) {
					queue.add(y);
					visited[y] = true;
					distance[y] = distance[x] + 1;	// 큐에서 뽑은 현재 도시의 거리에서 1 증가
				}
			}
		}
	}
}