import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Map<String, Integer> map = new TreeMap<>();
		int totalCount = 0;
		String s;
		
		while ((s = br.readLine()) != null) {		
			map.put(s, map.getOrDefault(s, 0) + 1);
			totalCount++;
		}
		
		for (String name : map.keySet()) {
			int count = map.get(name);
			double ratio = count * 100.0 / totalCount;
			bw.write(String.format("%s %.4f\n", name, ratio));
		}
		
		bw.flush();
	}
}