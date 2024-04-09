import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader(); // 문제 해결 소스 코드
        int[] num = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 
                              11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                              21, 22, 23, 24, 25, 26, 27, 28, 29, 30 };
        int n;
        for (int i = 0; i < 28; i++) {
            n = fr.nextInt();
            for (int j = 0; j < 30; j++)
                if (num[j] == n)
                    num[j] = 0;
        }
        int[] no = new int[2];
        int index = 0;
        for (int i = 0; i < 30; i++) {
            if (num[i] != 0) {
                no[index++] = num[i];
            }
        }
        if (no[0] > no[1]) {
            int temp = no[0];
            no[0] = no[1];
            no[1] = temp;
        }
        System.out.println(no[0]);
        System.out.println(no[1]);
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