import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;						// 2차원 도시 배열의 행, 열 개수
	static int M;						// 폐업시키지 않을 치킨집의 개수
	static ArrayList<Point> houses;		// 집 좌표 리스트
	static ArrayList<Point> chickens;	// 치킨집 좌표 리스트
	static int[] open;					// 오픈한 치킨집 인덱스 배열
	static boolean[] selected;			// 치킨집의 오픈 여부 배열
	static int cityChickenLength;		// 도시의 치킨 거리의 최솟값	
	
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
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());	
		houses = new ArrayList<>();
		chickens = new ArrayList<>();
		open = new int[M];
		cityChickenLength = Integer.MAX_VALUE;
		
		// 2차원 도시 배열 초기화
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int spot = Integer.parseInt(st.nextToken());
				
				if (spot == 1) {
					houses.add(new Point(i, j));
				} else if (spot == 2) {
					chickens.add(new Point(i, j));
				}
			}
		}
		
		selected = new boolean[chickens.size()];
		
		dfs(0, 0);
		
		System.out.println(cityChickenLength);
	}
	
	public static void dfs(int depth, int index) {
		if (depth == M) {
			int tempChickenLength = 0;
			
			for (Point house : houses) {
				int houseCheckenLength = Integer.MAX_VALUE;
				
				for (int i = 0; i < M; i++) {
					Point chicken = chickens.get(open[i]);
					int chickenlength = Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
					houseCheckenLength = Math.min(houseCheckenLength, chickenlength);
				}
				
				tempChickenLength += houseCheckenLength;
			}
			
			cityChickenLength = Math.min(cityChickenLength, tempChickenLength);
			return;
		}
		
		for (int i = index; i < chickens.size(); i++) {			
			if (!selected[i]) {
				selected[i] = true;
				open[depth] = i;
				dfs(depth + 1, i + 1);
				selected[i] = false;
			}
		}
	}
}