import java.util.*;

class Solution {
    List<Integer>[] graph;
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        graph = new ArrayList[n];
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1 && i != j) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++;
                bfs(i);
            }
        }
        
        return answer;
    }
    
    public void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            for (int next : graph[now]) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
    }
}