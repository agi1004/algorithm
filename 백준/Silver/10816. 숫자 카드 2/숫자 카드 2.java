import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] card = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] arr = new int[M];
		HashMap<Integer, Integer> map = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int n = Integer.parseInt(st.nextToken());
			arr[i] = n;
			map.put(n, 0);
		}
		
		for (int i = 0; i < N; i++) {
			if (map.containsKey(card[i])) {
				int count = map.get(card[i]);
				map.put(card[i], ++count);
			}
		}
		
		for (int i = 0; i < M; i++) {
			bw.write(map.get(arr[i]) + " ");
		}
		
		bw.flush();
	}
}