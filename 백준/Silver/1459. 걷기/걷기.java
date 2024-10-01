import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long X = Integer.parseInt(st.nextToken());
		long Y = Integer.parseInt(st.nextToken());
		long W = Integer.parseInt(st.nextToken());	// 평행 이동 시간
		long S = Integer.parseInt(st.nextToken());	// 대각선 이동 시간
		
		// 평행으로만 이동
		long time1 = (X + Y) * W;
		// X좌표와 Y좌표 중 더 작은 좌표만큼 대각선 이동 후, 두 좌표 차이의 절댓값만큼 평행 이동
		long time2 = Math.min(X, Y) * S + Math.abs(X - Y) * W;
		
		long time3 = 0;
		
		if ((X + Y) % 2 == 0) {	// X좌표 + Y좌표가 짝수라면
			// 둘 중 더 큰 좌표만큼 대각선으로만 이동
			time3 = Math.max(X, Y) * S;	
		} else {	// X좌표 + Y좌표가 홀수라면
			// 둘 중 더 큰 좌표 - 1만큼 대각선 이동 후, 1번 평행 이동
			time3 = (Math.max(X, Y) - 1) * S + W;
		}
		
		// 위의 경우의 수 중 가장 작은 값 구하기
		System.out.println(Math.min(time1, Math.min(time2, time3)));
	}
}