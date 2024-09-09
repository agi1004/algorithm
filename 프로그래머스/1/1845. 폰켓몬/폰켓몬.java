import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int N = nums.length;
        Set<Integer> pocketmons = new HashSet<>();
        
        for (int num : nums) {
            pocketmons.add(num);
        }
        
        if (pocketmons.size() <= N / 2) {
            answer = pocketmons.size();
        } else {
            answer = N / 2;
        }
        
        return answer;
    }
}