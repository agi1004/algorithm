import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] a;
	static int x;
	static int count = 0;	// 두 수의 합이 x가 되는 쌍의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());	// 수열의 크기
		a = new int[n];								// 수열
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		x = Integer.parseInt(br.readLine());		// 두 수의 합
		
		Arrays.sort(a);
		
		twoPointer(0, n - 1);    // 투포인터 알고리즘 실행 전에는 무조건 오름차순 정렬을 해야 함
		
		System.out.println(count);
	}
	
	public static void twoPointer(int s, int e) {
		while (s < e) {
			if (a[s] + a[e] == x) {
				count++;
				s++;
			} else if (a[s] + a[e] < x) {
				s++;
			} else {
				e--;
			}
		}
	}
}