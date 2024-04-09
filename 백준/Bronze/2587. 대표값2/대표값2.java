import java.util.*;
import java.io.*;

public class Main {
     public static void main(String[] args) {
         FastReader fr = new FastReader(); // 문제 해결 소스 코드
         int[] num = new int[5];
         int sum = 0;
         for (int i = 0; i < 5; i++) {
             int n = fr.nextInt();
             num[i] = n;
         }
         for (int i = 0; i < 5; i++)
             sum += num[i];
         System.out.println(sum / 5);
         Arrays.sort(num);
         System.out.println(num[2]);
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