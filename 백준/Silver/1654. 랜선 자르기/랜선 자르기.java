import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] line;
	//static long maxLength = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());	// 친구가 이미 가지고 있는 랜선의 개수
		int N = Integer.parseInt(st.nextToken());	// 내가 필요한 랜선의 개수
		line = new int[K];
		
		for (int i = 0; i < K; i++) {
			line[i] = Integer.parseInt(br.readLine());
		}
		
		long start = 1;
		long end = Arrays.stream(line).max().getAsInt();
		long mid = (start + end) / 2;
		long count = findCount(mid);
		
		while (start <= end) {
			if (count == N) {
//				maxLength = Math.max(maxLength, mid);
				start = mid + 1;
			} else if (count < N) {		// 더 작은 값으로 잘라야 함
				end = mid - 1;
			} else {					// 더 큰 값으로 잘라야 함
				start = mid + 1;
			}
			
			mid = (start + end) / 2;
			count = findCount(mid);
		}
		
		//System.out.println(maxLength);
		System.out.println(mid);
	}
	
	public static long findCount(long length) {
		long count = 0;
		
		for (int i = 0; i < line.length; i++) {
			count += line[i] / length;
		}
		
		return count;
	}
}