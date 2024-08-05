import java.util.*;

class Solution {   
    public int solution(int[][] targets) {
        int answer = 0;
        
        // 끝 점을 기준으로 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        
        int x = 0;  // 요격 미사일의 위치
        
        for (int i = 0; i < targets.length; i++) {
            // 현재 요격 미사일이 요격할 수 없는 새로운 폭격 미사일이 나타날 때
            if (x <= targets[i][0]) {
                x = targets[i][1];  // 새로운 요격 미사일 발사 위치 설정
                answer++;           // 요격 미사일 수 증가
            }
        }
        
        return answer;
    }
}