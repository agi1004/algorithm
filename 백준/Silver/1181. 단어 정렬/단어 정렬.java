import java.util.*;
import java.io.*;

class Word implements Comparable<Word> {
  public String str;
  public int length;
  public Word(String str, int length) {
    this.str = str;
    this.length = length;
  }
  @Override
  public int compareTo(Word other) {
    if (length != other.length)
      return length - other.length;
    else {
      for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) < other.str.charAt(i))
          return -1;
        else if (str.charAt(i) > other.str.charAt(i))
          return 1;
      }
      return 0;
    }
  }
  @Override
  public String toString() {
    return str;
  }
}

public class Main {
  public static void main(String[] args) {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    int n = fr.nextInt();
    Word[] word = new Word[n];
    for (int i = 0; i < n; i++) {
      String s = fr.next();
      word[i] = new Word(s, s.length());
    }
    Arrays.sort(word);
    for (int i = 0; i < n; i++) {
      if (i >= 1)
        if (word[i].str.equals(word[i - 1].str))
          continue;
      System.out.println(word[i].str);
    }
  }
    public static class FastReader {
        BufferedReader br;
        BufferedWriter bw;
        StringTokenizer st;
        public FastReader() { 
          br = new BufferedReader(new InputStreamReader(System.in));
          bw = new BufferedWriter(new OutputStreamWriter(System.out));
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