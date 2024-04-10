import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String result = "";
		
		for (int i = 0; i < N; i += 4) {
			result += "long ";
		}
		result += "int";
		
		System.out.println(result);
	}
}