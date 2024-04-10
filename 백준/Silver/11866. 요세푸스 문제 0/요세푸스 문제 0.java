import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    
    Queue<Integer> queue = new LinkedList<>();
    ArrayList<Integer> josephus = new ArrayList<>();
    int n = fr.nextInt();
    int k = fr.nextInt();
    int count = 1;
    
    for (int i = 1; i <= n; i++)
      queue.offer(i);
    
    while (!queue.isEmpty()) {
      if (count == k) {
        josephus.add(queue.poll());
        count = 1;
      }
      else {
        queue.offer(queue.poll());
        count++;
      }
    }

    System.out.print("<");
    for (int i = 0; i < josephus.size() - 1; i++)
      System.out.print(josephus.get(i) + ", ");
    System.out.print(josephus.get(josephus.size() - 1));
    System.out.println(">");
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