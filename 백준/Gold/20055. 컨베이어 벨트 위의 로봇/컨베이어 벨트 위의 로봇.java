import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 로봇이 내리는 위치
		int K = Integer.parseInt(st.nextToken());	// 내구도가 0인 칸의 개수가 K개 이상이면 과정 종료
		List<Integer> belt = new ArrayList<>();		// 벨트 리스트(크기: 2 * N)
		int[] robot = new int[N];	// 로봇 배열 (크기: N)
		int time = 0;	// 과정이 진행되는 시간
		
		// 벨트를 0번 블럭부터 2 * N - 1번 블럭까지 내구도 세팅
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			belt.add(Integer.parseInt(st.nextToken()));
		}
		
		// 벨트에 로봇을 올리고 옮기고 내리는 과정 반복
		while (true) {
			time++;		// 과정이 진행되는 시간 1 증가
			
			// 벨트와 로봇을 함께 이동
			// 1) 벨트 1칸 이동
			// 맨 끝 인덱스에 있는 요소를 제거해서 맨 앞 인덱스에 삽입
			belt.add(0, belt.remove(2 * N - 1));	
			
			// 2) 로봇 1칸 이동
			for (int i = N - 2; i >= 0; i--) {
				if (robot[i] != 0) {
					robot[i + 1] = 1;	// i + 1번 위치로 로봇 이동
					robot[i]--;		// i번 위치에 있는 로봇 개수 1 감소
				}
			}
			
			// 로봇이 N - 1번 위치에 오면 로봇 실어서 나르기
			if (robot[N - 1] == 1) {
				robot[N - 1] = 0;
			}
			
			// 벨트에 먼저 올라온 로봇부터 차례대로 1칸씩 이동
			for (int i = N - 2; i >= 0; i--) {
				// 해당 블록에 로봇이 있고, 다음(i + 1)번 블록에 내구도가 남아있고, 다음 블록에 로봇이 없다면
				if (robot[i] != 0 && belt.get(i + 1) != 0 && robot[i + 1] == 0) {
					robot[i + 1] = 1;	// i + 1번 위치로 로봇 이동
					robot[i]--;		// i번 위치에 있는 로봇 개수 1 감소
					belt.set(i + 1, belt.get(i + 1) - 1);	// i + 1번 위치의 블럭 내구도 1 감소
				}
			}
			
			// 0번 위치에 있는 블럭의 내구도가 남아 있다면 로봇 올리기
			if (belt.get(0) != 0) {
				robot[0]++;	// 0번 위치에 있는 로봇 개수 1 증가
				belt.set(0, belt.get(0) - 1);	// 0번 위치의 블럭 내구도 1 감소
			}
			
			// 로봇이 N - 1번 위치에 오면 로봇 실어서 나르기
			if (robot[N - 1] == 1) {
				robot[N - 1] = 0;
			}
			
			int count = 0;	// 블럭 내구도가 0인 칸의 개수
			
			// 블럭 내구도가 0인 칸의 개수 카운트
			for (int durability : belt) {
				if (durability == 0) {
					count++;
				}
			}
			
			// 내구도가 0인 블럭의 개수가 K개 이상이라면 과정 종료
			if (count >= K) {
				System.out.println(time);	// 과정이 종료될 때까지 몇 초가 걸리는 지 출력
				break;
			}
		}
	}
}