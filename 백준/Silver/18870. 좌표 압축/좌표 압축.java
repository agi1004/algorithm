import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] X;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = new int[N];
		int[] temp = new int[N];
		int[] answer = new int[N];
		
		for (int i = 0; i < N; i++) {
			X[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			temp[i] = X[i];
		}
		
		Arrays.sort(X);
		
		for (int i = 1; i < N; i++) {
			if (X[i] > X[i - 1]) {
				answer[i] = answer[i - 1] + 1;
			} else {
				answer[i] = answer[i - 1];
			}
		}
		
		for (int i = 0; i < N; i++) {
			int index = binarySearch(temp[i]);
			bw.write(answer[index] + " ");
		}
		
		bw.flush();
	}
	
	public static int binarySearch(int target) {
		int start = 0;
		int end = X.length - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (target == X[mid]) {
				return mid;
			} else if (target < X[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		return -1;
	}
}