import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		// 사전 역순으로 출력하기 위해 Collections.reverseOrder() 사용
		TreeSet<String> log = new TreeSet <>(Collections.reverseOrder());
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String inout = st.nextToken();
			if (inout.equals("enter")) {
				log.add(name);
			} else {
				log.remove(name);
			}
		}
		
		for (String name : log) {
			bw.write(name + "\n");
		}
		
		bw.flush();
	}
}