import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class City implements Comparable<City> {
	int number, cost;
	
	City(int number, int cost) {
		this.number = number;
		this.cost = cost;
	}
	
	public int compareTo(City city) {
		if (this.cost > city.cost)
			return 1;
		else
			return -1;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());	// 도시의 개수
		int M = Integer.parseInt(br.readLine());	// 버스의 개수
		ArrayList<City>[] graph = new ArrayList[N + 1];
		boolean[] visited = new boolean[N + 1];
		int[] price = new int[N + 1];	// 최소 비용 배열
		PriorityQueue<City> pq = new PriorityQueue<City>();
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<City>();
		}
		
		for (int i = 1; i <= N; i++) {
			price[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int arrive = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[start].add(new City(arrive, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());	// 출발 도시
		int B = Integer.parseInt(st.nextToken());	// 도착 도시

		pq.add(new City(A, 0));
		price[A] = 0;
		
		while (!pq.isEmpty()) {
			City now = pq.poll();
			int now_number = now.number;
			if (visited[now_number])
				continue;
			visited[now_number] = true;
			
			for (City next : graph[now_number]) {
				int next_number = next.number;
				int next_cost = next.cost;
				if (price[next_number] > price[now_number] + next_cost) {
					price[next_number] = price[now_number] + next_cost;
					pq.add(new City(next_number, price[next_number]));
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (i == B)
				System.out.println(price[i]);
		}
	}
}