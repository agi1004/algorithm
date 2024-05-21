import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] gear = new ArrayList[5];	// 1 ~ 4번 톱니바퀴 ArrayList
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1 ~ 4번 톱니바퀴에 각각 ArrayList 만들어주기
		for (int i = 1; i <= 4; i++) {
			gear[i] = new ArrayList<Integer>();
		}
		
		// 1 ~ 4번 톱니바퀴의 상태 초기화
		for (int i = 1; i <= 4; i++) {
			String line = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i].add(line.charAt(j) - '0');
			}
		}
		
		int K = Integer.parseInt(br.readLine());	// 회전 횟수
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());	// 회전시킬 톱니바퀴의 번호
			int d = Integer.parseInt(st.nextToken()); 	// 회전시킬 톱니바퀴의 방향 (1: 시계, -1: 반시계)
			int[] spinDirection = getSpinDirection(n, d);	// 톱니바퀴 회전 방향 배열 받아오기
			
			// 톱니바퀴 1부터 4까지 탐색
			for (int j = 1; j <= 4; j++) {
				if (spinDirection[j] == -1) {	// j번째 톱니바퀴가 반시계 방향으로 회전해야 한다면
					gear[j].add(gear[j].remove(0));	// 맨 앞의 요소를 제거하고 맨 뒤에 추가
				} else if (spinDirection[j] == 1) {	// j번째 톱니바퀴가 시계 방향으로 회전해야 한다면
					gear[j].add(0, gear[j].remove(7));	// 맨 뒤의 요소를 제거하고 맨 앞에 추가
				}
			}
		}
		
		int score = 0;	// K번 회전시킨 이후 네 톱니바퀴 점수의 합
		
		for (int i = 1; i <= 4; i++) {
			if (gear[i].get(0) == 1) {	// i번째 톱니자퀴의 12시 방향이 S극이면
				// 2의 i-1승만큼 더하기
				// 2^1-1 = 2^0 = 1
				// 2^2-1 = 2^1 = 2
				// 2^3-1 = 2^2 = 4
				// 2^4-1 = 2^3 = 8
				score += Math.pow(2, i - 1);	
			}
		}
		
		System.out.println(score);	// K번 회전시킨 이후 네 톱니바퀴 점수의 합 출력
	}
	
	public static int[] getSpinDirection(int n, int d) {
		int[] spinDirection = new int[5];	// 1 ~ 4번 톱니바퀴 회전 방향 배열 (1: 시계, -1: 반시계)
		
		switch (n) {
			case 1:		// 톱니바퀴 번호가 1일 때
				spinDirection[1] = d;	// 톱니바퀴1의 방향을 d로 설정
				
				// 톱니바퀴1과 톱니바퀴2가 맞닿은 극이 다르면
				if (gear[1].get(2) != gear[2].get(6)) {
					spinDirection[2] = -d;	// 2를 1과 반대 방향으로 회전
				} else {	// 톱니바퀴1과 톱니바퀴2가 맞닿은 극이 같다면
					break;	// 2 옆에있는 3도 회전 시킬 필요 없으므로 break
				}
				
				// 톱니바퀴2와 톱니바퀴3이 맞닿은 극이 다르면
				if (gear[2].get(2) != gear[3].get(6)) {
					spinDirection[3] = d;	// 3을 2와 반대 방향으로 회전
				} else {	// 톱니바퀴2와 톱니바퀴3이 맞닿은 극이 같다면
					break;	// 3 옆에있는 4도 회전 시킬 필요 없으므로 break
				}
				
				// 톱니바퀴3과 톱니바퀴4가 맞닿은 극이 다르면
				if (gear[3].get(2) != gear[4].get(6)) {
					spinDirection[4] = -d;	// 4를 3과 반대 방향으로 회전
				} 
				break;	// 톱니바퀴의 회전 방향을 모두 셋팅했으므로 break
				
			case 2:		// 톱니바퀴 번호가 2일 때
				spinDirection[2] = d;	// 톱니바퀴2의 방향을 d로 설정
				
				// 톱니바퀴1과 톱니바퀴2가 맞닿은 극이 다르면
				if (gear[1].get(2) != gear[2].get(6)) {
					spinDirection[1] = -d;	// 1을 2와 반대 방향으로 회전
				}	// 1은 맨 끝에 있으므로 break하면 안 됨
				
				// 톱니바퀴2와 톱니바퀴3이 맞닿은 극이 다르면
				if (gear[2].get(2) != gear[3].get(6)) {
					spinDirection[3] = -d;	// 3을 2와 반대 방향으로 회전
				} else {	// 톱니바퀴2와 톱니바퀴3이 맞닿은 극이 같다면
					break;	// 3 옆에있는 4도 회전 시킬 필요 없으므로 break
				}
				
				// 톱니바퀴3과 톱니바퀴4가 맞닿은 극이 다르면
				if (gear[3].get(2) != gear[4].get(6)) {
					spinDirection[4] = d;	// 4를 3과 반대 방향으로 회전
				}		
				break;	// 톱니바퀴의 회전 방향을 모두 셋팅했으므로 break
				
			case 3:		// 톱니바퀴 번호가 3일 때
				spinDirection[3] = d;	// 톱니바퀴3의 방향을 d로 설정
				
				// 톱니바퀴3과 톱니바퀴4가 맞닿은 극이 다르면
				if (gear[3].get(2) != gear[4].get(6)) {
					spinDirection[4] = -d;	// 4를 3과 반대 방향으로 회전
				}	// 4는 맨 끝에 있으므로 break하면 안됨
				
				// 톱니바퀴2와 톱니바퀴3이 맞닿은 극이 다르면
				if (gear[2].get(2) != gear[3].get(6)) {
					spinDirection[2] = -d;	// 2를 3과 반대 방향으로 회전
				} else {	// 톱니바퀴2와 톱니바퀴3이 맞닿은 극이 같다면
					break;	// 2 옆에있는 1도 회전 시킬 필요 없으므로 break
				}
				
				// 톱니바퀴1과 톱니바퀴2가 맞닿은 극이 다르면
				if (gear[1].get(2) != gear[2].get(6)) {
					spinDirection[1] = d;	// 1을 2와 반대 방향으로 회전
				} 
				
				break;	// 톱니바퀴의 회전 방향을 모두 셋팅했으므로 break
				
			 case 4:	// 톱니바퀴 번호가 4일 때
				spinDirection[4] = d;	// 톱니바퀴4의 방향을 d로 설정
				
				// 톱니바퀴3과 톱니바퀴4가 맞닿은 극이 다르면
				if (gear[3].get(2) != gear[4].get(6)) {
					spinDirection[3] = -d;	// 3을 4와 반대 방향으로 회전
				} else {	// 톱니바퀴3과 톱니바퀴4가 맞닿은 극이 같다면
					break;	// 3 옆에있는 2도 회전 시킬 필요 없으므로 break
				}	
				
				// 톱니바퀴2와 톱니바퀴3이 맞닿은 극이 다르면
				if (gear[2].get(2) != gear[3].get(6)) {
					spinDirection[2] = d;	// 2를 3과 반대 방향으로 회전
				} else {	// 톱니바퀴2와 톱니바퀴3이 맞닿은 극이 다르면
					break;	// 2 옆에있는 1도 회전 시킬 필요 없으므로 break
				}
				
				// 톱니바퀴1과 톱니바퀴2가 맞닿은 극이 다르면
				if (gear[1].get(2) != gear[2].get(6)) {
					spinDirection[1] = -d;	// 1을 2와 반대 방향으로 회전
				} 
				
				break;	// 톱니바퀴의 회전 방향을 모두 셋팅했으므로 break
		}
		
		return spinDirection;	// 1 ~ 4번 톱니바퀴 회전 방향 배열 리턴
	}
}