import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 영어 지문에 나오는 단어의 개수
		int M = Integer.parseInt(st.nextToken());	// 외울 단어의 길이 기준
		// 단어장 트리맵 => 단어 중복 저장을 방지하고, 알파벳 사전 순으로 저장시키기 위한 용도
		TreeMap<String, Integer> dict = new TreeMap<>();	
		
		// 단어장 초기화
		for (int i = 0; i < N; i++) {
			String word = br.readLine();	// 입력받은 단어
			// 단어의 길이가 외울 단어의 길이보다 짧다면 건너뛰기
			if (word.length() < M) {	
				continue;				
			}
			// 단어장에 해당 단어가 이미 존재한다면
			if (dict.containsKey(word)) {	
				dict.put(word, dict.get(word) + 1);	// 단어 빈도 수 1 증가
			} else {	// 단어장에 해당 단어가 없다면
				dict.put(word,  1);		// 단어 빈도 수를 1로 설정하고 새로 추가
			}
		}
		
		// 단어장에 존재하는 단어들
		// 우선순위 3: 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치 => 이미 만족한 상태
		ArrayList<String> words = new ArrayList<>(dict.keySet());
		
		// 단어 순서를 우선순위별로 정렬 
		words.sort(new Comparator<String>() {
			public int compare(String o1, String o2) {
				Integer freq1 = dict.get(o1);	// 앞 단어의 빈도 수
				Integer freq2 = dict.get(o2);	// 뒷 단어의 빈도 수
				// 두 단어의 빈도 수가 다르다면
				if (freq1.compareTo(freq2) != 0) {	
					// 우선순위 1: 자주 나오는 단어일수록 앞에 배치
					return freq2.compareTo(freq1);	
				} else {	// 두 단어의 빈도 수가 같다면
					// 우선순위 2: 해당 단어의 길이가 길수록 앞에 배치
					return o2.length() - o1.length();
				}
			}
		});
		
		// 정렬된 단어들을 차례대로 버퍼에 작성
		for (String word : words) {
			bw.write(word + "\n");
		}
		
		bw.flush();	// 버퍼에 작성되어있는 상태를 그대로 한꺼번에 출력
	}
}