import java.io.*;
import java.util.*;

public class Main {
	static final String EMPTY = "FRULA";
	static Stack<Character> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String bombStr = br.readLine();
		int len = bombStr.length();
		
		bomb(str, bombStr, len);
		
		String result = pop(stack.size());
		
		if (result.isEmpty()) {
			System.out.println(EMPTY);
			return;
		}
		
		System.out.println(result);
	}
	
	public static void bomb(String str, String bombStr, int len) {
		for (char ch : str.toCharArray()) {
			stack.push(ch);
			
			if (stack.size() >= len && ch == bombStr.charAt(len - 1)) {
				String partStr = pop(len);
				
				if (!partStr.equals(bombStr)) {
					push(partStr, len);
				}
			}
		}
	}
	
	public static String pop(int len) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < len; i++) {
			sb.append(stack.pop());
		}
		
		return sb.reverse().toString();
	}
	
	public static void push(String partStr, int len) {
		for (int i = 0; i < len; i++) {
			stack.push(partStr.charAt(i));
		}
	}
}