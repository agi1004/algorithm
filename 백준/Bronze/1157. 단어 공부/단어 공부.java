import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader(); // 문제 해결 소스 코드
        String s = fr.next();
        s = s.toUpperCase();
        
        int[] alphabet = new int[26];
        for (int i = 0; i < 26; i++)
            alphabet[i] = 0;
        
        for (int i = 0; i < s.length(); i++)
            for (int j = 0; j < 26; j++)
                if ((int)s.charAt(i) == j + 65)
                    alphabet[j]++;
        
        int maxCount = 0;
        int maxIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] > maxCount) {
                maxCount = alphabet[i];
                maxIndex = i;
            }
        }
        
        int same = 0;
        for (int i = 0; i < 26; i++)
            if (alphabet[i] == maxCount)
                same++;
        
        if (same >= 2)
            System.out.println("?");
        else 
            System.out.println((char)(maxIndex + 65));
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