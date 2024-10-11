class Solution {
    int[] answer = {-1};
    int[] lion = new int[11];
    int maxDiff = 0;
    
    public int[] solution(int n, int[] info) {
        dfs(0, n, info);
        return answer;
    }
    
    public void dfs(int depth, int n, int[] info) {
        if (depth == n) {
            int apeachScore = 0, lionScore = 0;
            
            for (int i = 0; i <= 10; i++) {
                if (info[i] == 0 && lion[i] == 0) {
                    continue;
                }
                
                if (lion[i] > info[i]) {
                    lionScore += 10 - i;
                } else {
                    apeachScore += 10 - i;
                }
            }
            
            if (lionScore > apeachScore) {
                int diff = lionScore - apeachScore;
                
                if (diff > maxDiff) {
                    answer = lion.clone();
                    maxDiff = diff;
                } else if (diff == maxDiff) {
                    for (int i = 10; i >= 0; i--) {
                        if (lion[i] > answer[i]) {
                            answer = lion.clone();
                            break;
                        }
                    }
                }
            }
            
            return; 
        }
        
        for (int i = 0; i <= 10 && lion[i] <= info[i]; i++) {
            lion[i]++;
            dfs(depth + 1, n, info);
            lion[i]--;
        }
    }
}