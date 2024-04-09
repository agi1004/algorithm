import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader(); // 문제 해결 소스 코드
        int n = fr.nextInt();
        String[] words = new String[n];
        int[] alphabet = new int[26];
        int count = n;
        for (int i = 0; i < n; i++) {
            String word = fr.next();
            words[i] = word;
        }
        for (int i = 0; i < 26; i++)
            alphabet[i] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (j >= 1) {
                    if (words[i].charAt(j) != words[i].charAt(j - 1)
                        && alphabet[(int)words[i].charAt(j) - 97] != 0) {
                        count--;
                        break;
                    }
                }
                alphabet[(int)words[i].charAt(j) - 97]++;
            }
            for (int j = 0; j < 26; j++)
                alphabet[j] = 0;
        }
        System.out.println(count);
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