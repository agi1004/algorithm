import java.util.*;
import java.io.*;

public class Main {  
  public static int count = 0;
  public static ArrayList<String> notHearSee = new ArrayList<>();
  
  public static void binarySearch(String[] arr, String key) {
    int start = 0;
    int end = arr.length - 1;

    while (start <= end) {
      int mid = (start + end) / 2;

      if (key.equals(arr[mid])) {
        count++;
        notHearSee.add(key);
        return;
      }
      else if (key.compareTo(arr[mid]) < 0)
        end = mid - 1;
      else if (key.compareTo(arr[mid]) > 0)
        start = mid + 1;
    }
  }
  
  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader();  // 문제 해결 소스 코드
    int notHearNum = fr.nextInt();
    int notSeeNum = fr.nextInt();

    String[] notHear = new String[notHearNum];
    for (int i = 0; i < notHearNum; i++) {
      String nh = fr.next();
      notHear[i] = nh;
    }

    String[] notSee = new String[notSeeNum];
    for (int i = 0; i < notSeeNum; i++) {
      String ns = fr.next();
      notSee[i] = ns;
    }

    Arrays.sort(notHear);

    for (int i = 0; i < notSeeNum; i++)
      binarySearch(notHear, notSee[i]);

    Collections.sort(notHearSee);
    
    System.out.println(count);
    for (int i = 0; i < count; i++)
      System.out.println(notHearSee.get(i));
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