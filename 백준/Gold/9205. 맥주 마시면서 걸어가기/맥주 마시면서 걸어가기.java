import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Point[] points;		// 좌표 정보가 담긴 1차원 배열
	static boolean[] visited;	// 방문 체크 1차원 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());	// 테스트 케이스의 개수
		
		// 테스트 케이스만큼 반복
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());	// 편의점의 개수
			// 좌표의 개수: 편의점의 개수 + 2 = 편의점의 개수 + 집 1개 + 페스티벌 1개
			points = new Point[n + 2];		// 좌표 배열 크기 초기화
			visited = new boolean[n + 2];	// 방문 배열 크기 초디화
			
			// 집, 편의점, 페스티벌 좌표 입력받기
			for (int j = 0; j < n + 2; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				points[j] = new Point(x, y);	// 좌표 정보를 배열에 저장
			}
			
			if (bfs()) {	// bfs 수행 결과가 true일 때
				System.out.println("happy");
			} else {		// bfs 수행 결과가 false일 때
				System.out.println("sad");
			}
		}
	}
	
	public static boolean bfs() {
		Queue<Point> queue = new LinkedList<>();	// bfs 수행을 위한 큐
		queue.add(points[0]);	// 맨 처음(집) 좌표를 큐에 삽입
		visited[0] = true;		// 맨 처음(집)의 인덱스를 방문 체크
		
		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			Point now = queue.poll();	// 현재 좌표를 큐에서 뽑기
			
			// 좌표 개수만큼 반복
			for (int i = 0; i < points.length; i++) {
				// 해당 좌표를 방문하지 않았거나, 현재 좌표와 해당 좌표의 거리 차이가 1000 이하라면
				// ㄴ 1000 이하인 이유: 맥주 한 박스에는 맥주 20개가 들어있고, 50미터에 한 병씩 마셔야 하므로
				//                 20 * 50 = 1000 을 넘으면 안된다.
				if (!visited[i] && distance(now, points[i]) <= 1000) {
					// 해당 좌표가 좌표 배열의 마지막 인덱스라면 목적지 좌표
					if (i == points.length - 1) {	
						return true;		// 무조건 갈 수 있으므로 true 리턴
					}
					queue.add(points[i]);	// 해당 좌표를 큐에 삽입
					visited[i] = true;		// 해당 좌표의 인덱스를 방문 체크
				}
			}
		}
		
		return false;	// 더이상 갈 좌표가 큐에 없으므로 false 리턴
	}
	
	// 두 좌표 사이의 거리를 구하는 메서드
	public static int distance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
	
	static class Point {
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}