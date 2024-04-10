import java.util.Scanner;

public class Main {
	static int N;
	static int[] arr;
	static int result = 0;	// 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	// N x N 체스판 위에 있는 퀸의 개수
		arr = new int[N];	// depth가 행의 역할을 하므로 열만 저장하는 1차원 배열 만들기
		
		dfs(0);
		
		System.out.println(result);
	}
	
	public static void dfs(int depth) {
		// depth가 N이란 것은 중간에 끊기지 않고 N개의 퀸을 모두 놓을 수 있었다는 뜻
		if (depth == N) {
			result++;	// 경우의 수 증가
			return;
		}
		
		for (int i = 0; i < N; i++) {
			arr[depth] = i;	        // 해당 퀸 놓기
			if(examine(depth)) {	// 해당 퀸의 위치가 다른 퀸의 열, 대각선과 겹치지 않는다면
				dfs(depth + 1);		// depth + 1(행 1 증가) 재귀 호출
			}
		}
	}
	
	// 해당 퀸의 위치가 다른 퀸의 열, 대각선과 겹치는지 검사
	public static boolean examine(int depth) {
		for (int i = 0; i < depth; i++) {
			// 해당 퀸의 위치가 다른 퀸의 열, 대각선과 겹친다면
			if (arr[depth] == arr[i] || Math.abs(arr[depth] - arr[i]) == depth - i) {
				return false;	// false 반환 후 재귀 호출 X
			}
		}
		// 모든 퀸의 위치가 다른 퀸의 열, 대각선과 겹치지 않는다면
		return true;	// true 반환 후 재귀 호출 O
	}
}