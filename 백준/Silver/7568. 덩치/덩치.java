import java.util.*;
import java.io.*;

class Person {
  public int kg, cm;
  public Person(int kg, int cm) {
    this.kg = kg;
    this.cm = cm;
  }
}
public class Main {
  public static void main(String[] args) {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    int n = fr.nextInt();
    Person[] person = new Person[n];
    int[] rank = new int[n];

    for (int i = 0; i < n; i++)
      rank[i] = 1;
    
    for (int i = 0; i < n; i++) {
      int x = fr.nextInt();
      int y = fr.nextInt();
      person[i] = new Person(x, y);
    }
    
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        if (person[i].kg < person[j].kg && person[i].cm < person[j].cm)
          rank[i]++;

    for (int i = 0; i < n; i++)
      System.out.print(rank[i] + " ");
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