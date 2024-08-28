import java.util.*;

class Solution {
    int answer = 0;
    char[] vowels = {'A', 'E', 'I', 'O', 'U'};
    boolean[][] visited;
    boolean found = false;
    
    public int solution(String word) {
        visited = new boolean[5][5];
        dfs(word, "", 0);
        return answer;
    }
    
    public void dfs(String target, String now, int depth) {
        if (now.equals(target)) {
            found = true;
            return;
        }
        
        if (depth == 5) return;
        
        for (int i = 0; i < 5; i++) {
            if (found) break;
            if (!visited[depth][i]) {
                visited[depth][i] = true;
                answer++;
                dfs(target, now + vowels[i], depth + 1);
                visited[depth][i] = false;
            }
        }
    }
}