import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int n, m;
    boolean[][] visited;
    int[][] area;
    
    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        visited = new boolean[n][m];
        area = new int[n][m];
        int areaNum = 1;
        
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (!visited[x][y] && land[x][y] == 1) {
                    findOilLump(land, x, y, areaNum++);
                }
            }
        }
        
        for (int y = 0; y < m; y++) {
            int sum = 0, nowAreaNum = -1;
            Set<Integer> areaNums = new HashSet<>();
            
            for (int x = 0; x < n; x++) {
                if (land[x][y] != 0 && !areaNums.contains(area[x][y])) {
                    sum += land[x][y];
                    areaNums.add(area[x][y]);
                }
            }
            
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
    
    public void findOilLump(int[][] land, int startX, int startY, int areaNum) {
        Queue<Oil> queue = new LinkedList<>();
        List<Oil> oils = new ArrayList<>();
        int count = 1;
        Oil start = new Oil(startX, startY);
        
        queue.add(start);
        visited[startX][startY] = true;
        
        oils.add(start);
        land[startX][startY] = count++;
        area[startX][startY] = areaNum;
        
        while (!queue.isEmpty()) {
            Oil now = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                
                if (visited[nx][ny] || land[nx][ny] == 0) {
                    continue;
                }
                
                Oil next = new Oil(nx, ny);
                
                queue.add(next);
                visited[nx][ny] = true;
                
                oils.add(next);
                land[nx][ny] = count++;
                area[nx][ny] = areaNum;
                
                startX = nx;
                startY = ny;
            }
        }
        
        for (Oil oil : oils) {
            land[oil.x][oil.y] = land[startX][startY];
        }
    }
    
    static class Oil {
        int x, y;
        
        Oil(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
