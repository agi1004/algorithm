class Solution {
    int answer = 0;
    boolean[] visited;
        
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];  
        dfs(k, dungeons, 0, 0);
        return answer;
    }
    
    public void dfs(int k, int[][] dungeons, int depth, int count) {
        if (depth == dungeons.length) {
            answer = Math.max(answer, count);
            return;
        }
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                
                if (k >= dungeons[i][0]) {
                    dfs(k - dungeons[i][1], dungeons, depth + 1, count + 1);
                } else {
                    dfs(k, dungeons, depth + 1, count);
                }
                
                visited[i] = false;
            }
        }
    }
}