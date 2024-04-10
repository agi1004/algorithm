import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 승환이의 앞에 서 있는 학생들의 수
		int[] student = new int[N];		// 승환이의 앞에 서 있는 학생들 배열
		Stack<Integer> stack = new Stack<>();	
		int nowOrder = 1;	// 현재 순서
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			student[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			stack.push(student[i]);		// 대기열 한명 스택에 삽입
			
			while (!stack.isEmpty()) {	// 스택이 비어있지 않다면 반복
				if (nowOrder == stack.peek()) {	// 현재 순서와 스택의 가장 윗부분이 같다면
					stack.pop();	// 스택에서 빼기 (간식 받기)
					nowOrder++;		// 현재 순서 증가
				} else {	// 현재 순서와 스택의 가장 윗부분이 같지 않다면
					break;	// while문 탈출하고 다음 대기자 차례로 가기
				}
			}
		}
		
		if (stack.isEmpty()) {	// 스택에 값이 남아있지 않다면 올바른 순서로 간식 받기 가능
			System.out.println("Nice");	
		} else {				// 스택에 값이 남아있다면 올바른 순서로 간식 받기 불가
			System.out.println("Sad");
		}
	}
}