import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String sik = sc.next();
		String[] sikArr = sik.split("-");
		String[] myArr;
		int result = 0;
		
		for (int i = 0; i < sikArr.length; i++) {
			myArr = sikArr[i].split("[+]");
			sikArr[i] = String.valueOf(mySum(myArr));
		}
		
		result += Integer.parseInt(sikArr[0]);
		for (int i = 1; i < sikArr.length; i++) {
			result -= Integer.parseInt(sikArr[i]);
		}
		
		System.out.println(result);
		sc.close();
	}
	
	public static int mySum(String[] myArr) {
		int mySum = 0;
		for (int i = 0; i < myArr.length; i++) {
			mySum += Integer.parseInt(myArr[i]);
		}
		return mySum;
	}
}