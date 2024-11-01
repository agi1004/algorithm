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
		
		String result = getResult();
		
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
				StringBuilder sb = new StringBuilder();
				
				append(sb, len);
				
				if (!sb.toString().equals(bombStr)) {
					push(sb, len);
				}
			}
		}
	}
	
	public static String getResult() {
		StringBuilder sb = new StringBuilder();
		
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		return sb.reverse().toString();
	}
	
	private static void append(StringBuilder sb, int len) {
		for (int i = 0; i < len; i++) {
			sb.append(stack.pop());
		}
		sb.reverse();
	}
	
	private static void push(StringBuilder sb, int len) {
		for (int i = 0; i < len; i++) {
			stack.push(sb.charAt(i));
		}
	}
}