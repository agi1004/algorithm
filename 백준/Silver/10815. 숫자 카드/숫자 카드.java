import java.util.*;
import java.io.*;

public class Main {
  public static int binarySearch(int target, int start, int end, int[] arr) {   
    while (start <= end) {
      int mid = (start + end) / 2;
      
      if (target == arr[mid])
        return 1;
      else if (target < arr[mid])
        end = mid - 1;
      else
        start = mid + 1;
    }
    
    return 0;
  }
  public static void main(String[] args) {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    int n = fr.nextInt();
    int[] sg = new int[n];
    for (int i = 0; i < n; i++) {
      int s = fr.nextInt();
      sg[i] = s;
    }
    
    int m = fr.nextInt();
    int[] card = new int[m];
    for (int i = 0; i < m; i++) {
      int c = fr.nextInt();
      card[i] = c;
    }

    Arrays.sort(sg);
    
    for (int i = 0; i < m; i++)
      System.out.print(binarySearch(card[i], 0, n - 1, sg) + " "); 
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