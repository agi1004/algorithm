import java.util.*;
import java.io.*;

public class Main {

  public static boolean[] visited;
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

  public static void dfs(int x) {
    visited[x] = true;
    System.out.print(x + " ");
    for (int i = 0; i < graph.get(x).size(); i++) {
      int y = graph.get(x).get(i);
      if (!visited[y]) dfs(y);
    }
  }

  public static void bfs(int start) {
    Queue<Integer> q = new LinkedList<>();
    q.offer(start);
    visited[start] = true;
    while (!q.isEmpty()) {
      int x = q.poll();
      System.out.print(x + " ");
      for (int i = 0; i < graph.get(x).size(); i++) {
        int y = graph.get(x).get(i);
        if (!visited[y]) {
          q.offer(y);
          visited[y] = true;
        }
      }
    }
  }
  
  public static void main(String[] args) {
    FastReader fr = new FastReader(); // 문제 해결 소스 코드
    int n = fr.nextInt();
    int m = fr.nextInt();
    int v = fr.nextInt();
    visited = new boolean[n + 1];

    for (int i = 0; i < n + 1; i++)
      graph.add(new ArrayList<Integer>());

    for (int i = 0; i < m; i++) {
      int a = fr.nextInt();
      int b = fr.nextInt();
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    for (int i = 0; i < n + 1; i++)
      Collections.sort(graph.get(i));

    dfs(v);
    
    System.out.println();
    for (int i = 0; i < visited.length; i++)
      visited[i] = false;
    
    bfs(v);
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