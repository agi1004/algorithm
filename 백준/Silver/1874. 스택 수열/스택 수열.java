import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    FastReader fr = new FastReader();  // 문제 해결 소스 코드
    int n = fr.nextInt();
    int[] arr = new int[n];
    ArrayList<Integer> stack = new ArrayList<>();
    ArrayList<Character> pp = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      int num = fr.nextInt();
      arr[i] = num;
    }

    int index = 0;
    for (int i = 1; i <= n; i++) {
      stack.add(i);
      pp.add('+');
      while (arr[index] == stack.get(stack.size() - 1)) {
        stack.remove(stack.size() - 1);
        pp.add('-');
        index++;
        if (stack.isEmpty()) break;
      }
    }

    if (stack.isEmpty())
      for (int i = 0; i < pp.size(); i++)
        System.out.println(pp.get(i));
    else
      System.out.println("NO");
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