import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    int[] nums = new int[10];
    int[] rmds = new int[10];
    int count = 1;
    
    for (int i = 0; i < 10; i++) {
      int n = fr.nextInt();
      nums[i] = n;
    }
    
    for (int i = 0; i < 10; i++)
      rmds[i] = nums[i] % 42;
    
    Arrays.sort(rmds);

    for (int i = 1; i < 10; i++)
      if (rmds[i] != rmds[i - 1])
        count++;

    System.out.println(count);
  }
    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() { br = new BufferedReader(new InputStreamReader(System.in)); }
        public FastReader(String s) throws FileNotFoundException { br = new BufferedReader(new FileReader(new File(s))); }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try { str = br.readLine(); }
            catch (IOException e) { e.printStackTrace(); }
            return str;
        }
    }
}