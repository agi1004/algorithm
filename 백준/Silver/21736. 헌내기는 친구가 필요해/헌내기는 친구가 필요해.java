import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;					// 캠퍼스의 세로, 가로 크기
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static char[][] campus;				// 2차원 캠퍼스 배열
	static boolean[][] visited;			// 2차원 방문 체크 배열				
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());	
		campus = new char[N][M];					
		visited = new boolean[N][M];				
		int x = -1, y = -1;		// 도연이의 현재 위치
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				campus[i][j] = line.charAt(j);
				if (campus[i][j] == 'I') {	// 해당 좌표에 있는 글자가 도연이에 해당한다면
					x = i;	// 도연이의 x좌표 위치 저장
					y = j;	// 도연이의 y좌표 위치 저장
				}
			}
		}
		
		int count = bfs(x, y);		// 도연이가 만날 수 있는 사람의 수
		
		if (count != 0) {
			System.out.println(count);
		} else {
			System.out.println("TT");
		}
	}
	
	public static int bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		int count = 0;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				
				if (visited[nx][ny] || campus[nx][ny] == 'X') {
					continue;
				}
				
				
				
				queue.add(new int[] {nx, ny});
				visited[nx][ny] = true;
				
				if (campus[nx][ny] == 'P') {
					count++;
				}
			}
		}
		
		return count;
	}
}