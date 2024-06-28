import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static int A, B;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		System.out.println(bfs());
		sc.close();
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