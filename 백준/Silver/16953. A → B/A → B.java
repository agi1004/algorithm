import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int A, B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		System.out.println(bfs());
	}
	
	public static int bfs() {
		Queue<Long> queue = new LinkedList<>();
		Set<Long> visited = new HashSet<>();
		int depth = 1;
		
		queue.add((long)A);
		visited.add((long)A);
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				long now = queue.poll();
				
				if (now == B) {
					return depth;
				}
				
				long next1 = now * 2;
				if (next1 <= B && !visited.contains(next1)) {
					queue.add(next1);
					visited.add(next1);
				}
				
				long next2 = now * 10 + 1;
				if (next2 <= B && !visited.contains(next2)) {
					queue.add(next2);
					visited.add(next2);
				}
			}
			
			depth++;
		}
		
		return -1;
	}
}