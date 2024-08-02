import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Homework {
		int d, w;
		
		Homework(int d, int w) {
			this.d = d;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Homework> homeworks = new ArrayList<>();
		int maxDay = 0;
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			homeworks.add(new Homework(d, w));
			maxDay = Math.max(maxDay, d);
		}
		
		int result = 0;

		for (int i = maxDay; i >= 1; i--) {
			int max = 0;
			int index = -1;
			
			for (int j = 0; j < homeworks.size(); j++) {
				Homework homework = homeworks.get(j);
				
				if (i <= homework.d && homework.w > max) {
					max = homework.w;
					index = j;
				}
			}
			
			if (index != -1) {
				result += max;
				homeworks.remove(index);
			}	
		}
		
		System.out.println(result);
	}
}