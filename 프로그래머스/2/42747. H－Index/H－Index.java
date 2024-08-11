import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        // citations = {3, 0, 6, 1, 5}
        // h = 3
        // 3번 이상 인용된 논문 횟수: 3편 -> 3, 6, 5 (3편 이상O)
        // 3번 이하 인용된 논문 -> 0 <= 3, 1 <= 3 (3편 이하O)
        // -> answer = Math.max(answer, h) = Math.max(0, 3) = 3
        // h = 0
        // 0번 이상 인용된 논문 횟수: 5편 -> 3, 0, 6, 1, 5 (0편 이상O)
        // 0번 이하 인용된 논문 -> X (0편 이하O)
        // -> answer = Math.max(answer, h) = Math.max(3, 0) = 3
        // h = 6
        // 6번 이상 인용된 논문 횟수: 1편 -> 6 (6편 이상X)
        // h = 1
        // 1번 이상 인용된 논문 횟수: 4편 -> 3, 6, 1, 5 (1편 이상O)
        // 1번 이하 인용된 논문 -> 0 <= 1 (1편 이하O)
        // -> answer = Math.max(answer, h) = Math.max(3, 1) = 3
        // h = 5
        // 5번 이상 인용된 논문 횟수: 2편 -> 6, 5 (5편 이상X)
        
        for (int h = 0; h <= citations.length; h++) {
            int count = 0;
            
            for (int citation : citations) {
                if (citation >= h) {
                    count++;
                }
            }
            
            if (count >= h) {
                answer = Math.max(answer, h);
            }
        }
        
        return answer;
    }
}