import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader(); // 문제 해결 소스 코드
        int n = fr.nextInt();
        String[] p = new String[n];
        for (int i = 0; i < n; i++)
            p[i] = fr.next();
        for (int i = 0; i < n; i++) {
            int score = 0;
            int correct = 0;
            for (int j = 0; j < p[i].length(); j++) {
                if (p[i].charAt(j) == 'X')
                    correct = 0;
                else if (p[i].charAt(j) == 'O') {
                    correct++;
                    score += correct;
                }
            }
            System.out.println(score);
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