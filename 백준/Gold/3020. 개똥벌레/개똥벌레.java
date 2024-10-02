import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[] down = new int[N / 2];
		int[] up = new int[N / 2];
		
		for (int i = 0; i < N / 2; i++) {
			down[i] = Integer.parseInt(br.readLine());
			up[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(down);
		Arrays.sort(up);
		
		int min = N;
		int count = 0;
		
		for (int i = 1; i <= H; i++) {
			int conflict = binarySearch(i, down) + binarySearch(H - i + 1, up);
			
			if (conflict == min) {
				count++;
			} else if (conflict < min) {
				min = conflict;
				count = 1;
			}
		}
		
		System.out.println(min + " " + count);
	}
	
	public static int binarySearch(int h, int[] arr) {
		int start = 0, end = arr.length;
		
		while (start < end) {
			int mid = (start + end) / 2;
			
			if (arr[mid] >= h) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		
		return arr.length - end;
	}
}