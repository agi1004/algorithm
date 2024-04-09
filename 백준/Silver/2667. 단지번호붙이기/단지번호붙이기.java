import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static int[] dx = {1, 0, -1, 0};	// x 좌표 조작 (오른쪽으로 이동, 왼쪽으로 이동)
	static int[] dy = {0, 1, 0, -1};	// y 좌표 조작 (아래로 이동, 위로 이동)
	static int[][] matrix;
	static boolean[][] visited;
	static int allCount;
	static int homeCount;
	static ArrayList<Integer> homeCountList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 지도의 크기 (N x N)
		matrix = new int[N + 1][N + 1];			// 2차원 (N x N) 지도
		visited = new boolean[N + 1][N + 1];	// 2차원 방문 배열
		allCount = 0;	// 총 단지 수
		homeCount = 1;	// 단지 내 집의 수
		homeCountList = new ArrayList<>();	// 단지 내 집의 수 리스트
		
		// 인접행렬 초기화
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N - 1; j++) {
				matrix[i][j + 1] = Integer.parseInt(str.substring(j, j + 1));
			}
			matrix[i][N] = Integer.parseInt(str.substring(N - 1));
		}
		
		// 띄엄띄엄 있는 여러 단지를 모두 방문해야 하므로 2중 반복문 돌리기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 해당 정점이 0이 아니고(집이 있음), 해당 정점을 방문하지 않았다면
				if (matrix[i][j] != 0 && !visited[i][j]) {
					homeCount = 1;	// 새로운 단지이므로 단지 내 집의 수 초기화	
					bfs(i, j);		// 해당 집부터 bfs 수행
				}
			}
		}
		
		Collections.sort(homeCountList);	// 단지 내 집의 수 리스트 오름차순 정렬
		
		System.out.println(allCount);		// 총 단지 수 출력
		
		// 오름차순 정렬된 단지 내 집의 수 출력
		for (int i = 0; i < homeCountList.size(); i++) {
			System.out.println(homeCountList.get(i));
		}
	}
	
	public static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {a, b});	// 해당 집을 큐에 삽입
		visited[a][b] = true;			// 해당 집 방문 체크
		int lastX = a, lastY = b;		// 가장 마지막으로 방문한 집 좌표
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();	// 큐에서 현재 집 꺼내기
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];	// 해당 집 기준 상하좌우로 x 좌표 조작
				int y = now[1] + dy[i];	// 해당 집 기준 상하좌우로 y 좌표 조작
				
				// x와 y가 유효한 인덱스가 아니라면 건너뛰기
				if (x < 1 || y < 1 || x > N || y > N) {
					continue;
				}
				
				// 방문한 집이라면 건너뛰기
				if (visited[x][y]) {
					continue;
				}
				
				// 해당 정점이 0이 아니라면(집이 있다면)
				if (matrix[x][y] != 0) { 
					queue.add(new int[] {x, y});	// 해당 집을 큐에 삽입
					visited[x][y] = true;			// 해당 집 방문 체크
					// 단지 내 집의 수를 1 증가 후 삽입
					// 후위 연산자인 이유는 bfs를 처음 수행할 때 이미 집 1개를 체크했으므로 
					// 이 다음부터 방문하는 집은 1보다 커야 함
					matrix[x][y] = ++homeCount;		
					lastX = x;		// 가장 마지막으로 방문한 x 좌표 갱신
					lastY = y;		// 가장 마지막으로 방문한 y 좌표 갱신
				}
			}
		}
		
		// 방문할 수 있는 인접 영역이 더 이상 없으므로 총 단지 수 1 증가
		allCount++;	
		// 단지 내 집의 수 리스트에 가장 마지막으로 방문한 집의 좌표값을 추가
		homeCountList.add(matrix[lastX][lastY]);	
	}
}