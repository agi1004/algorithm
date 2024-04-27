import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 색종이의 수
		int[][] paper = new int[101][101];			// 색종이 영역 배열
		int area = 0;	// 색종이가 붙은 검은 영역의 넓이
		
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());	// 색종이 x좌표
			int y = Integer.parseInt(st.nextToken());	// 색종이 y좌표
			
			for (int i = x; i < x + 10; i++) {
				for (int j = y; j < y + 10; j++) {
					paper[i][j] = 1;	// 해당 색종이 영역에 1 대입
				}
			}
		}
		
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				area += paper[i][j];	// 색종이 영역 모두 더하기
			}
		}
		
		System.out.println(area);	// 색종이가 붙은 검은 영역의 넓이 출력
	}
}