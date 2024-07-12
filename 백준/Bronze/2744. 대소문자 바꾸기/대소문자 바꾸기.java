import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String word = sc.next();
		String newWord = "";
		
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				newWord += (char)(c + 32);
			} else {
				newWord += (char)(c - 32);
			}
		}
		
		System.out.println(newWord);
		sc.close();
	}
}