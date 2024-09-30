import java.io.*;
import java.util.*;

public class Main {
	static List<Integer> nums = new ArrayList<>();
	static List<Character> ops = new ArrayList<>();
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String line = br.readLine();
		
		for (int i = 0; i < N; i++) {
			char ch = line.charAt(i);
			if (ch == '+' || ch == '-' || ch == '*') {
				ops.add(ch);
			} else {
				nums.add(Character.getNumericValue(ch));
			}
		}
		
		// 0: 다음 연산할 연산자의 인덱스
		// nums.get(0): 현재까지의 연산 결과
		dfs(0, nums.get(0));
		
		System.out.println(max);
	}
	
	// depth: 현재 연산할 연산자의 인덱스
	// result: 이전까지의 연산 결과
	public static void dfs(int depth, int result) {
		// 주어진 연산자의 개수를 초과하였을 경우
		if (depth == ops.size()) {
			max = Math.max(max, result);
			return;
		}
		
		// 괄호가 없는 경우
		int res1 = calc(ops.get(depth), result, nums.get(depth + 1));
		dfs(depth + 1, res1);
		
		// 괄호가 있는 경우
		if (depth + 1 < ops.size()) {
			// result의 오른쪽에 있는 값을 연산 (괄호 연산 값)
			int res2 = calc(ops.get(depth + 1), nums.get(depth + 1), nums.get(depth + 2));
			
            // 괄호 오른쪽에 존재하는 연산자의 인덱스, 현재 result와 방금 구한 괄호 값을 연산한 결과를 넘김
			dfs(depth + 2, calc(ops.get(depth), result, res2));
		}
	}
	
	private static int calc(char op, int n1, int n2) {
		switch (op) {
			case '+':
				return n1 + n2;
			case '-':
				return n1 - n2;
			case '*':
				return n1 * n2;
		}
		return -1;
	}
}