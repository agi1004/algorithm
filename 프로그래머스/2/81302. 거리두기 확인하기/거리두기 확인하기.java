import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    char[][] map = new char[5][5];
    boolean[][] checked;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int index = 0;
        
        for (String[] place : places) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    map[i][j] = place[i].charAt(j);
                }
            }
            
            checked = new boolean[5][5];
            boolean flag = true;
            
            outer: for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (map[i][j] == 'P') {
                        checked[i][j] = true;

                        if (!checkDist(i, j)) {
                            flag = false;
                            break outer;
                        }
                    }
                }
            }
            
            if (flag) {
                answer[index++] = 1;
            } else {
                answer[index++] = 0;
            }
        }
        
        return answer;
    }
    
    public boolean checkDist(int startX, int startY) {
        for (int i = startX; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 'P' && !checked[i][j]) {
                    int dist = Math.abs(startX - i) + Math.abs(startY - j);
                    
                    // 거리가 2면 검사, 1이면 바로 해당
                    
                    if (dist == 1) {
                        return false;
                    }
                    
                    if (dist == 2) {
                        if (!hasPartition(startX, startY, i, j)) {
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }
    
    private boolean hasPartition(int x1, int y1, int x2, int y2) {
        // x좌표가 같다면 (x1, |y1 - y2| + Math.min(y1, y2) - 1)이 X인지 검사
        if (x1 == x2) {
            int adjY = Math.abs(y1 - y2) + Math.min(y1, y2) - 1;
            
            if (map[x1][adjY] == 'X') {
                return true;
            } else {
                return false;
            }
        }
        
        // y좌표가 같다면 (|x1 - x2| + Math.min(x1, x2) - 1, y1)이 X인지 검사
        if (y1 == y2) {
            int adjX = Math.abs(x1 - x2) + Math.min(x1, x2) - 1;
            
            if (map[adjX][y1] == 'X') {
                return true;
            } else {
                return false;
            }
        }
        
        // x, y좌표가 둘다 다르다면 (x1, y2), (x2, y1) 둘다 X인지 검사
        if (map[x1][y2] == 'X' && map[x2][y1] == 'X') {
            return true;
        } else {
            return false;
        }
    }
}