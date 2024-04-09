import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		char[] charArr = bf.readLine().toCharArray();
		int[] intArr = new int[charArr.length];
		
		// 문자 배열을 숫자로 바꿔서 숫자 배열에 대입
		for (int i = 0; i < charArr.length; i++) {
			// 아스키코드 => 문자 0: 아스키코드 48 ~ 문자 9: 아스키코드 57
			intArr[i] = (int)charArr[i] - 48;
		}
		
		// 선택 정렬 (배열 크기만큼 과정 반복)
		for (int i = 0; i < intArr.length; i++) {
			int maxIndex = i;
			// 최댓값 찾기
			for (int j = i + 1; j < intArr.length; j++) {
				if (intArr[j] > intArr[maxIndex]) {
					maxIndex = j;
				}
			}
			// 최댓값보다 작은 값이 앞에 있으면 swap
			if (intArr[i] < intArr[maxIndex]) {
				int temp = intArr[i];
				intArr[i] = intArr[maxIndex];
				intArr[maxIndex] = temp;
			}
		}
		
		// 정렬된 배열 출력
		for (int i = 0; i < intArr.length; i++) {
			System.out.print(intArr[i]);
		}
		
		bf.close();
	}
}