import java.util.Scanner;

public class Main {
	static int N, M;
	static boolean[] visited;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[M];
		
		dfs(1, 0);
	}
	
	public static void dfs(int start, int depth) {
		if (depth == M) {
			for (int num : arr) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		
		// i를 이전 깊이에 있던 노드보다는 무조건 큰 값부터 탐색하므로
		// visited 배열로 방문 체크를 할 필요 없음
		for (int i = start; i <= N; i++) {
			arr[depth] = i;
			dfs(i + 1, depth + 1);	// 현재 인덱스보다 더 큰 값부터 탐색
		}
	}
}