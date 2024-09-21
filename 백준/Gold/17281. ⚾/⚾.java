import java.io.*;
import java.util.*;

public class Main {
	static int N;	// 이닝 수
	static int[][] hits;	// i번 이닝 때 j번 선수의 결과
	static boolean[] selected = new boolean[10];	// i번 타자 선정 여부
	static int[] orders = new int[10];	// i번 타자의 선수 번호
	static int maxScore;	// 팀이 얻을 수 있는 최대 점수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		hits = new int[N + 1][10];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				// i번 이닝 때 j번 선수의 결과
				hits[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		selected[4] = true;		// 4번 타자 선정
		orders[4] = 1;			// 4번 타자는 1번 선수
		
		dfs(2);	// 2번 선수부터 타순 정하기
		
		System.out.println(maxScore);
	}
	
	// 백트래킹: 타순을 정할 수 있는 모든 경우의 수 (순열) 구하기
	public static void dfs(int depth) {
		if (depth == 10) {	// depth가 10이라면 모든 선수의 타순을 정한 것
			int score = play();		// 야구 플레이 후 얻은 총 점수
			maxScore = Math.max(maxScore, score);	// 팀이 얻을 수 있는 최대 점수 갱신
			return;		// 다시 돌아가서 선수들의 타순을 새로 정하러 가기
		}
		
		// 1번부터 9번 타자까지 순서 정하기
		for (int i = 1; i <= 9; i++) {
			if (!selected[i]) {	// i번 타자가 선정이 안 되었다면
				selected[i] = true;		// i번 타자 선정
				orders[i] = depth;		// i번 타자는 depth번 선수
				dfs(depth + 1);			// depth + 1번 선수의 타순 정하기  
				selected[i] = false;	// i번 타자 선정 해제
			}
		}
	}
	
	// 야구 플레이
	public static int play() {
		int score = 0;	// 야구 플레이 후 얻은 총 점수
		int startHitter = 1;	// 시작 타자
		
		// N번 이닝까지 진행
		for (int i = 1; i <= N; i++) {
			boolean[] base = new boolean[4];	// 1, 2, 3루 타자 존재 여부
			int out = 0;	// 아웃 횟수
			
			outer: while (true) {
				// 시작 타자부터 순서대로 진행
				for (int j = startHitter; j <= 9; j++) {
					int hit = hits[i][orders[j]];	// i번 이닝 때 j번 타자의 결과
					
					switch (hit) {
						case 0:		// 아웃
							out++;	// 아웃 횟수 증가 후 종료
							break;
							
						case 1:		// 안타
							// 1 ~ 3루에 있던 모든 선수들이 한 칸씩 이동
							for (int k = 3; k >= 1; k--) {
								if (base[k]) {	// k루에 타자가 존재한다면
									if (k == 3) {	// 3루에 있던 선수라면
										score++;			// 점수 1점 획득
										base[k] = false;	// 이동했으므로 3루에는 아무도 없음
									} else {		// 1, 2루에 있던 선수라면
										base[k] = false;	// 이동했으므로 1, 2루에는 아무도 없음
										base[k + 1] = true;	// 다음 칸(2, 3루)으로 이동
									}
								}
							}
							
							base[1] = true;	// 현재 타자는 1루로 이동 후 종료
							break;
							
						case 2:		// 2루타
							// 1 ~ 3루에 있던 모든 선수들이 한 칸씩 이동
							for (int k = 3; k >= 1; k--) {
								if (base[k]) {	// k루에 타자가 존재한다면
									if (k == 3 || k == 2) {	// 2, 3루에 있던 선수라면
										score++;			// 점수 1점 획득
										base[k] = false;	// 이동했으므로 2, 3루에는 아무도 없음
									} else {	// 1루에 있던 선수라면
										base[k] = false;	// 이동했으므로 1루에는 아무도 없음
										base[k + 2] = true;	// 다다음 칸(3루)으로 이동
									}
								}
							}
							
							base[2] = true;	// 현재 타자는 2루로 이동 후 종료
							break;
							
						case 3:		// 3루타
							// 1 ~ 3루에 있던 모든 선수들이 한 칸씩 이동
							for (int k = 3; k >= 1; k--) {
								if (base[k]) {	// k루에 타자가 존재한다면
									score++;			// 점수 1점 획득
									base[k] = false;	// 이동했으므로 1, 2, 3루에는 아무도 없음
								}
							}
							
							base[3] = true;	// 현재 타자는 3루로 이동 후 종료
							break;
							
						case 4:		// 홈런
							// 1 ~ 3루에 있던 모든 선수들이 한 칸씩 이동
							for (int k = 3; k >= 1; k--) {
								if (base[k]) {	// k루에 타자가 존재한다면
									score++;			// 점수 1점 획득
									base[k] = false;	// 이동했으므로 1, 2, 3루에는 아무도 없음
								}
							}
							
							score++;	// 현재 타자도 점수 1점 획득 후 종료
							break;
					}
					
					if (out == 3) {		// 3아웃이 발생하면 이닝 종료
						startHitter = j + 1;	// 시작 타자를 현재 타자의 다음 타자로 변경
						
						if (startHitter == 10) {	// 시작 타자가 10이 되었다면 말이 안되므로
							startHitter = 1;	// 시작 타자를 1로 설정
						}
						
						break outer;	// while문 탈출 후 다음 이닝 시작
					}
				}
				
				// 9번 타자까지 공을 쳤는데 3아웃이 발생하지 않은 상태면
				// 이닝은 끝나지 않고, 1번 타자가 다시 타석에 선다.
				startHitter = 1;
			}
		}
		
		return score;	// 야구 플레이 후 얻은 총 점수 리턴
	}
}