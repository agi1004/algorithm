import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		Num[] dp = new Num[N + 1];
		
		dp[N] = new Num(0, N);
		
		for (int i = N; i >= 1; i--) {
			if (dp[i - 1] == null) {
				dp[i - 1] = new Num(dp[i].count + 1, i); 
			} else if (dp[i].count + 1 < dp[i - 1].count) {
				dp[i - 1].count = dp[i].count + 1;
				dp[i - 1].before = i;
			}
			
			if (i % 2 == 0) {
				if (dp[i / 2] == null) {
					dp[i / 2] = new Num(dp[i].count + 1, i);
				} else if (dp[i].count + 1 < dp[i / 2].count) {
					dp[i / 2].count = dp[i].count + 1;
					dp[i / 2].before = i;
				}
			}
			
			if (i % 3 == 0) {
				if (dp[i / 3] == null) {
					dp[i / 3] = new Num(dp[i].count + 1, i);
				} else if (dp[i].count + 1 < dp[i / 3].count) {
					dp[i / 3].count = dp[i].count + 1;
					dp[i / 3].before = i;
				}
			}
		}
		
		int n = 1;	
		ArrayList<Integer> nums = new ArrayList<>();
		
		nums.add(1);

		while (dp[n].count != 0) {
			nums.add(dp[n].before);
			n = dp[n].before;	
		}
		
		bw.write(dp[1].count + "\n");
		
		for (int i = nums.size() - 1; i >= 0; i--) {
			bw.write(nums.get(i) + " ");
		}
		
		bw.flush();
	}
	
	static class Num {
		int count, before;
		
		Num(int count, int before) {
			this.count = count;
			this.before = before;
		}
	}
}