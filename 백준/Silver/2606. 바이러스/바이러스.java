import java.util.*;
import java.io.*;

public class Main {

  public static FastReader fr = new FastReader(); // 문제 해결 소스 코드
  public static int n = fr.nextInt();
  public static boolean[] visited = new boolean[n + 1];
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
  public static int count = 0;

  // DFS 함수 정의
  public static void dfs(int x) {
    // 현재 노드를 방문 처리
    visited[x] = true;
    // 현재 노드와 연결된 다른 노드를 재귀적으로 방문
    for (int i = 0; i < graph.get(x).size(); i++) {
      int y = graph.get(x).get(i);
      if (!visited[y]) {
        count++;
        dfs(y);
      }
    }
  }
  
  public static void main(String[] args) {
    int pair = fr.nextInt();
    for (int i = 0; i <= n; i++)
      graph.add(new ArrayList<Integer>());
    
    for (int i = 0; i < pair; i++) {
      int a = fr.nextInt();
      int b = fr.nextInt();
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    dfs(1);
    System.out.println(count);
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