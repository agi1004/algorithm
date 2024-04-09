import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 도감에 수록되어 있는 포켓몬의 개수
		int M = Integer.parseInt(st.nextToken()); 	// 맞혀야 하는 문제의 개수
		HashMap<String, Integer> name = new HashMap<>();	// key: 이름, value: 번호
		HashMap<Integer, String> number = new HashMap<>();	// key: 번호, value: 이름
		
		for (int i = 1; i <= N; i++) {
			String pocketmon = br.readLine();
			name.put(pocketmon, i);
			number.put(i, pocketmon);
		}
		
		for (int i = 0; i < M; i++) {
			String question = br.readLine();
			if (question.charAt(0) >= '1' && question.charAt(0) <= '9') {
				bw.write(number.get(Integer.parseInt(question)) + "\n");	
			} else {
				bw.write(name.get(question) + "\n");
			}
		}
		
		bw.flush();
	}
}