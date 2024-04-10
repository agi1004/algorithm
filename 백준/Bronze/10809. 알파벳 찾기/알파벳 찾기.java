import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader(); // 문제 해결 소스 코드
        String S = fr.next();
        int[] index = new int[26];
        for (int i = 0; i < 26; i++)
            index[i] = -1;
        for (int i = 0; i < S.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (S.charAt(i) == c) {
                    if (index[(int)c - 97] == -1)
                        index[(int)c - 97] = i;
                }   
            }
        }
        for (int i = 0; i < 26; i++)
            System.out.print(index[i] + " ");
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