class Solution {
    int[][] dp;
    
    int solution(int[][] land) {
        int N = land.length;
        dp = new int[N][4];
        
        init(land);
        
        for (int i = 1; i < N; i++) {
            dp[i][0] = calc(1, 2, 3, i, 0, land);
            dp[i][1] = calc(0, 2, 3, i, 1, land);
            dp[i][2] = calc(0, 1, 3, i, 2, land);
            dp[i][3] = calc(0, 1, 2, i, 3, land);
        }
        
        return getMax(N);
    }
    
    private void init(int[][] land) {
        for (int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }
    }
    
    private int calc(int a, int b, int c, int i, int j, int[][] land) {
        return Math.max(dp[i - 1][a], Math.max(dp[i - 1][b], dp[i - 1][c])) + land[i][j];
    }
    
    private int getMax(int N) {
        int max = 0;
        
        for (int i = 0; i < 4; i++) {
            max = Math.max(max, dp[N - 1][i]);
        }
        
        return max;
    }
}