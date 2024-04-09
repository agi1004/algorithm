import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    int n = fr.nextInt();
    int[] nums = new int[n];
    int count = 0;
    boolean flag;
    
    for (int i = 0; i < n; i++) {
      int num = fr.nextInt();
      nums[i] = num;
    }

    for (int i = 0; i < n; i++) {
      flag = false;
      if (nums[i] == 1) continue;
      
      for (int j = 2; j <= nums[i]; j++) {
        if (nums[i] == j) continue;
        if (nums[i] % j == 0) {
          flag = true;
          break;
        }
      }
      
      if (flag) continue;
      else count++;
    }
    
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