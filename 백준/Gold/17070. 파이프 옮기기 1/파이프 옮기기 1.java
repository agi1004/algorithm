import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] house;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		house = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(move(1, 2, 0));
	}
	
	public static int move(int x, int y, int d) {
		Queue<Pipe> queue = new LinkedList<>();
		int count = 0;
		
		queue.add(new Pipe(x, y, d));
		
		while (!queue.isEmpty()) {
			Pipe now = queue.poll();
			
			if (now.x == N && now.y == N) {
				count++;
				continue;
			}
			
			if (now.d == 0 || now.d == 2) {
				if (validate(now.x, now.y + 1)) {
					queue.add(new Pipe(now.x, now.y + 1, 0));
				}
			}
			
			if (now.d == 1 || now.d == 2) {
				if (validate(now.x + 1, now.y)) {
					queue.add(new Pipe(now.x + 1, now.y, 1));
				}
			}
			
			if (validate(now.x, now.y + 1) && validate(now.x + 1, now.y) && validate(now.x + 1, now.y + 1)) {
				queue.add(new Pipe(now.x + 1, now.y + 1, 2));
			}
		}
		
		return count;
	}
	
	public static boolean validate(int x, int y) {
		return x <= N && y <= N && house[x][y] == 0;
	}
	
	static class Pipe {
		int x, y, d;
		
		Pipe (int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}