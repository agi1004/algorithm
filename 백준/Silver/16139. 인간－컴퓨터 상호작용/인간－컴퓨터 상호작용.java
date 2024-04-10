import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String S = br.readLine();	// 특정 문자열
		int[][] D = new int[26][S.length() + 1];	// 인덱스 0: 'a' ~ 인덱스 25: 'z'
		
		// 배열 초기화
		for (int i = 0; i < S.length(); i++) {	// 문자열 길이만큼 0부터 반복
			char ch = S.charAt(i);	// 문자의 아스키 코드 => ch가 'a'라면, ch - 97 = 0
			D[ch - 97][i + 1] = 1;	// 문자열의 i번째에 해당하는 문자의 개수를 1로 만들기
		}
		
		// 누적합 배열 저장
		for (int i = 0; i < 26; i++) {	// 알파벳 개수(a ~ z)만큼 반복
			for (int j = 1; j <= S.length(); j++) {	// 문자열 길이만큼 1부터 반복
				int temp = D[i][j];
				temp += D[i][j - 1];
				D[i][j] = temp;
			}
		}
		
		int q = Integer.parseInt(br.readLine());	// 질문의 수
		
		// 구간별 소문자 개수 구하기 (구간합)
		for (int i = 0; i < q; i++) {	// 질문의 수만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			char a = st.nextToken().charAt(0);			// 특정 알파벳
			int l = Integer.parseInt(st.nextToken());	// 문자열의 l번째 인덱스(앞)
			int r = Integer.parseInt(st.nextToken());	// 문자열의 r번째 인덱스(뒤)
			// l번째 문자부터 r번째 문자 사이에 특정 알파벳이 나타나는 횟수 => 구간합 사용
			bw.write(D[a - 97][r + 1] - D[a - 97][l] + "\n");
		}
		
		bw.flush();
	}
}