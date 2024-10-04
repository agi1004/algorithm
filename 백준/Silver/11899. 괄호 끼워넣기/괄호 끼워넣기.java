import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		Stack<Character> stack = new Stack<>();
		int count = 0;
		
		for (int i = 0; i < S.length(); i++) {
			char ch = S.charAt(i);
			
			if (ch == '(') {
				stack.push(ch);
			} else {
				if (!stack.isEmpty()) {
					stack.pop();
				} else {
					count++;
				}
			}
		}
		
		if (!stack.isEmpty()) {
			count += stack.size();
		}
		
		System.out.println(count);
	}
}