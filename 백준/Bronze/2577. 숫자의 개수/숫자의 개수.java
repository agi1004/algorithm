import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		int[] numCounts = new int[10];
		int mul = A * B * C;
		String mulToStr = String.valueOf(mul);
		
		for (int i = 0; i < mulToStr.length(); i++) {
			int num = mulToStr.charAt(i) - '0';
			numCounts[num]++;
		}
		
		for (int count : numCounts) {
			System.out.println(count);
		}
	}
}