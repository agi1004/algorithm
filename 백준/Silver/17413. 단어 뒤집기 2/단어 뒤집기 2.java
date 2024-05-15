import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();		// 초기 문자열
		ArrayList<String> words = new ArrayList<>();	// 단어 리스트
		
		int start = -1, end = -1;	// <부터 start(시작 인덱스), >까지 end(끝 인덱스)
		String reverse = "";		// 뒤집을 단어
		
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == '<') {		
				start = i;		// 시작 인덱스 추가
				// < 앞에 있던 단어 뒤집고 리스트에 추가
				words.add(reverseWord(reverse));	
				reverse = "";	// 뒤집을 단어 초기화
			} else if (S.charAt(i) == '>') {	
				end = i;		// 끝 인덱스 추가
				// <, >를 포함한 문자열 자르기
				String temp = S.substring(start, end + 1);	
				words.add(temp);	// 자른 문자열을 리스트에 추가
				start = -1;			// 시작 인덱스 초기화
				end = -1;			// 끝 인덱스 초기화
			} else if (start == -1 && end == -1) {	// <, > 사이에 있는 단어가 아닐 때
				if (S.charAt(i) == ' ') {		// 공백일 때
					// 공백 앞에 있던 단어 뒤집고 공백 추가후 리스트에 추가
					words.add(reverseWord(reverse) + " ");	
					reverse = "";	// 뒤집을 단어 초기화
				} else {	// 공백이 아닐 때
					reverse += S.charAt(i);	// 뒤집을 단어에 문자 추가
				}
			}
		}
		
		if (reverse != "") {	// for 문이 끝났는데도 뒤집을 단어가 남아있다면
			words.add(reverseWord(reverse));	// 단어 뒤집고 리스트에 추가
		}
		
		for (String word : words) {		// 단어 리스트에 있는 단어들 일렬로 출력
			System.out.print(word);
		}
	}
	
	// 단어를 뒤집을 메소드
	public static String reverseWord(String word) {
		String reverseWord = "";
		for (int i = word.length() - 1; i >= 0; i--) {
			reverseWord += word.charAt(i);
		}
		return reverseWord;
	}
}