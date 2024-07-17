import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;	// 사무실의 세로, 가로 크기
	static ArrayList<CCTV> cctvs = new ArrayList<>();	// CCTV(x좌표, y좌표, 번호) 리스트
	static int minSize = Integer.MAX_VALUE;		// 사각 지대의 최소 크기
	
	static class CCTV {
		int x, y, n;	// CCTV의 x좌표, y좌표, 번호
		
		CCTV(int x, int y, int n) {
			this.x = x;
			this.y = y;
			this.n = n;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] office = new int[N][M];		// 2차원 사무실 배열
		
		// 2차원 사무실 배열 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				// 0: 빈 칸
				// 6: 벽
				// 1 ~ 5: CCTV
				office[i][j] = Integer.parseInt(st.nextToken());
				// 입력받은 번호가 CCTV라면
				if (office[i][j] >= 1 && office[i][j] <= 5) {
					// CCTV 리스트에 x좌표, y좌표, 입력받은 번호 추가
					cctvs.add(new CCTV(i, j, office[i][j]));
				}
			}
		}
		
		// 첫번째 깊이(0)와 초기 사무실 배열을 인수로 넘겨서 dfs 수행
		// => 첫번째 CCTV를 찾아서 방향 돌리기
		dfs(0, office);
		System.out.println(minSize);	// 사각 지대의 최소 크기 출력
	}
	
	public static void dfs(int depth, int[][] office) {
		// depth가 CCTV 리스트의 크기와 같다면 모든 CCTV를 탐색한 것
		if (depth == cctvs.size()) {
			// 현재 사각 지대의 최소 크기(minSize)와 
			// 모든 CCTV를 탐색한 후 사무실 배열에 남아있는 사각지대(0)의 개수를 비교하여
			// 더 작은 값을 사각 지대의 최소 크기(minSize)에 갱신
			minSize = Math.min(minSize, getSize(office));
			return;		// 모든 CCTV를 탐색했으므로 dfs 종료
		}
		
		CCTV cctv = cctvs.get(depth);	// 현재 depth에 해당하는 CCTV 가져오기
		int x = cctv.x;		// CCTV의 x좌표
		int y = cctv.y;		// CCTV의 y좌표
		int n = cctv.n;		// CCTV의 번호
		
		if (n == 1) {	// CCTV가 1번일 때
			// 방향을 90도씩 4번 돌리기
			// →, ↓, ←, ↑
			for (int d = 0; d < 4; d++) {
				// 매개변수로 받은 배열을 깊은 복사한 임시 사무실 배열 얻어내기
				int[][] tempOffice = deepCopy(office);
				
				if (d == 0) {			// 현재 방향이 0일 때: →
					right(x, y, tempOffice);	// 오른쪽 감시
				} else if (d == 1) {	// 현재 방향이 1일 때: ↓
					down(x, y, tempOffice);		// 아래 감시
				} else if (d == 2) {	// 현재 방향이 2일 때: ←
					left(x, y, tempOffice);		// 왼쪽 감시
				} else {				// 현재 방향이 3일 때: ↑
					up(x, y, tempOffice);		// 위 감시
				}
				
				// 현재 깊이에서 1 더한 값과 임시 사무실 배열을 인수로 넘겨서 dfs 수행
				// => 다음 CCTV를 찾아서 방향 돌리기
				dfs(depth + 1, tempOffice);	
			}
		} else if (n == 2) {	// CCTV가 1번일 때
			// 방향을 90도씩 2번 돌리기
			// ↔, ↕
			for (int d = 0; d < 2; d++) {
				// 매개변수로 받은 배열을 깊은 복사한 임시 사무실 배열 얻어내기
				int[][] tempOffice = deepCopy(office);
				
				if (d == 0) {	// 현재 방향이 0일 때: ↔
					left(x, y, tempOffice);		// 왼쪽 감시
					right(x, y, tempOffice);	// 오른쪽 감시
				} else {		// 현재 방향이 1일 때: ↕
					up(x, y, tempOffice);		// 위 감시 
					down(x, y, tempOffice);		// 아래 감시
				}
				
				// 현재 깊이에서 1 더한 값과 임시 사무실 배열을 인수로 넘겨서 dfs 수행
				// => 다음 CCTV를 찾아서 방향 돌리기
				dfs(depth + 1, tempOffice);
			}
		} else if (n == 3) {	// CCTV가 3번일 때
			// 방향을 90도씩 4번 돌리기
			// ↑→, ↓→, ←↓, ←↑
			for (int d = 0; d < 4; d++) {
				// 매개변수로 받은 배열을 깊은 복사한 임시 사무실 배열 얻어내기
				int[][] tempOffice = deepCopy(office);
				
				if (d == 0) {			// 현재 방향이 0일 때: ↑→
					up(x, y, tempOffice);		// 위 감시
					right(x, y, tempOffice);	// 오른쪽 감시
				} else if (d == 1) {	// 현재 방향이 1일 때: ↓→
					down(x, y, tempOffice);		// 아래 감시
					right(x, y, tempOffice);	// 오른쪽 감시
				} else if (d == 2) {	// 현재 방향이 2일 때: ←↓
					left(x, y, tempOffice);		// 왼쪽 감시
					down(x, y, tempOffice);		// 아래 감시
				} else {				// 현재 방향이 3일 때: ←↑
					left(x, y, tempOffice);		// 왼쪽 감시
					up(x, y, tempOffice);		// 위 감시
				}
				
				// 현재 깊이에서 1 더한 값과 임시 사무실 배열을 인수로 넘겨서 dfs 수행
				// => 다음 CCTV를 찾아서 방향 돌리기
				dfs(depth + 1, tempOffice);
			}
		} else if (n == 4) {	// CCTV가 4번일 때
			// 방향을 90도씩 4번 돌리기
			// ←↑→, ↑↓→, ←↓→, ←↑↓
			for (int d = 0; d < 4; d++) {
				// 매개변수로 받은 배열을 깊은 복사한 임시 사무실 배열 얻어내기
				int[][] tempOffice = deepCopy(office);
				
				if (d == 0) {			// 현재 방향이 0일 때: ←↑→
					left(x, y, tempOffice);		// 왼쪽 감시
					up(x, y, tempOffice);		// 위 감시
					right(x, y, tempOffice);	// 오른쪽 감시
				} else if (d == 1) {	// 현재 방향이 1일 때: ↑↓→
					up(x, y, tempOffice);		// 위 감시
					right(x, y, tempOffice);	// 오른쪽 감시
					down(x, y, tempOffice);		// 아래 감시
				} else if (d == 2) {	// 현재 방향이 2일 때: ←↓→
					right(x, y, tempOffice);	// 오른쪽 감시
					down(x, y, tempOffice);		// 아래 감시
					left(x, y, tempOffice);		// 왼쪽 감시
				} else {				// 현재 방향이 3일 때: ←↑↓
					down(x, y, tempOffice);		// 아래 감시
					left(x, y, tempOffice);		// 왼쪽 감시
					up(x, y, tempOffice);		// 위 감시
				}
				
				// 현재 깊이에서 1 더한 값과 임시 사무실 배열을 인수로 넘겨서 dfs 수행
				// => 다음 CCTV를 찾아서 방향 돌리기
				dfs(depth + 1, tempOffice);
			}
		} else {	// CCTV가 5번일 때
			// ←↑↓→ 한 방향밖에 없으므로 for문 돌리지 않아도 됨
			// 매개변수로 받은 배열을 깊은 복사한 임시 사무실 배열 얻어내기
			int[][] tempOffice = deepCopy(office);
			
			right(x, y, tempOffice);	// 오른쪽 감시
			down(x, y, tempOffice);		// 아래 감시
			left(x, y, tempOffice);		// 왼쪽 감시
			up(x, y, tempOffice);		// 위 감시
			
			// 현재 깊이에서 1 더한 값과 임시 사무실 배열을 인수로 넘겨서 dfs 수행
			// => 다음 CCTV를 찾아서 방향 돌리기
			dfs(depth + 1, tempOffice);
		}
	}
	
	// 매개변수로 받은 배열을 깊은 복사하여 리턴
	public static int[][] deepCopy(int[][] original) {
		int[][] copy = new int[N][M];
		
		// 2차원 배열 깊은 복사
		for (int i = 0; i < N; i++) {
			System.arraycopy(original[i], 0, copy[i], 0, M);
		}
		
		return copy;
	}
	
	// 오른쪽 감시
	public static void right(int x, int y, int[][] tempOffice) {
		// y좌표 + 1부터 M까지 인덱스를 늘리면서 탐색
		for (int i = y + 1; i < M; i++) {
			if (tempOffice[x][i] == 6) {	// 해당 칸이 벽이라면
				break;						// 감시 종료
			} else if (tempOffice[x][i] == 0) {	// 해당 칸이 빈 칸이라면
				tempOffice[x][i] = -1;			// 해당 칸 감시 완료
			}
		}
	}
	
	// 왼쪽 감시
	public static void left(int x, int y, int[][] tempOffice) {
		// y좌표 - 1부터 0까지 인덱스를 줄이면서 탐색
		for (int i = y - 1; i >= 0; i--) {	
			if (tempOffice[x][i] == 6) {	// 해당 칸이 벽이라면
				break;						// 감시 종료
			} else if (tempOffice[x][i] == 0) {	// 해당 칸이 빈 칸이라면
				tempOffice[x][i] = -1;			// 해당 칸 감시 완료
			}
		}
	}
	
	// 위 감시
	public static void up(int x, int y, int[][] tempOffice) {
		// x좌표 - 1부터 0까지 인덱스를 줄이면서 탐색
		for (int i = x - 1; i >= 0; i--) {
			if (tempOffice[i][y] == 6) {	// 해당 칸이 벽이라면
				break;						// 감시 종료
			} else if (tempOffice[i][y] == 0) {	// 해당 칸이 빈 칸이라면
				tempOffice[i][y] = -1;			// 해당 칸 감시 완료
			}
		}
	}
	
	// 아래 감시
	public static void down(int x, int y, int[][] tempOffice) {
		// x좌표 + 1부터 N까지 인덱스를 늘리면서 탐색
		for (int i = x + 1; i < N; i++) {
			if (tempOffice[i][y] == 6) {	// 해당 칸이 벽이라면
				break;						// 감시 종료
			} else if (tempOffice[i][y] == 0) {	// 해당 칸이 빈 칸이라면
				tempOffice[i][y] = -1;			// 해당 칸 감시 완료
			}
		}
	}
	
	// 모든 CCTV를 탐색한 후 사무실 배열에 남아있는 사각지대(0)의 개수 리턴
	public static int getSize(int[][] tempOffice) {
		int size = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tempOffice[i][j] == 0) {
					size++;
				}
			}
		}
		
		return size;
	}
}