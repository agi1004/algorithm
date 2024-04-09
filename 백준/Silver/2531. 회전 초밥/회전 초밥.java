import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 총 접시의 수 (2 ≤ N ≤ 30,000)
		int d = Integer.parseInt(st.nextToken());	// 초밥의 가짓수 (2 ≤ d ≤ 3,000)
		int k = Integer.parseInt(st.nextToken());	// 연속해서 먹는 접시의 수 (2 ≤ k ≤ 3,000 (k ≤ N))
		int c = Integer.parseInt(st.nextToken());	// 쿠폰 번호 (1 ≤ c ≤ d)				
		ArrayList<Integer> plant = new ArrayList<>();	// 총 접시 list
		HashSet<Integer> eat = new HashSet<>();			// 연속해서 먹는 접시 set
		int maxCount = 0;
		
		for (int i = 0; i < N; i++) {
			plant.add(Integer.parseInt(br.readLine()));
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < k; j++) {
				eat.add(plant.get(j));
			}
	
			eat.add(c);
			
			maxCount = Math.max(maxCount, eat.size());
			
			int first = plant.get(0);
			plant.remove(0);
			plant.add(first);
			
			eat.clear();
		}
		
		System.out.println(maxCount);
	}
}