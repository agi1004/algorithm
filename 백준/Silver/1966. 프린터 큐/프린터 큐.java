import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    Queue<Integer> importantQ = new LinkedList<>();
    Queue<Integer> orderQ = new LinkedList<>();
    int test = fr.nextInt();

    for (int i = 0; i < test; i++) {
      int n = fr.nextInt();
      int m = fr.nextInt();
      int count = 0;
      boolean flag = true;
      
      for (int j = 0; j < n; j++) {
        int important = fr.nextInt();
        importantQ.offer(important);
        orderQ.offer(j); 
      }
      
      while (flag) {
        Iterator iter = importantQ.iterator();
        
        while (iter.hasNext()) {
          if (importantQ.peek() < (Integer)iter.next()) {
            importantQ.offer(importantQ.poll());
            orderQ.offer(orderQ.poll());
            iter = importantQ.iterator();
          }
        }

        count++;

        importantQ.poll();
        if (orderQ.poll() == m) {
          System.out.println(count);
          flag = false;
          break;
        }
      }

      importantQ.clear();
      orderQ.clear();
    }
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