import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] paper = new int[N + 1];
		Deque<Integer> deque = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			paper[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			deque.add(i);
		}
		
		int n = deque.pollFirst();
		bw.write(n + " ");
		
		while (!deque.isEmpty()) {
			if (paper[n] > 0) {
				for (int i = 0; i < Math.abs(paper[n]) - 1; i++) {
					deque.addLast(deque.pollFirst());
				}
				
				n = deque.pollFirst();
				
			} else {
				for (int i = 0; i < Math.abs(paper[n]) - 1; i++) {
					deque.addFirst(deque.pollLast());
				}
				
				n = deque.pollLast();
			}
			
			bw.write(n + " ");
		}
		
		bw.flush();
	}
}