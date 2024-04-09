import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader(); // 문제 해결 소스 코드
        String word = fr.next();
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            count++;
            if (i == word.length() - 1) 
                break;
            if (word.charAt(i) == 'd') {
                if (word.charAt(i + 1) == 'z') {
                    if (i + 2 == word.length()) {
                        count++;
                        break;
                    }
                    if (word.charAt(i + 2) == '=')
                        i += 2;
                    continue;
                }
                else if (word.charAt(i + 1) == '-') {
                    i++;
                    continue;
                }
            }
            else if (word.charAt(i) == 'c') {
                if (word.charAt(i + 1) == '=' || word.charAt(i + 1) == '-') 
                    i++;
                continue;
            }
            else if (word.charAt(i) == 'l') {
                if (word.charAt(i + 1) == 'j') 
                    i++;
                continue;
            }
            else if (word.charAt(i) == 'n') {
                if (word.charAt(i + 1) == 'j') 
                    i++;
                continue;
            }
            else if (word.charAt(i) == 's') {
                if (word.charAt(i + 1) == '=') 
                    i++;
                continue;
            }
            else if (word.charAt(i) == 'z') {
                if (word.charAt(i + 1) == '=') 
                    i++;
                continue;
            }
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