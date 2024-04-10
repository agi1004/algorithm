import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] fibo = new int[n + 1];
		
		fibo[0] = 1;
		fibo[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 10007;
		}
		
		System.out.println(fibo[n]);
	}
}