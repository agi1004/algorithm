// 아래 글 참고
// https://youngdroidstudy.tistory.com/entry/%EB%B0%B1%ED%8A%B8%EB%9E%98%ED%82%B9-%EB%B0%B1%ED%8A%B8%EB%9E%98%ED%82%B9%EC%9D%98-%EC%84%A4%EB%AA%85%EA%B3%BC-%EA%B0%84%EB%8B%A8%ED%95%9C-%EC%98%88%EC%A0%9C%ED%92%80%EC%9D%B4

import java.util.Scanner;

public class Main {
	static boolean[] visited;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 모두 구하기
		int N = sc.nextInt();	// 자연수 범위: 1 ~ N
		int M = sc.nextInt();	// 수열 길이: M
		visited = new boolean[N + 1];	// 방문 배열
		arr = new int[M];				// 수열 배열 (depth는 0부터 시작)
		
		dfs(N, M, 0);	// depth: 0
	}
	
	public static void dfs(int N, int M, int depth) {
		if (depth == M) {	// depth와 M(수열 길이)이 같으면
			for (int num : arr) {
				System.out.print(num + " ");	// 수열 배열 출력
			}
			System.out.println();
			return;			// 함수 종료
		}
		
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {			// 해당 정점을 방문하지 않았다면
				visited[i] = true;		// 방문 체크
				arr[depth] = i;			// 해당 depth에 i 넣기
				dfs(N, M, depth + 1);	// depth 1 증가 후 재귀함수 호출
				visited[i] = false;		// 모두 탐색해야 하므로 visited를 다시 false로 변경
			}
		}
	}

}