import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 도시 개수
		int M = Integer.parseInt(st.nextToken());	// 도로의 수
		int K = Integer.parseInt(st.nextToken());	// K번째 최단경로
		ArrayList<Dosi>[] graph = new ArrayList[N + 1];
		PriorityQueue<Integer>[] distanceQueue = new PriorityQueue[N + 1];
		Comparator<Integer> cp = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1 < o2 ? 1 : -1;
			}
		};
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Dosi>();
		}
		
		for (int i = 0; i <= N; i++) {
			distanceQueue[i] = new PriorityQueue<Integer>(K, cp);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Dosi(b, c));
		}
		
		PriorityQueue<Dosi> pq = new PriorityQueue<Dosi>();
		pq.add(new Dosi(1, 0));
		distanceQueue[1].add(0);
		
		while (!pq.isEmpty()) {
			Dosi now = pq.poll();
			
			for (Dosi next : graph[now.city]) {
				if (distanceQueue[next.city].size() < K) {
					distanceQueue[next.city].add(now.time + next.time);
					pq.add(new Dosi(next.city, now.time + next.time));
				} 
				else if (distanceQueue[next.city].peek() > now.time + next.time) {
					distanceQueue[next.city].poll();
					distanceQueue[next.city].add(now.time + next.time);
					pq.add(new Dosi(next.city, now.time + next.time));
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (distanceQueue[i].size() == K) {
				System.out.println(distanceQueue[i].peek());
			}
			else {
				System.out.println(-1);
			}
		}
	}
}

class Dosi implements Comparable<Dosi> {
	int city, time;
	
	Dosi(int city, int time) {
		this.city = city;
		this.time = time;
	}
	
	public int compareTo(Dosi dosi) {
		if (this.time > dosi.time)
			return 1;
		else
			return -1;
	}
}