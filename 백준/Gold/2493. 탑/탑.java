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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] tower = new int[N + 1];
		int[] result = new int[N + 1];
		Stack<Integer> stack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			tower[i] = Integer.parseInt(st.nextToken());
		}
		
		stack.push(1);
		
		for (int i = 2; i <= N; i++) {
			while (true) {
				if (tower[i] < tower[stack.peek()]) {
					result[i] = stack.peek();
					break;
				}
				
				stack.pop();
				
				if (stack.isEmpty()) {
					break;
				}
			}
			
			stack.push(i);
		}
		
		for (int i = 1; i <= N; i++) {
			bw.write(result[i] + " ");
		}
		
		bw.flush();
	}
}