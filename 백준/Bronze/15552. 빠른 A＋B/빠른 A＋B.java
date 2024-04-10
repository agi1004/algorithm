import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    int t = fr.nextInt();
    for (int i = 0; i < t; i++) {
      int a = fr.nextInt();
      int b = fr.nextInt();
      fr.bw.write(a + b + "\n");
    }
    fr.bw.flush();
  }
    public static class FastReader {
        BufferedReader br;
        BufferedWriter bw;
        StringTokenizer st;
        public FastReader() { 
          br = new BufferedReader(new InputStreamReader(System.in)); 
          bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }
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