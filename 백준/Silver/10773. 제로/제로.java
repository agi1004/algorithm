import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    FastReader fr = new FastReader();  // 문제 해결 소스 코드
    int k = fr.nextInt();
    ArrayList<Integer> jb = new ArrayList<>();
    int sum = 0;

    for (int i = 0; i < k; i++) {
      int n = fr.nextInt();
      if (i >= 1 && n == 0)
        jb.remove(jb.size() - 1);
      else
        jb.add(n);
    }

    for (int i = 0; i < jb.size(); i++)
      sum += jb.get(i);

    System.out.println(sum);
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