import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int N = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        if (map.size() <= N / 2) {
            answer = map.size();
        } else {
            answer = N / 2;
        }
        
        return answer;
    }
}