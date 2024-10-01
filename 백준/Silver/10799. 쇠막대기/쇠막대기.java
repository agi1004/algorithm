import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		Stack<Character> stack = new Stack<>();
		int count = 0;
		
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);	// 현재 괄호
			
			if (ch == '(') {	// 현재 괄호가 열린 괄호라면
				stack.push(ch);	// 스택에 추가
			} else {	// 현재 괄호가 닫힌 괄호라면
				stack.pop();	// 스택에서 뽑기
				
				if (input.charAt(i - 1) == '(') {	// 이전 괄호가 열린 괄호라면 레이저라는 뜻
					count += stack.size();	// 잘려진 조각이 스택의 크기만큼 발생
				} else {	// 이전 괄호가 닫힌 괄호라면
					count++;	// 잘려진 조각 1개 발생
				}
			}
		}
		
		System.out.println(count);
	}
}