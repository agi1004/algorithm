import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    FastReader fr = new FastReader();  // 문제 해결 소스 코드
    ArrayList<Integer> stack = new ArrayList<>();
    int n = fr.nextInt();

    for (int i = 0; i < n; i++) {
      String order = fr.nextLine();
      
      if (order.substring(0, 2).equals("pu")) {
        stack.add(Integer.parseInt(order.substring(5)));
      }
        
      else if (order.equals("pop")) {
        if (stack.isEmpty())
          System.out.println(-1);
        else {
          System.out.println(stack.get(stack.size() - 1));
          stack.remove(stack.size() - 1);
        }
      }
        
      else if (order.equals("size")) {
        System.out.println(stack.size());
      }
        
      else if (order.equals("empty")) {
        if (stack.isEmpty()) 
          System.out.println(1);
        else 
          System.out.println(0);
      }
        
      else if (order.equals("top")) {
        if (stack.isEmpty())
          System.out.println(-1);
        else
          System.out.println(stack.get(stack.size() - 1));
      }
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