import java.util.*;
import java.io.*;

class Dot implements Comparable<Dot> {
    public int x, y;
    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Dot d) {
        if (this.x > d.x)
            return 1;
        else if (this.x < d.x)
            return -1;
        else if (this.x == d.x) {
            if (this.y > d.y)
                return 1;
            else if (this.y < d.y)
                return -1;
        }
        return 0;
    }
    @Override
    public String toString() {
        return x + " " + y;
    }
}
public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader(); // 문제 해결 소스 코드
        int N = fr.nextInt();
        ArrayList<Dot> dot = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int x = fr.nextInt();
            int y = fr.nextInt();
            dot.add(new Dot(x, y));
        }
        Collections.sort(dot);
        for (Dot d : dot)
            System.out.println(d);
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