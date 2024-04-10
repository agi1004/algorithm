// Do it 알고리즘 코딩테스트 자바 p.530
import java.util.Scanner;

public class Main {
	static Matrix[] M;
	static int[][] D;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		M = new Matrix[N + 1];
		D = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				D[i][j] = -1;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			M[i] = new Matrix(x, y);
		}
		
		System.out.println(execute(1, N));
	}
	
	// 톱-다운 방식으로 점화식 함수 구현하기
	public static int execute(int s, int e) {
		int result = Integer.MAX_VALUE;
		
		if (D[s][e] != -1) {			// 계산했던 부분이면 다시 계산하지 않음 (메모이제이션)
			return D[s][e];
		}
		
		if (s == e) {					// 행렬 1개의 곱셈 연산 수
			return 0;
		}
		
		if (s + 1 == e) {				// 행렬 2개의 곱셈 연산 수
			return M[s].x * M[s].y * M[e].y;
		}
		
		for (int i = s; i < e; i++) {	// 행렬이 3개 이상일 때 곱셈 연산 수 -> 점화식 처리
			result = Math.min(result, M[s].x * M[i].y * M[e].y + execute(s, i) + execute(i + 1, e));
		}
		
		return D[s][e] = result;
	}
}

// 행렬 정보 저장 클래스
class Matrix {
	int x, y;
	
	Matrix(int x, int y) {
		this.x = x;
		this.y = y;
	}
}