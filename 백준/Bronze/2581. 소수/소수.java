import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    int m = fr.nextInt();
    int n = fr.nextInt();
    boolean flag = false;
    int sum = 0, min = 0, count = 0;

    for (int i = m; i <= n; i++) {
      flag = false;
      if (i == 1) continue;
      
      for (int j = 2; j <= i; j++) {
        if (i == j) continue;
        if (i % j == 0) {
          flag = true;
          break;
        }
      }
      
      if (flag) continue;
      else {
        sum += i;
        count++;
        if (count == 1) min = i;
      }
    }

    if (count == 0)
      System.out.println(-1);
    else {
      System.out.println(sum);
      System.out.println(min);
    }
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