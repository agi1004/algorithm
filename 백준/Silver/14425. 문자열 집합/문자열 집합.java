import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    FastReader fr = new FastReader();  // 문제 해결 소스 코드
    int n = fr.nextInt();
    int m = fr.nextInt();
    int count = 0;
    
    String[] s = new String[n];
    for (int i = 0; i < n; i++) {
      String ns = fr.next();
      s[i] = ns;
    }

    String[] mStr = new String[m];
    for (int i = 0; i < m; i++) {
      String ms = fr.next();
      mStr[i] = ms;
    }

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        if (s[i].equals(mStr[j]))
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