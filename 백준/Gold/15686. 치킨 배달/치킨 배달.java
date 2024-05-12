import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {			
	static int M;											// 폐업시키지 않을 치킨집의 개수
	static ArrayList<Point> houses = new ArrayList<>();		// 집 좌표 리스트
	static ArrayList<Point> chickens = new ArrayList<>();	// 치킨집 좌표 리스트
	static int[] open;										// 오픈한 치킨집 인덱스 배열
	static boolean[] selected;								// 치킨집의 오픈 여부 배열
	static int minCityChickenLength = Integer.MAX_VALUE;	// 도시의 치킨 거리의 최솟값	
	
	static class Point {
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 2차원 도시 배열의 행, 열 개수	
		M = Integer.parseInt(st.nextToken());	
		open = new int[M];
		
		// 2차원 도시 배열 초기화
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int spot = Integer.parseInt(st.nextToken());
				if (spot == 1) {
					houses.add(new Point(i, j));	// 집 좌표 리스트에 값 삽입
				} else if (spot == 2) {
					chickens.add(new Point(i, j));	// 치킨집 좌표 리스트에 값 삽입
				}
			}
		}
		
		// 치킨집 오픈 여부 배열의 사이즈를 치킨집의 개수만큼 정해주고 초기화
		selected = new boolean[chickens.size()];	
		
		// M개의 치킨집을 선택한 경우의 수를 모두 탐색해야하므로 백트래킹 수행
		dfs(0, 0);
		
		System.out.println(minCityChickenLength);	// 도시의 치킨 거리의 최솟값 출력
	}
	
	public static void dfs(int depth, int index) {
		// M개의 치킨집을 모두 선택했으면 
		// M개의 치킨집을 선택한 도시의 치킨 거리 계산 후 함수 종료
		if (depth == M) {
			int cityChickenLength = 0;	// M개의 치킨집을 선택한 도시의 치킨 거리
			
			for (Point house : houses) {	// 집들 모두 탐색
				// 해당 집과 치킨집들 사이의 거리중 가장 작은 값
				int chickenLength = Integer.MAX_VALUE;
				
				for (int i = 0; i < M; i++) {	// 오픈한 치킨집 모두 탐색
					// open[i]: i번째로 오픈한 치킨집의 인덱스
					// chicken: open[i]번째 치킨집
					Point chicken = chickens.get(open[i]);
					// 해당 집과 해당 치킨집 사이의 거리
					int length = Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
					// 해당 집과 치킨집들 사이의 거리 최솟값 갱신
					chickenLength = Math.min(chickenLength, length);
				}
				
				// 해당 집의 최소 치킨 거리를 찾았으면 도시의 치킨 거리에 더하기
				cityChickenLength += chickenLength;
			}
			
			// 도시의 치킨 거리의 최솟값 갱신
			minCityChickenLength = Math.min(minCityChickenLength, cityChickenLength);
			return;		// 함수 종료 후 다른 치킨집 오픈하러 가기
		}
		
		// 백트래킹
		// 0 ~ chicken.size() - 1번째 치킨집 모두 탐색
		// 오픈된 치킨집은 총 M개이므로, depth번째 치킨집도 M번 들어가야 함(인덱스는 M - 1까지)
		for (int i = index; i < chickens.size(); i++) {			
			if (!selected[i]) {			// i번째 치킨집이 폐업 상태라면
				selected[i] = true;		// i번째 치킨집 오픈시키기
				open[depth] = i;		// depth번째 오픈된 치킨집 = i번째 치킨집
				// depth + 1번째 치킨집 오픈하러 가기
				// 앞에는 이미 탐색했으므로 i + 1번째부터 탐색해야 시간 복잡도가 줄어듦
				dfs(depth + 1, i + 1);	
				selected[i] = false;	// i번째 치킨집 폐업시키기
			}
		}
	}
}