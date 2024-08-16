import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int[][] map = new int[101][101];
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 문제에 주어진 (x, y)는 이 코드에서 (y, x)로 사용
        for (int[] rect : rectangle) {
            int x1 = rect[1] * 2;
            int y1 = rect[0] * 2;
            int x2 = rect[3] * 2;
            int y2 = rect[2] * 2;
            draw(x1, y1, x2, y2);
        }
        
        // 문제에 주어진 (x, y)는 이 코드에서 (y, x)로 사용
        return bfs(characterY * 2, characterX * 2, itemY * 2, itemX * 2);
    }
    
    public int bfs(int startX, int startY, int endX, int endY) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[101][101];
        
        queue.add(new Point(startX, startY, 0));
        visited[startX][startY] = true;
        
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            
            if (now.x == endX && now.y == endY) {
                return now.count / 2;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (nx < 0 || ny < 0 || nx > 100 || ny > 100) {
                    continue;
                }
                
                if (visited[nx][ny] || map[nx][ny] != 2) {
                    continue;
                }
                
                queue.add(new Point(nx, ny, now.count + 1));
                visited[nx][ny] = true;
            }
        }
        
        return 0;
    }
    
    public void draw(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (map[i][j] == 1) {
                    continue;
                }
                
                map[i][j] = 1;
                
                if (i == x1 || i == x2 || j == y1 || j == y2) {
                    map[i][j] = 2;
                }
            }
        }
    }
}

class Point {
    int x, y, count;
    
    Point(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}