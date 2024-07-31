import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, Integer> map = new TreeMap<>();
		int totalCount = 0;
		
		while (sc.hasNext()) {
			String name = sc.nextLine();
			int count = (map.containsKey(name)) ? map.get(name) + 1 : 1;
			map.put(name, count);
			totalCount++;
		}
		
		for (String name : map.keySet()) {
			double ratio = map.get(name) * 100.0 / totalCount;
			System.out.printf("%s %.4f\n", name, ratio);
		}
		
		sc.close();
	}
}