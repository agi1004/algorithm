import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());	// 2차원 세계의 세로 길이
		int W = Integer.parseInt(st.nextToken());	// 2차원 세계의 가로 길이
		int[] heights = new int[W];		// 각 열의 블록 높이 배열
		int totalWater = 0;				// 고이는 빗물의 총량
		
		st = new StringTokenizer(br.readLine());
		// 각 열의 블록 높이 배열 초기화
		for (int i = 0; i < W; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		
		// 각 열에 대해 고일 수 있는 빗물 계산
		for (int i = 0; i < W; i++) {
			int leftMax = 0;
			int rightMax = 0;
			
			// 현재 열의 왼쪽에서 가장 높은 블록의 높이 찾기
			for (int j = 0; j < i; j++) {
				leftMax = Math.max(leftMax, heights[j]);
			}
			
			// 현재 열의 오른쪽에서 가장 높은 블록의 높이 찾기
			for (int j = i; j < W; j++) {
				rightMax = Math.max(rightMax, heights[j]);
			}
			
			// 현재 열에서 고일 수 있는 빗물의 양 계산
			totalWater += Math.max(0, Math.min(leftMax, rightMax) - heights[i]);
		}
		
		System.out.println(totalWater);		// 고이는 빗물의 총량 출력
	}
}