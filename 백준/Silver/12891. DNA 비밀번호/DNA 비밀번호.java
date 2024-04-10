import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] checkArr;
	static int[] myArr;
	static int checkSecret;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int S = Integer.parseInt(st.nextToken());	// 문자열 길이
		int P = Integer.parseInt(st.nextToken());	// 부분문자열 길이
		char[] A = new char[S];		// DNA 문자열
		checkArr = new int[4];	// 부분문자열에 포함되어야 할 A,C,G,T 최소 개수
		myArr = new int[4];	// 내 DNA 문자열의 A,C,G,T 개수
		checkSecret = 0;	// A,C,G,T 중 하나라도 개수가 같을 시 1 증가
		int result = 0;	// 만들 수 있는 비밀번호 종류의 수 (checkCount의 수가 4일 때 1 증가)
		
		A = bf.readLine().toCharArray();
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < 4; i++) {
			checkArr[i] = Integer.parseInt(st.nextToken());
			if (checkArr[i] == 0)	// A,C,G,T 최소 개수가 0이면 무조건 만족하므로 1 증가
				checkSecret++;
		}
		
		for (int i = 0; i < P; i++) {	// 처음에는 부분문자열 길이까지만 반복
			Add(A[i]);	// 하나씩 검사하여 결과를 만족하면 myArr, checkCount, result 늘리기
		}
		if (checkSecret == 4)
			result++;
		
		// 슬라이딩 윈도우
		for (int i = P; i < S; i++) {
			// i: 맨 뒤 문자, j: 맨 앞 문자
			int j = i - P;
			Add(A[i]);		// 맨 뒤에 있는 문자를 하나씩 검사
			Remove(A[j]);	// 맨 앞 문자의 결과는 하나씩 제거 (중복되면 안 되기 때문)
			if (checkSecret == 4)
				result++;
		}
		
		System.out.println(result);
		bf.close();
	}

	private static void Add(char c) {
		switch (c) {
			case 'A':
				myArr[0]++;
				if (checkArr[0] == myArr[0])	// 값이 같을 때만 딱 한번 증가시켜야 함. 안 그러면 계속 증가됨
					checkSecret++;
				break;
			case 'C':
				myArr[1]++;
				if (checkArr[1] == myArr[1])
					checkSecret++;
				break;
			case 'G':
				myArr[2]++;
				if (checkArr[2] == myArr[2])
					checkSecret++;
				break;
			case 'T':
				myArr[3]++;
				if (checkArr[3] == myArr[3])
					checkSecret++;
				break;
		}
	}
	
	// 맨 앞 문자를 또 계산하면 안 되므로 중복값 제거
	private static void Remove(char c) {
		switch (c) {
			case 'A':
				if (checkArr[0] == myArr[0])
					checkSecret--;
				myArr[0]--;
				break;
			case 'C':	
				if (checkArr[1] == myArr[1])
					checkSecret--;
				myArr[1]--;
				break;
			case 'G':
				if (checkArr[2] == myArr[2])
					checkSecret--;
				myArr[2]--;
				break;
			case 'T':
				if (checkArr[3] == myArr[3])
					checkSecret--;
				myArr[3]--;
				break;
		}
	}
}