import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;     // 서로 다른 옷의 조합의 수
        Map<String, Integer> counts = new HashMap<>();  // K: 의상의 종류, V: 개수
        
        for (String[] c : clothes) {
            // 의상의 종류와 개수를 맵에 추가
            counts.put(c[1], counts.getOrDefault(c[1], 0) + 1);
        }
        
        // 서로 다른 옷의 조합의 수: 각 의상의 종류 개수 + 1을 모두 곱하기
        for (int count : counts.values()) {
            answer *= count + 1;
        }
        
        // 옷을 아무것도 고르지 않은 경우의 수 1개 제거 후 리턴
        return answer - 1;
    }
}