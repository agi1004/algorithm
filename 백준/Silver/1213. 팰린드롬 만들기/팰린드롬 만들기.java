import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine();
		Map<Character, Integer> map = new TreeMap<>();
		
		for (char ch : name.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}
		
		int oddCount = 0;
		char oddChar = '\0';
		
		for (char ch : map.keySet()) {
			if (map.get(ch) % 2 == 1) {
				oddCount++;
				oddChar = ch;
			}
		}
		
		if (oddCount > 1) {
			System.out.println("I'm Sorry Hansoo");
			return;
		}
		
		StringBuilder halfAnswer = new StringBuilder();
		
		for (char ch : map.keySet()) {
			for (int i = 0; i < map.get(ch) / 2; i++) {
				halfAnswer.append(ch);
			}
		}
		
		String answer = halfAnswer.toString();
		String reverse = halfAnswer.reverse().toString();
		
		if (oddCount == 1) {
			answer += oddChar;
		}

		answer += reverse;
		
		System.out.println(answer);
	}
}