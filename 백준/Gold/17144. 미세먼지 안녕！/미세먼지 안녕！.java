import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;					// 집의 세로 크기, 가로 크기	
	static int[][] room;				// 2차원 방 배열 (초기 미세먼지가 그대로 있는 배열)
	static int[][] copyRoom;			// 2차원 복사 방 배열 (미세먼지를 확산시킬 배열)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());	
		C = Integer.parseInt(st.nextToken());	
		int T = Integer.parseInt(st.nextToken());		// T초
		room = new int[R][C];
		copyRoom = new int[R][C];
		ArrayList<int[]> cleaner = new ArrayList<>();	// 공기청정기 좌표 리스트
		
		// 2차원 방 배열 초기화
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				// -1: 공기청정기가 설치된 곳, 나머지 값: 미세먼지의 양
				room[r][c] = Integer.parseInt(st.nextToken());
				copyRoom[r][c] = room[r][c];	// 복사 방 배열에도 같은 값 할당
				if (room[r][c] == -1) {			// 공기청정기가 설치된 칸이라면
					cleaner.add(new int[] {r, c});	// 공기청정기 좌표 리스트에 삽입
				}
			}
		}
		
		// T초 동안 반복
		for (int t = 0; t < T; t++) {
			// 확산은 미세먼지가 있는 모든 칸에서 동시에 일어남
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					// 미세먼지를 모든 칸에서 동시에 퍼뜨려야하므로 원본 배열 사용
					if (room[r][c] > 0) {	// -1, 0이 아니면 미세머지가 있는 칸
						spread(r, c);		// 해당 칸부터 미세먼지 확산
					}
				}
			}
			
			for (int i = 0; i < cleaner.size(); i++) {
				int x = cleaner.get(i)[0];	// 공기청정기의 x좌표
				int y = cleaner.get(i)[1];	// 공기청정기의 y좌표
				windCleaner(x, y, i);	// 해당 칸과 인덱스(위 or 아래)를 넘겨주고 공기청정기 작동시키기
			}
			
			// 미세먼지의 위치를 원본 배열에서 찾으므로 
			// 미세먼지를 확산시킨 이후에는 원본 배열로 깊은 복사
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					room[r][c] = copyRoom[r][c];
				}
			}
		}
		
		int amount = 0;		// T초가 지난 후 방에 남아있는 미세먼지의 양
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (copyRoom[r][c] > 0) {		// -1, 0이 아니면 미세머지가 있는 칸
					amount += copyRoom[r][c];	// 남아있는 미세먼지의 양 더하기
				}
			}
		}
		
		System.out.println(amount);		// T초가 지난 후 방에 남아있는 미세먼지의 양 출력
	}
	
	// 미세먼지를 확산시키는 메소드
	public static void spread(int r, int c) {
		ArrayList<int[]> arounds = new ArrayList<>();	// 인접 칸의 좌표 리스트
		int[] dx = {-1, 0, 1, 0};	// 상, 하 이동
		int[] dy = {0, -1, 0, 1};	// 좌, 우 이동
		
		// 인접한 칸(상, 하, 좌, 우)을 모두 탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dx[i];		// x좌표를 인접한 칸으로 이동
			int nc = c + dy[i];		// y좌표를 인접한 칸으로 이동
			
			// 인덱스가 유효 범위가 아니라면 건너뛰기
			if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
				continue;
			}
			
			// 공기청정기가 있는 칸이 아니라면
			if (copyRoom[nr][nc] != -1) {
				arounds.add(new int[] {nr, nc});	// 인접 칸을 인접 칸의 좌표 리스트에 삽입
				// 원본 배열의 위치에 있는 값을 기준으로 확산되는 양을 계산해서 
				// 복사된 배열 위치의 인접 칸에 더하기
				copyRoom[nr][nc] += room[r][c] / 5;
			}
		}
		
		// 인접 칸의 개수가 0개가 아니라면 (인접 칸이 존재한다면)
		if (arounds.size() != 0) {
			// 남은 미세머지 양을 계산해서 복사된 배열 위치의 칸에 대입
			copyRoom[r][c] = copyRoom[r][c] - (room[r][c] / 5) * arounds.size();	
		}
	}
	
	// 공기청정기가 작동하는 메소드
	public static void windCleaner(int a, int b, int index) {
		Queue<int[]> queue = new LinkedList<>();	// 다음 칸으로 이동하기 위한 큐
		Queue<Integer> temp = new LinkedList<>();	// 미세먼지를 저장해 놓을 임시 큐
		int[] dx = null, dy = null;		// x좌표와 y좌표 이동을 위한 배열
		int d = 0;						// 현재 방향
		queue.add(new int[] {a, b});	// 공기청정기 칸을 큐에 삽입		
		
		if (index == 0) {			// 위쪽 공기청정기라면 반시계방향으로 순환
			// 우, 상, 좌, 하 순서로 이동
			dx = new int[] {0, -1, 0, 1};
			dy = new int[] {1, 0, -1, 0};
		} else if (index == 1) {	// 아래쪽 공기청정기라면 시계방향으로 순환
			// 우, 하, 좌, 상 순서로 이동
			dx = new int[] {0, 1, 0, -1};
			dy = new int[] {1, 0, -1, 0};
		}
		
		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			int[] now = queue.poll();	// 현재 칸을 큐에서 뽑기
			int x = now[0];				// 현재 칸의 x좌표
			int y = now[1];				// 현재 칸의 y좌표
				
			int nx = x + dx[d];			// x좌표를 인접한 칸으로 이동
			int ny = y + dy[d];			// y좌표를 인접한 칸으로 이동
			
			// 벽에 부딪쳤다면 (인덱스가 유효 범위가 아니라면)
			if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
				d++;					// 방향 전환
				nx = x + dx[d];			// x좌표를 인접한 칸으로 이동
				ny = y + dy[d];			// y좌표를 인접한 칸으로 이동
			}
			
			if (copyRoom[nx][ny] == -1) {	// 공기청정기가 있는 칸이라면
				return;						// 미세먼지 이동 끝내기
			}
			
			queue.add(new int[] {nx, ny});	// 인접 칸을 다음 칸으로 이동할 큐에 삽입
			temp.add(copyRoom[nx][ny]);		// 인접 칸을 미세먼지에 저장해 놓을 큐에 삽입
			
			if (copyRoom[x][y] == -1) {		// 현재 칸이 -1이라면
				copyRoom[nx][ny] = 0;		// 인접 칸에 0 대입
			} else {						// 현재 칸이 -1이 아니라면
				copyRoom[nx][ny] = temp.poll();	// 인접 칸에 임시 큐에서 뽑은 값을 삽입
			}		
		}
	}
}