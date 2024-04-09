import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] meeting = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			meeting[i][0] = sc.nextInt();
			meeting[i][1] = sc.nextInt();
		}
		
		// 두번째 열(종료 시간) 기준으로 오름차순 정렬
		Arrays.sort(meeting, new Comparator<int[]>() {
			@Override
			public int compare(int[] S, int[] E) {
				if (S[1] == E[1]) {		// 종료 시간이 같을 때
					return S[0] - E[0];	// 시작 시간 기준으로 오름차순 정렬 
				}
				return S[1] - E[1];		// 종료 시간 기준으로 오름차순 정렬
			}
		});
	
		int count = 0;
		int lastTime = -1;
		for (int i = 0; i < N; i++) {
			if (meeting[i][0] >= lastTime) {
				count++;
				lastTime = meeting[i][1];
			}
		}
		
		System.out.println(count);
		sc.close();
	}
}