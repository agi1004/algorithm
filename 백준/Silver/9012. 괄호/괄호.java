import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    FastReader fr = new FastReader();  // 문제 해결 소스 코드
    int t = fr.nextInt();
    ArrayList<Character> stack = new ArrayList<>();
    
    for (int i = 0; i < t; i++) {
      String ps = fr.next();
      
      for (int j = 0; j < ps.length(); j++)
        stack.add(ps.charAt(j));
      
      int k = 0;
      while (!stack.isEmpty()) {
        if (k + 1 == stack.size()) break;
        if (stack.get(k) == '(' && stack.get(k + 1) == ')') {
          stack.remove(k);
          stack.remove(k);
          k = 0;
        }
        else k++;
      }

      if (stack.isEmpty())
        System.out.println("YES");
      else
        System.out.println("NO");

      stack.clear();
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