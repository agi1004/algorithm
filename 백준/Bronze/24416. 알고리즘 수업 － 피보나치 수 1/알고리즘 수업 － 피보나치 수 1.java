import java.util.Scanner;

public class Main {
	static int count1 = 0;	// 코드1 실행 횟수
	static int count2 = 0;	// 코드2 실행 횟수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		fib(n);			// 코드1
		fibonacci(n);	// 코드2
		
		System.out.println(count1 + " " + count2);
	}
	
	// 코드1
	public static int fib(int n) {
		if (n == 1 || n == 2) {
			count1++;	// 코드1 실행 횟수 1 증가
			return 1;
		} else {
			return fib(n - 1) + fib(n - 2);
		}
	}
	
	// 코드2
	public static int fibonacci(int n) {
		int[] f = new int[n + 1];
		
		f[1] = f[2] = 1;
		
		for (int i = 3; i <= n; i++) {
			count2++;	// 코드2 실행 횟수 2 증가
			f[i] = f[i - 1] + f[i - 2];
		}
		
		return f[n];
	}
}