import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Acount = Integer.parseInt(st.nextToken());
		int Bcount = Integer.parseInt(st.nextToken());
		int[] A = new int[Acount];
		int[] B = new int[Bcount];
		HashMap<Integer, Boolean> AexceptB = new HashMap<>();
		HashMap<Integer, Boolean> BexceptA = new HashMap<>();
		int count = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Acount; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			AexceptB.put(A[i], false);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Bcount; i++) {
			B[i] = Integer.parseInt(st.nextToken());
			BexceptA.put(B[i], false);
		}
		
		for (int i = 0; i < Bcount; i++) {
			if (AexceptB.containsKey(B[i])) {
				AexceptB.put(B[i], true);
			}
		}
		
		for (int i = 0; i < Acount; i++) {
			if (BexceptA.containsKey(A[i])) {
				BexceptA.put(A[i], true);
			}
		}
		
		for (int i = 0; i < Acount; i++) {
			if (!AexceptB.get(A[i])) {
				count++;
			}
		}
		
		for (int i = 0; i < Bcount; i++) {
			if (!BexceptA.get(B[i])) {
				count++;
			}
		}
		
		System.out.println(count);
	}
}