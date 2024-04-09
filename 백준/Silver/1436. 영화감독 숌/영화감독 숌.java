import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int count = 0;
		int result = 666;	// 666이 포함된 N번째 숫자
		
		while (true) {
			// 해당 숫자(result)를 문자열로 변경 후 666이 포함되어있으면
			if (String.valueOf(result).contains("666")) {
				count++;	// count 증가
			}
			
			// count가 N이랑 같다면 (해당 숫자(result)가 666이 포함된 N번째 숫자라면)
			if (count == N) {
				break;
			}
			
			result++;	// result 증가
		}
		
		System.out.println(result);
	}
}