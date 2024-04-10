import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    Queue<Integer> queue = new LinkedList<>();
    int n = fr.nextInt();
    int last = 0;

    for (int i = 0; i < n; i++) {
      String order = fr.nextLine();

      if (order.substring(0, 2).equals("pu")) {
        last = Integer.parseInt(order.substring(5));
        queue.offer(last);
      }
        
      else if (order.equals("pop")) {
        if (queue.isEmpty())
          fr.bw.write(-1 + "\n");
        else {
          fr.bw.write(queue.poll() + "\n");
        }
      }
        
      else if (order.equals("size")) {
        fr.bw.write(queue.size() + "\n");
      }
        
      else if (order.equals("empty")) {
        if (queue.isEmpty())
          fr.bw.write(1 + "\n");
        else
          fr.bw.write(0 + "\n");
      }
        
      else if (order.equals("front")) {
        if (queue.isEmpty())
          fr.bw.write(-1 + "\n");
        else
          fr.bw.write(queue.peek() + "\n");
      }
        
      else if (order.equals("back")) {
        if (queue.isEmpty())
          fr.bw.write(-1 + "\n");
        else 
          fr.bw.write(last + "\n");
      }
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