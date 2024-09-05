import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int N, M;
    
    public int solution(String[] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length();
        
        int[] start = findPoint(maps, 'S');
        int startToLever = bfs(maps, start, 'L');
        
        if (startToLever == -1) {
            return -1;
        }
        
        int[] lever = findPoint(maps, 'L');
        int leverToEnd = bfs(maps, lever, 'E');
        
        if (leverToEnd == -1) {
            return -1;
        }
        
        answer = startToLever + leverToEnd;
        
        return answer;
    }
    
    public int bfs(String[] maps, int[] point, char target) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int[][] dist = new int[N][M];
        
        queue.add(point);
        visited[point[0]][point[1]] = true;
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            
            if (maps[x].charAt(y) == target) {
                return dist[x][y];
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                
                if (visited[nx][ny] || maps[nx].charAt(ny) == 'X') {
                    continue;
                }
                
                queue.add(new int[] {nx, ny});
                visited[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;
            }
        }
        
        return -1;
    }
    
    public int[] findPoint(String[] maps, char target) {
        int[] point = new int[2];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i].charAt(j) == target) {
                    point = new int[] {i, j};
                    return point;
                }
            }
        }
        
        return point;
    }
}