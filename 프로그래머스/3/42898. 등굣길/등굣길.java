class Solution {
    public int solution(int m, int n, int[][] puddles) {
        // 각 지역마다 도달할 수 있는 경로의 수를 저장하는 dp 배열
        // dp[i][j]: (i, j) 위치에 도달할 수 있는 경로의 수
        int[][] dp = new int[n][m];
        
        // 물에 잠긴 지역을 -1로 표시
        // * 문제에는 x좌표, y좌표가 반대로 주어져있으므로 주의!
        for (int[] puddle : puddles) {
            int x = puddle[1] - 1;
            int y = puddle[0] - 1;
            dp[x][y] = -1;
        }
        
        dp[0][0] = 1;   // 집 좌표(시작 위치)에 도달할 수 있는 경로 개수는 1로 초기화
        
        // 모든 지역을 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == -1) {   // 해당 위치가 물에 잠긴 지역이라면
                    // 물에 잠긴 지역은 갈 수가 없으므로 경로 개수를 0으로 설정 후 건너뛰기
                    dp[i][j] = 0;   
                    continue;
                }
                
                if (i > 0) {    // 위쪽에서 오는 경우
                    // 위쪽 좌표에 도달할 수 있는 경로 개수를 현재 좌표에 누적
                    dp[i][j] += dp[i - 1][j];
                }
                
                if (j > 0) {    // 왼쪽에서 오는 경우
                    // 왼쪽 좌표에 도달할 수 있는 경로 개수를 현재 좌표에 누적
                    dp[i][j] += dp[i][j - 1];
                }
                
                dp[i][j] %= 1_000_000_007;  // 문제 조건에 따라 결과를 MOD 연산
            }
        }
        
        return dp[n - 1][m - 1];    // 학교 좌표(도착 위치)에 도달할 수 있는 경로의 수 리턴
    }
}