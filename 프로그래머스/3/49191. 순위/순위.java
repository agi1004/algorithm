import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] dist = new int[n + 1][n + 1];
        
        // 초기화: 무한대 값으로 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], 1000000000);
            dist[i][i] = 0;     // 자신에게 가는 경로는 0으로 초기화
        }
        
        // 주어진 경기 결과를 그래프에 기록
        for (int[] result : results) {
            int A = result[0];
            int B = result[1];
            dist[A][B] = 1; // A가 B를 이겼다는 의미
        }
        
        // 플로이드-워셜 알고리즘 수행
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        // 각 선수에 대해 다른 선수들과의 승패 관계가 모두 알려진 경우를 찾음
        for (int i = 1; i <= n; i++) {
            int count = 0;
            
            for (int j = 1; j <= n; j++) {
                if (i != j && (dist[i][j] < 1000000000 || dist[j][i] < 1000000000)) {
                    count++;
                }
            }
            
            if (count == n - 1) { // 자기 자신을 제외한 모든 선수와 관계가 있으면
                answer++;
            }
        }
        
        return answer;
    }
}