import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    int x = fr.nextInt();
    int[] ft = { 1, 1 };
    boolean flag1 = false, flag2 = false;
    
    for (int i = 1; i <= x; i++) {
      if (i == 1) continue;
      if (ft[0] - 1 == 0 && !flag1) {
        flag1 = true;
        flag2 = false;
        ft[1]++;
        continue;
      }
      else if (ft[1] - 1 == 0 && !flag2) {
        flag2 = true;
        flag1 = false;
        ft[0]++;
        continue;
      }
      if (flag1) {
        ft[0]++;
        ft[1]--;
      }
      else if (flag2) {
        ft[0]--;
        ft[1]++;
      }
    }
    System.out.println(ft[0] + "/" + ft[1]);
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