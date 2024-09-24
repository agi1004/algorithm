import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int N, M;
    boolean[][] visited;
    
    public int[] solution(String[] maps) {
        List<Integer> foods = new ArrayList<>();
        N = maps.length;
        M = maps[0].length();
        visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && maps[i].charAt(j) != 'X') {
                    foods.add(bfs(maps, i, j));
                }
            }
        }
        
        if (foods.isEmpty()) {
            foods.add(-1);
        }
        
        int[] answer = new int[foods.size()];
        
        for (int i = 0; i < foods.size(); i++) {
            answer[i] = foods.get(i);
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
    
    public int bfs(String[] maps, int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        int sum = 0;
        
        queue.add(new int[] {startX, startY});
        visited[startX][startY] = true;
        sum += maps[startX].charAt(startY) - '0';
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1];
            
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
                sum += maps[nx].charAt(ny) - '0';
            }
        }
        
        return sum;
    }
}