import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long M = sc.nextLong();
		long N = sc.nextLong();
		
		if (M > N) {
			System.out.println((N - 1) * 2 + 1);
		} else {
			System.out.println((M - 1) * 2);
		}
		
		if (M == N) {
			if (M % 2 == 0) {
				System.out.println((M / 2 + 1) + " " + (N / 2));
			} else {
				System.out.println((M / 2 + 1) + " " + (N / 2 + 1));
			}	
		} else if (M > N) {
			if (N % 2 == 0) {
				System.out.println((N / 2 + 1) + " " + (N / 2));
			} else {
				System.out.println((N / 2 + 1 + (M - N)) + " " + (N / 2 + 1));
			}
		} else { 	
			if (M % 2 == 0) {
				System.out.println((M / 2 + 1) + " " + (M / 2));
			} else {
				System.out.println((M / 2 + 1) + " " + (M / 2 + 1 + (N - M)));
			}
		}
	}
}