import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int result = 1;
		int share = 1;
		
		int count = 0;
		while (count < K) {
			result *= N--;
			count++;
		}
		
		for (int i = 1; i <= K; i++) {
			share *= i;
		}
		
		System.out.println(result / share);
	}
}