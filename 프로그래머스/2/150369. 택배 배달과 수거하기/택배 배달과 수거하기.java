class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;    // 모든 배달과 수거를 마치고 돌아올 수 있는 최소 이동 거리
        int delivery = 0;   // 남은 배달 수
        int pickup = 0;     // 남은 수거 수
        
        // 물류창고 -> 집 : 배달
        // 집 -> 물류창고 : 수거

        // 맨 끝 집부터 배달, 수거
        for (int i = n - 1; i >= 0; i--) {
            delivery -= deliveries[i];  // 남은 배달 수에 i번째 집의 배달 수 빼기
            pickup -= pickups[i];       // 남은 수거 수에 i번째 집의 수거 수 빼기
            
            // 남은 배달 수가 음수거나 남은 수거 수가 음수라면
            // i번째 집에는 배달 가능한 택배 용량이 초과되었다는 뜻이므로
            // i번째 집을 한번 더 방문해야 함
            while (delivery < 0 || pickup < 0) {
                // 트럭에 실을 수 있는 상자의 최대 개수는 cap이므로
                delivery += cap;    // 남은 배달 수에 cap을 더하기
                pickup += cap;      // 남은 수거 수에 cap을 더하기
                // i + 1을 하는 이유: 인덱스는 0부터 시작하므로 거리를 구하려면 +1을 해야 함
                // 왕복해야 하므로 두 배 값을 answer에 더하기
                answer += (i + 1) * 2;
            }
        }
        
        return answer;  // 모든 배달과 수거를 마치고 돌아올 수 있는 최소 이동 거리 리턴
    }
}