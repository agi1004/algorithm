import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] dx = new int[] { 1, 0, -1, 0 };	// x의 상하좌우
	static int[] dy = new int[] { 0, 1, 0, -1 };	// y의 상하좌우
	static int N, M;
	static Queue<int[]> queue;
	static boolean[][] visited;
	static int[][] A;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		queue = new LinkedList<>();
		N = sc.nextInt();
		M = sc.nextInt();
		visited = new boolean[N][M];
		A = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(str.substring(j, j + 1));
			}
		}
		
		bfs(0, 0);
		
		System.out.println(A[N - 1][M - 1]);
	}
	
	public static void bfs(int a, int b) {
		queue.offer(new int[] {a, b});
		visited[a][b] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];
				int y = now[1] + dy[i];
				
				if (x >= 0 && y >= 0 && x < N && y < M) {
					if (!visited[x][y] && A[x][y] != 0) {
						queue.offer(new int[] {x, y});
						visited[x][y] = true;
						A[x][y] = A[now[0]][now[1]] + 1;
					}
				}
			}
			
			if (A[N - 1][M - 1] != 1)
				break;
		}
	}
}