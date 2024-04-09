import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    FastReader fr = new FastReader();  // 문제 해결 소스 코드
    ArrayList<Character> stack = new ArrayList<>();
    
    while (true) {
      String str = fr.nextLine();

      if (str.equals(".")) break;

      for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) == '(' || str.charAt(i) == ')'
           || str.charAt(i) == '[' || str.charAt(i) == ']')
          stack.add(str.charAt(i));
      }        

      int j = 0;
      while (!stack.isEmpty()) {
        if (j + 1 == stack.size()) break;
        if (stack.get(j) == '(' && stack.get(j + 1) == ')') {
          stack.remove(j);
          stack.remove(j);
          j = 0;
        }
        else if (stack.get(j) == '[' && stack.get(j + 1) == ']') {
          stack.remove(j);
          stack.remove(j);
          j = 0;
        }
        else j++;
      }

      if (stack.isEmpty())
        System.out.println("yes");
      else
        System.out.println("no");

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