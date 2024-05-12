import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N;			// 보드의 크기
	static int[][] board;	// 2차원 N * N 크기의 보드
	
	static class Snack {
		int r;	// 뱀의 행 위치
		int c;	// 뱀의 열 위치
		int d;	// 뱀의 방향 (0: 북, 1: 동, 2: 남, 3: 서)
		
		Snack(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	
		int K = Integer.parseInt(br.readLine());	// 사과의 개수
		board = new int[N + 1][N + 1];
		HashMap<Integer, Character> change = new HashMap<>();	// 뱀의 방향 변환 정보	
		int second = 0;		// 게임 경과 시간
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());	// 사과의 행 위치
			int c = Integer.parseInt(st.nextToken());	// 사과의 열 위치
			board[r][c] = 2;
		}
		
		int L = Integer.parseInt(br.readLine());	// 뱀의 방향 변환 횟수
		
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());	// X초가 끝난 뒤
			char C = st.nextToken().charAt(0);	// 왼쪽(L) 또는 오른쪽(D)으로 90도 방향 회전
			change.put(X, C);	// 뱀의 방향 변환 정보 삽입
		}
		
		Deque<Snack> deque = new ArrayDeque<>();
		deque.add(new Snack(1, 1, 1));	// 뱀의 맨 처음 위치(1행 1열), 뱀의 맨 처음 방향(1: 동쪽)
		board[1][1] = 1;	// 뱀의 처음 위치
		
		while (!deque.isEmpty()) {			
			Snack now = deque.peek();	// 현재 뱀의 머리
			second++;					// 1초 경과
			
			// 앞으로 갈 수 있으면 뱀의 머리 객체 리턴
			Snack next = goStraight(now.r, now.c, now.d);	
			
			if (next == null) {	// 앞으로 이동한 곳이 벽이거나 자기자신의 몸이라면 이동 불가
				break;			// 게임 종료
			} else {	// 앞으로 이동한 곳이 벽이 아니고, 자기자신의 몸도 아니라면 이동 가능
				// 현재 경과 시간에 방향을 회전시켜야 하는지 확인하기
				if (change.containsKey(second)) {	
					char C = change.get(second);	// 변환해야 할 방향 가져오기
					if (C == 'L') {			// 왼쪽으로 회전해야 할 때
						next.d = (next.d == 0) ? 3 : next.d - 1;
					} else if (C == 'D') {	// 오른쪽으로 회전해야 할 때
						next.d = (next.d == 3) ? 0 : next.d + 1;
					}
				}
				
				deque.addFirst(next);	// 몸 길이를 늘려 머리를 다음 칸에 위치시키기
				
				if (board[next.r][next.c] == 2) {	// 이동한 칸에 사과가 있다면
					// 그 칸에 있던 사과가 없어지고 해당 칸이 자기자신의 몸으로 바뀜
					board[next.r][next.c] = 1;
				} else {	// 이동한 칸에 사과가 없다면
					board[next.r][next.c] = 1;	// 해당 칸이 자기자신의 몸으로 바뀜
					Snack tail = deque.pollLast();	// 몸 길이를 줄이기
					board[tail.r][tail.c] = 0;		// 꼬리가 위치한 칸을 비워주기
				}
			}
		}
		
		System.out.println(second);		// 게임이 몇 초에 끝나는지 출력
	}
	
	public static Snack goStraight(int r, int c, int d) {
		Snack snack = null;
		
		switch (d) {
			case 0:
				// 앞으로 이동한 곳이 벽이 아니고, 자기자신의 몸도 아니라면 
				if (r - 1 >= 1 && board[r - 1][c] != 1) {
					snack = new Snack(r - 1, c, d);		// 앞으로 이동한 뱀 객체 생성 
				}
				break;
			case 1:
				if (c + 1 <= N && board[r][c + 1] != 1) {
					snack = new Snack(r, c + 1, d);
				}
				break;
			case 2:
				if (r + 1 <= N && board[r + 1][c] != 1) {
					snack = new Snack(r + 1, c, d);
				}
				break;
			case 3:
				if (c - 1 >= 1 && board[r][c - 1] != 1) {
					snack = new Snack(r, c - 1, d);
				}
				break;
		}
		
		return snack;	// 앞으로 이동한 뱀 객체 리턴
	}
}