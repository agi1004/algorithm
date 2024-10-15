import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        String[] nums = s.replace("{", "").replace("}", "").split(",");
        Map<Integer, Integer> map = new TreeMap<>();
        
        for (String num : nums) {
            int digit = Integer.parseInt(num);
            map.put(digit, map.getOrDefault(digit, 0) + 1);
        } 
        
        List<Integer> keys = new ArrayList<>(map.keySet());
        
        keys.sort((o1, o2) -> map.get(o2) - map.get(o1));
        
        answer = new int[map.size()];
        int index = 0;
        
        for (int digit : keys) {
            answer[index++] = digit;
        }
        
        return answer;
    }
}