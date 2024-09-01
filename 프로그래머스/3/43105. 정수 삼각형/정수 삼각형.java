class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int N = triangle.length;
        int[][] dp = new int[N][];
        
        for (int i = 0; i < N; i++) {
            dp[i] = new int[triangle[i].length];
        }
        
        dp[0][0] = triangle[0][0];
        
        for (int i = 1; i < N; i++) {
            int M = triangle[i].length;
            
            for (int j = 0; j < M; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else if (j == M - 1) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                }
                
                if (i == N - 1) {
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }
        
        return answer;
    }
}