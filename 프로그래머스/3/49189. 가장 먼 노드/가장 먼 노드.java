import java.util.*;

class Solution {
    List<Integer>[] graph;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        graph = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        
        int[] dist = bfs(1, n);
        int max = 0;
        
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dist[i]);
        }
        
        for (int i = 1; i <= n; i++) {
            if (dist[i] == max) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public int[] bfs(int start, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            for (int next : graph[now]) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    dist[next] = dist[now] + 1;
                }
            }
        }
        
        return dist;
    }
}