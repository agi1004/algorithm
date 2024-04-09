import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader(); // 문제 해결 소스 코드
        int C = fr.nextInt();
        for (int i = 0; i < C; i++) {
            int N = fr.nextInt();
            int[] score = new int[N];
            int sum = 0;
            for (int j = 0; j < N; j++) 
                score[j] = fr.nextInt();
            for (int j = 0; j < N; j++)
                sum += score[j];
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (score[j] > (double)sum / N)
                    count++;
            }
            System.out.printf("%.3f%%\n", (double)count / N * 100);
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