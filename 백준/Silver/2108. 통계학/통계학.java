import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    int n = fr.nextInt();
    int[] numbers = new int[n];
    for (int i = 0; i < n; i++) {
      int number = fr.nextInt();
      numbers[i] = number;
    }

    // 산술평균
    int sum = 0;
    for (int i = 0; i < n; i++)
      sum += numbers[i];
    System.out.println(Math.round((double)sum / n));

    // 중앙값
    Arrays.sort(numbers);
    System.out.println(numbers[n / 2]);

    // 최빈값
    int[] count = new int[8001];
    for (int i = 0; i < n; i++) {
      count[numbers[i] + 4000]++;
    }
    int mx = 0, mxIdx = 0;
    for (int i = 0; i <= 8000; i++) {
      if (count[i] > mx) {
        mx = count[i];
        mxIdx = i;
      }
    }
    int choibin = 0;
    boolean isChoi = false;
    for (int i = 0; i <= 8000; i++) {
      if (count[i] == mx && i > mxIdx) {
        isChoi = true;
        choibin = i;
        System.out.println(choibin - 4000);
        break;
      }
    }
    if (isChoi == false)
      System.out.println(mxIdx - 4000);

    // 범위
    int max = -4000, min = 4000;
    for (int i = 0; i < n; i++) {
      max = Math.max(numbers[i], max);
      min = Math.min(numbers[i], min);
    }
    System.out.println(max - min);
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