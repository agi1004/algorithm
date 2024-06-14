import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;					// 도화지의 세로, 가로 크기
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static int[][] paper;				// 2차원 도화지 배열
	static boolean[][] visited;			// 2차원 방문 배열
	static int maxArea = 0;				// 가장 넓은 그림의 넓이
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	// 도화지의 세로 크기 입력 받기
		m = Integer.parseInt(st.nextToken());	// 도화지의 가로 크기 입력 받기
		paper = new int[n][m];					// 2차원 도화지 배열 크기 초기화
		visited = new boolean[n][m];			// 2차원 방문 배열 크기 초기화
		int count = 0;							// 그림의 개수
		
		// 2차원 도화지 배열 초기화
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				// 그림이 있는 경우: 1, 없는 경우: 0
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 해당 칸을 방문하지 않았거나, 그림이 있는 칸이라면
				if (!visited[i][j] && paper[i][j] != 0) {
					count++;		// 그림의 개수 1 증가
					bfs(i, j);		// 해당 칸부터 bfs 수행
				}
			}
		}
		
		System.out.println(count);		// 그림의 개수 출력
		System.out.println(maxArea);	// 가장 넓은 그림의 넓이 출력
	}
	
	public static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();	// bfs 수행을 위한 큐
		queue.add(new int[] {a, b});	// 큐에 해당 칸 삽입
		visited[a][b] = true;			// 해당 칸을 방문 체크		
		int area = 2;					// 해당 칸은 1이고, 다음 칸부터 넓이는 2로 카운트하면서 점점 증가
		
		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			int[] now = queue.poll();	// 현재 칸을 큐에서 뽑기
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];		// 인접 칸의 x좌표
				int y = now[1] + dy[i];		// 인접 칸의 y좌표
				
				// 인접 칸의 x좌표나 y좌표가 유효한 인덱스가 아니라면 건너뛰기
				if (x < 0 || y < 0 || x >= n || y >= m) {
					continue;
				}
				
				// 인접 칸을 방문했거나, 그림이 있는 칸이 아니라면 건너뛰기
				if (visited[x][y] || paper[x][y] == 0) {
					continue;
				}
				
				queue.add(new int[] {x, y});	// 큐에 인접 칸 삽입
				visited[x][y] = true;			// 인접 칸을 방문 체크
				paper[x][y] = area++;			// 인접 칸을 area로 바꾸고 1 증가 => 넓이 체크
				a = x;							// 가장 최근 방문한 x좌표를 인자에 대입
				b = y;							// 가장 최근 방문한 y좌표를 인자에 대입
			}
		}
		
		// 가장 넓은 그림의 넓이 갱신
		// a: 가장 최근 방문한 x좌표, b: 가장 최근 방문한 y좌표
		maxArea = Math.max(maxArea, paper[a][b]);	
	}
}