import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<String> dict = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        StringBuilder w = new StringBuilder();
        
        for (int i = 0; i < 26; i++) {
            dict.add(String.valueOf((char)('A' + i)));
        }
        
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            
            if (!dict.contains(w.toString() + c)) {
                nums.add(dict.indexOf(w.toString()) + 1);
                dict.add(w.toString() + c);
                w = new StringBuilder(c);
            }
            
            w.append(c);
        }
        
        if (!w.toString().isEmpty()) {
            nums.add(dict.indexOf(w.toString()) + 1);
        }
        
        int[] answer = new int[nums.size()];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = nums.get(i);
        }
        
        return answer;
    }
}