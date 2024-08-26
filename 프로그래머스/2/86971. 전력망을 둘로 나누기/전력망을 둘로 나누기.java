import java.util.*;

class Solution {
    List<Integer>[] tree;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        tree = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            tree[v1].add(v2);
            tree[v2].add(v1);
        }
        
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            
            tree[v1].remove((Integer)v2);
            tree[v2].remove((Integer)v1);
            
            int diff = Math.abs(bfs(v1, n) - bfs(v2, n));
            
            tree[v1].add(v2);
            tree[v2].add(v1);
            
            answer = Math.min(answer, diff);
        }
        
        return answer;
    }
    
    public int bfs(int start, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int count = 0;
        
        queue.add(start);
        visited[start] = true;
        count++;
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            for (int next : tree[now]) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
}