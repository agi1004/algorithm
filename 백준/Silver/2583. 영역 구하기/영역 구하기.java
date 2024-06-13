import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N;					// 세로, 가로 길이
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static int[][] paper;				// 2차원 모눈종이 배열
	static boolean[][] visited;			// 2차원 방문 배열
	static int[][] count;				// 2차원 영역 카운트 배열
	static PriorityQueue<Integer> area = new PriorityQueue<>();	// 영역 넓이 저장하는 우선순위 큐
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());	
		N = Integer.parseInt(st.nextToken());	
		int K = Integer.parseInt(st.nextToken());	// 직사각형 개수
		paper = new int[N][M];
		visited = new boolean[N][M];
		count = new int[N][M];
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());	// 왼쪽 아래 꼭짓점의 x좌표
			int y1 = Integer.parseInt(st.nextToken());	// 왼쪽 아래 꼭짓점의 y좌표
			int x2 = Integer.parseInt(st.nextToken());	// 오른쪽 아래 꼭짓점의 x좌표
			int y2 = Integer.parseInt(st.nextToken());	// 오른쪽 아래 꼭짓점의 y좌표

			for (int i = x1; i < x2; i++) {
				for (int j = y1; j < y2; j++) {
					paper[i][j] = 1;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && paper[i][j] == 0) {
					bfs(i, j);
				}
			}
		}
		
		bw.write(area.size() + "\n");
		
		while (!area.isEmpty()) {
			bw.write(area.poll() + " ");
		}
		
		bw.flush();
	}
	
	public static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {a, b});
		visited[a][b] = true;
		count[a][b] = 1;
		int cnt = 2;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];
				int y = now[1] + dy[i];
				
				if (x < 0 || y < 0 || x >= N || y >= M) {
					continue;
				}
				
				if (visited[x][y] || paper[x][y] == 1) {
					continue;
				}
				
				queue.add(new int[] {x, y});
				visited[x][y] = true;
				count[x][y] = cnt++;
				a = x;
				b = y;
			}
		}
		
		area.add(count[a][b]);
	}
}