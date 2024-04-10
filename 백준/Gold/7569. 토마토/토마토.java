import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());	// 가로
		int N = Integer.parseInt(st.nextToken());	// 세로
		int H = Integer.parseInt(st.nextToken());	// 높이
		int[] dx = {-1, 0, 1, 0, 0, 0};				// 왼쪽, 오른쪽
		int[] dy = {0, -1, 0, 1, 0, 0};				// 앞, 뒤
		int[] dz = {0, 0, 0, 0, -1, 1};				// 위, 아래
		int[][][] matrix = new int[H][N][M];			// 3차원 토마토 상자
		boolean[][][] visited = new boolean[H][N][M];	// 3차원 방문 배열 
		Queue<int[]> queue = new LinkedList<>();	// bfs에 사용할 자료형이 배열인 큐
		
		// 토마토 상자 초기화
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					matrix[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		boolean flag = true;	// 토마토가 모두 익었으면 true, 아니면 false
		
		// 익지 안은 토마토가 있는지 검사
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (matrix[i][j][k] == 0) {	// 익지 안은 토마토가 있다면	
						flag = false;	// flag를 false로 바꿔주고
						break;			// 반복문 일단 탈출
					}
				}
			}
		}
		
		if (flag) {	// 토마토가 모두 익었다면
			System.out.println(0);	// 0 출력 후
			return;					// 함수 종료
		}
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					// 토마토가 있거나 방문을 하지 않았다면
					if (matrix[i][j][k] == 1 && !visited[i][j][k]) {
						// 여기서 큐에 삽입하는 이유
						// -> 해당 토마토들 주변에서 차례대로 1씩 증가시켜야 하기 때문
						queue.add(new int[] {i, j, k});	// 큐에 해당 좌표 삽입
						visited[i][j][k] = true;		// 방문 체크
					}
				}
			}
		}
		
		// 여기서부터 bfs 수행
		
		int lastX = -1, lastY = -1, lastZ = -1;	// 가장 최근에 접근한 x, y, z좌표
		
		while (!queue.isEmpty()) {
			int now[] = queue.poll();	// 큐에서 좌표 뽑기
			
			for (int i = 0; i < 6; i++) {
				int x = now[1] + dx[i];	// x 좌표 갱신	(가로)
				int y = now[2] + dy[i];	// y 좌표 갱신 (세로)
				int z = now[0] + dz[i];	// z 좌표 갱신 (높이)
				
				// 인덱스가 유효한 값이 아니라면 건너뛰기
				if (x < 0 || y < 0 || z < 0 || x >= N || y >= M || z >= H) {
					continue;
				}
				
				// 해당 좌표에 토마토가 없거나 이미 방문을 했다면 건너뛰기
				if (matrix[z][x][y] == -1 || visited[z][x][y]) {
					continue;
				}
				
				queue.add(new int[] {z, x, y});	// 큐에 해당 좌표 삽입
				visited[z][x][y] = true;		// 방문 체크
				// 일수를 체크하기 위해 전 좌표에서 1 증가
				matrix[z][x][y] = matrix[now[0]][now[1]][now[2]] + 1;
				
				lastX = x;	// 최근 x좌표 갱신
				lastY = y;	// 최근 y좌표 갱신
				lastZ = z;	// 최근 z좌표 갱신
			}
		}
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (matrix[i][j][k] == 0) {	// 익지 안은 토마토가 있다면
						System.out.println(-1);	// -1 출력 후
						return;					// 함수 종료
					}
				}
			}
		}
		
		// 가장 최근 방문한 좌표의 배열값에서 1 빼기 
		// -> 맨 처음이 1이었기 때문에 bfs 돌리면 2부터 삽입되기 때문
		System.out.println(matrix[lastZ][lastX][lastY] - 1);
	}
}