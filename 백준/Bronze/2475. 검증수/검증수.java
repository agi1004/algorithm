import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] nums = new int[5];
		int sum = 0;
		
		for (int i = 0; i < 5; i++) {
			nums[i] = sc.nextInt();
			sum += nums[i] * nums[i];
		}
		
		System.out.println(sum % 10);
		sc.close();
	}
}