import java.util.Scanner;

public class Main {
	static int count = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] A = new int[n];
		
		MenOfPassion(A, n);
		
		System.out.println(count);
		System.out.println(count - 1);
	}
	
	public static int MenOfPassion(int[] A, int n) {
		count++;
		int i = n / 2;
		return A[i];
	}
}