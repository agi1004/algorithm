import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 단어의 개수
		String[] words = new String[N];				// 1차원 단어 배열
		Map<Character, Integer> weight = new HashMap<>();	// 가중치 맵
		
		// N개의 단어 입력받기
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();			// i번째 단어 입력받기
			int length = words[i].length();		// i번째 단어의 길이
			
			// i번째 단어를 구성하는 각 알파벳의 가중치 세팅
			for (int j = 0; j < length; j++) {
				char ch = words[i].charAt(j);	// i번째 단어의 j번째 알파벳
				// 해당 알파벳의 가중치 세팅 => 알파벳이 앞에 있고, 자주 나올수록 가중치가 높아짐
				// 만약 length가 4라면 다음과 같음
				// j = 0: (int)Math.pow(10, length - 0 - 1) = 10^3 = 1000
				// j = 1: (int)Math.pow(10, length - 1 - 1) = 10^2 = 100
				// j = 2: (int)Math.pow(10, length - 2 - 1) = 10^1 = 10
				// j = 3: (int)Math.pow(10, length - 3 - 1) = 10^0 = 1
				// 인덱스가 앞에 있을수록 높은 자릿수이기 때문에 가중치가 높아짐
				// 그리고 앞에서 저장했던 가중치의 값에서 이 값을 더하므로 알파벳이 자주 나올수록 가중치가 높아짐
				weight.put(ch, weight.getOrDefault(ch, 0) + (int)Math.pow(10, length - j - 1));
			}
		}
		
		// 가중치 맵의 키(알파벳) 집합을 인자로 받는 알파벳 리스트 생성
		List<Character> alphabets = new ArrayList<>(weight.keySet());
		
		// 알파벳 리스트를 가중치가 큰 순서대로 내림차순 정렬
		alphabets.sort((o1, o2) ->
			weight.get(o2) - weight.get(o1)
		);
		
		
		Map<Character, Integer> alphabetToNum = new HashMap<>();	// 알파벳을 숫자로 매핑하는 맵
		int num = 9;	// 9부터 차례대로 줄일 예정
		
		// 가중치가 큰 순서대로 정렬된 알파벳 탐색
		for (char alphabet : alphabets) {
			// 가중치가 큰 알파벳일수록(자릿수가 높고, 자주 나올수록) 큰 숫자를 가져감
			alphabetToNum.put(alphabet, num--);
		}
		
		int sum = 0;	// 전체 단어들의 합의 최댓값 => 위에서 최댓값이 되게 세팅해줬으므로 더하기만 하면 됨
		
		// 단어 탐색
		for (String word : words) {
			int value = 0;	// 각 단어의 숫자
			
			// 각 단어의 알파벳 탐색
			for (char ch : word.toCharArray()) {
				value = value * 10 + alphabetToNum.get(ch);	// 단어를 숫자로 바꾸기
			}
			
			sum += value;	// 각 단어의 숫자를 전체 단어들의 합 변수에 누적시키기
		}
		
		System.out.println(sum);	// 전체 단어들의 합의 최댓값 출력
	}
}