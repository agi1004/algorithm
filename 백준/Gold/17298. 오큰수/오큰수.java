import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] NGE = new int[N];
		Stack<Integer> stack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		stack.push(0);
		for (int i = 1; i < N; i++) {
			if (A[i] > A[stack.peek()]) {
				NGE[stack.pop()] = A[i];
				if (!stack.isEmpty()) {
					i--;
				}
			} else {
				stack.push(i);
			}
			if (stack.isEmpty()) {
				stack.push(i);
			}
		}
		
		while (!stack.isEmpty()) {
			NGE[stack.pop()] = -1;
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < N; i++) {
			bw.write(NGE[i] + " ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}