import java.util.*;
import java.io.*;

class Number {
    public int n;
    public Number(int n) { this.n = n; }
    public int factorial(int n) {
        if (n == 0 || n == 1) 
            return 1;
        else
            return n * factorial(n - 1);
    }    
}
public class Main {

    public static void main(String[] args) {
        FastReader fr = new FastReader(); // 문제 해결 소스 코드
        int N = fr.nextInt();
        Number num = new Number(N);
        System.out.println(num.factorial(N));
    }
    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() { 
	        br = new BufferedReader(new InputStreamReader(System.in));
        }
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