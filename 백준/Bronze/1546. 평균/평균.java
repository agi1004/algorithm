import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader(); // 문제 해결 소스 코드
        int N = fr.nextInt();
        double[] score = new double[N];
        double sum = 0;
        for (int i = 0; i < N; i++)
            score[i] = fr.nextDouble();
        double max = 0;
        for (int i = 0; i < N; i++)
            if (score[i] > max)
                max = score[i];
        for (int i = 0; i < N; i++) 
            score[i] = score[i] / max * 100;
        for (int i = 0; i < N; i++)
            sum += score[i];
        System.out.println(sum / N);
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