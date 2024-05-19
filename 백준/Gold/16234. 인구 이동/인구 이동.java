import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;						// 땅의 행, 열 개수
	static int L, R;					// L명 이상, R명 이하
	static int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
	static int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
	static int[][] A;					// 2차원 땅 배열
	static boolean[][] visited;			// 2차원 방문 여부 배열
	static int[][] unionCount;			// 2차원 연합국 영역 배열
	static int count;					// 연합국 개수
	static int days = 0;				// 인구 이동 발생 일 수
	static Queue<int[]> queue = new LinkedList<>();	// bfs 사용을 위한 큐
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());	
		A = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());	// 각 나라의 인구 수
			}
		}
		
		while (true) {
			HashMap<Integer, Union> union = new HashMap<>();
			visited = new boolean[N][N];
			unionCount = new int[N][N];
			count = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						count++;
						openBorderBfs(i, j);
					}
				}
			}
			
			boolean existed = existOpenedBorder();
			
			if (!existed) {
				System.out.println(days);
				return;
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (unionCount[i][j] != 0) {
						if (union.containsKey(unionCount[i][j])) {
							Union temp = union.get(unionCount[i][j]);
							temp.people += A[i][j];
							temp.space++;
							union.put(unionCount[i][j], temp);
						} else {
							union.put(unionCount[i][j], new Union(A[i][j], 1));
						}
					}
				}
			}
			
			Set<Integer> keySet = union.keySet();
			
			for (int unionCnt : keySet) {
				Union uni = union.get(unionCnt);
				int spacePeopleCount = uni.people / uni.space;	// 각 칸의 인구 수
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (unionCount[i][j] == unionCnt) {
							A[i][j] = spacePeopleCount;
						}
					}
				}
			}
			
			days++;
		}
	}
	
	public static void openBorderBfs(int a, int b) {
		queue.add(new int[] {a, b});
		visited[a][b] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = now[0] + dx[i];
				int y = now[1] + dy[i];
				
				if (x < 0 || y < 0 || x >= N || y >= N) {
					continue;
				}
				
				if (visited[x][y]) {
					continue;
				}
				
				int diff = Math.abs(A[now[0]][now[1]] - A[x][y]);
                
				if (diff >= L && diff <= R) {
					queue.add(new int[] {x, y});
					visited[x][y] = true;
					unionCount[now[0]][now[1]] = count;
					unionCount[x][y] = count;
				}
			}
		}
	}
	
	public static boolean existOpenedBorder() {
		boolean existed = false;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (unionCount[i][j] != 0) {
					existed = true;
					return existed;
				}
			}
		}
		
		return existed;
	}
	
	static class Union {
		int people;		// 연합의 인구 수
		int space;		// 연합을 이루고 있는 칸의 개수
		
		Union(int people, int space) {
			this.people = people;
			this.space = space;
		}
	}
}