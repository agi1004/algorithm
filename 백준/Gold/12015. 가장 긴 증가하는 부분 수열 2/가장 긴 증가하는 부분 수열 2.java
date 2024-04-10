import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer> LIS = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		LIS.add(A[0]);	// LIS 초기 값으로 첫번째 수열의 값을 가짐
		
		for (int i = 1; i < N; i++) {	
			// A[i]가 LIS의 마지막 값보다 클 경우 추가
			if (A[i] > LIS.get(LIS.size() - 1)) {
				LIS.add(A[i]);
			} else {
				// A[i]보다 큰 가장 가까운 값이 위치한 곳에 A[i] 값으로 교체
				int replaceIndex = lowerBound(A[i]);
				LIS.set(replaceIndex, A[i]);
			}
		}
		
		System.out.println(LIS.size());		// LIS 길이 출력
	}
	
	public static int lowerBound(int key) {
		int start = 0, end = LIS.size() - 1;
		
		while (start < end) {
			int mid = (start + end) / 2;
			
			if (key <= LIS.get(mid)) {
				end = mid;
			} else {
				start = mid + 1;  
			}
		}

		return start;	// LIS에서 대치될 key의 위치
	}
}