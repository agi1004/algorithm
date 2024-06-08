import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		Num[] A = new Num[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());
			A[i] = new Num(now, 1, -1);
		}
		
		int maxIdx = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (A[j].now < A[i].now && A[j].order + 1 > A[i].order) {
					A[i].order++;
					A[i].beforeIdx = j;
				}
			}
			if (A[i].order > A[maxIdx].order) {
				maxIdx = i;
			}
		}
		
		ArrayList<Integer> LIS = new ArrayList<>();
		int idx = maxIdx;
		
		while (true) {
			LIS.add(A[idx].now);
			idx = A[idx].beforeIdx;
			if (idx == -1) {
				break;
			}
		}
		
		bw.write(A[maxIdx].order + "\n");
		
		for (int i = LIS.size() - 1; i >= 0; i--) {
			bw.write(LIS.get(i) + " ");
		}
		
		bw.flush();
	}
	
	static class Num {
		int now, order, beforeIdx;
		
		Num(int now, int order, int beforeIdx) {
			this.now = now;
			this.order = order;
			this.beforeIdx = beforeIdx;
		}
	}
}