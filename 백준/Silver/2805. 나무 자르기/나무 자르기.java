import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 나무의 수
		int M = Integer.parseInt(st.nextToken());	// 가져가려고 하는 나무의 길이
		tree = new int[N];						// 나무의 높이 배열
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = Arrays.stream(tree).max().getAsInt();	// 최대 높이 나무의 인덱스
		int mid = (start + end) / 2;	// 절단기 높이
		long result = findResult(mid);	// 절단기로 자른 뒤 가져가려고 하는 나무의 총 길이
		
		while (start <= end) {
			if (result < M) {		// 나무를 더 적게 잘라야 하므로 end 줄이기 
				end = mid - 1;		// 더 작은 수로 잘라야 나무를 더 많이 가져갈 수 있음
			} else {				// 나무를 더 많이 잘라야 하므로 start 늘리기
				start = mid + 1;	// 더 큰 수로 잘라야 나무를 더 적게 가져갈 수 있음
			}
			
			// result가 M 이상인 것을 만족하고 start가 커질수록
			// 나무를 더 적게 가져갈 수 있으므로 굳이 Math.max()로 최댓값 갱신할 필요 없음
			
			mid = (start + end) / 2;	// 절단기 높이 조절
			result = findResult(mid);	// 절단기로 자른 뒤 가져가려고 하는 나무의 총 길이 구하기
		}
		
		System.out.println(mid);		// 절단기 높이 출력		
	}
	
	public static long findResult(int height) {
		long result = 0;		
		
		for (int i = 0; i < tree.length; i++) {
			// tree[i] - height: 자른 나무의 길이
			// 자른 나무의 길이가 음수인 경우는 더하지 않음 (무조건 0 이상이 더해짐)
			result += Math.max(tree[i] - height, 0);	
		}
		
		return result;
	}
}