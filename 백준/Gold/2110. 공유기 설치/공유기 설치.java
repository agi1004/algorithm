import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] x;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		x = new int[N];
		
		for (int i = 0; i < N; i++) {
			x[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(x);
		
		int start = 1;				// 최소 거리는 1
		int end = x[N - 1] - x[0];	// 최대 거리는 첫 집과 마지막 집 사이의 거리
		int mid = (start + end) / 2;	// 인접한 두 공유기 간 거리
		int count = findCount(mid);		// 공유기 설치 개수 (C와 같게 만들기)
		
		while (start <= end) {
			if (count < C) {		// 공유기 설치 개수가 필요 개수보다 적다는 것은 인접한 두 공유기 간 거리가 너무 크기 때문
				end = mid - 1;		// end를 줄여서 더 작은 값으로 인접한 두 공유기 간 거리 설정
			} else {				
				start = mid + 1;	
			}
			
			mid = (start + end) / 2;	// 인접한 두 공유기 간 거리 조정
			count = findCount(mid);		// 공유기 설치 개수 (C와 같게 만들기)
		}
		
		// while 문을 빠져나온 후 mid의 최종 값은 인접한 두 공유기 간 최대 거리
		System.out.println(mid);		
	}
	
	public static int findCount(int dist) {
		int count = 1;		// 첫 집에는 무조건 공유기를 설치해야 하므로 count를 1부터 시작
		int prevHouse = x[0];	// 첫 집부터 탐색 시작
		
		for (int i = 1; i < x.length; i++) {
			if (x[i] - prevHouse >= dist) {	// 현재 집과 이전 집 사이의 거리가 설정한 거리 이상일 경우
				count++;			// 공유기를 설치하고 count 증가
				prevHouse = x[i];	// 공유기를 설치한 집으로 이전 집 갱신
			}
		}
		
		return count;		// 공유기 설치 개수 반환
	}
}