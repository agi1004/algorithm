import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader(); // 문제 해결 소스 코드
        String word = fr.next();
        int time = 0;
        for (int i = 0; i < word.length(); i++) {
            switch (word.charAt(i)) {
                case 'A': case 'B': case 'C':
                    time += 3;
                    break;
                case 'D': case 'E': case 'F':
                    time += 4;
                    break;
                case 'G': case 'H': case 'I':
                    time += 5;
                    break;
                case 'J': case 'K': case 'L':
                    time += 6;
                    break;
                case 'M': case 'N': case 'O':
                    time += 7;
                    break;
                case 'P': case 'Q': case 'R': case 'S':
                    time += 8;
                    break;
                case 'T': case 'U': case 'V':
                    time += 9;
                    break;
                case 'W': case 'X': case 'Y': case 'Z':
                    time += 10;
                    break;    
            }
        }
        System.out.println(time);
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