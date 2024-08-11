import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        boolean existed = false;
        
        for (String word : words) {
            if (word.equals(target)) {
                existed = true;
                break;
            }
        }
        
        if (!existed) {
            return 0;
        }
        
        dfs(words, begin, target, 0);
        
        return answer;
    }
    
    public void dfs(String[] words, String now, String target, int depth) {
        if (now.equals(target)) {
            answer = Math.min(answer, depth);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i]) {
                int diff = 0;
                
                for (int j = 0; j < words[i].length(); j++) {
                    if (words[i].charAt(j) != now.charAt(j)) {
                        diff++;
                    }
                }
                
                if (diff == 1) {
                    visited[i] = true;
                    dfs(words, words[i], target, depth + 1);
                    visited[i] = false;
                }
            }
        }
    }
}