import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        Map<Character, Integer> map = new HashMap<>();
        
        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                if (!map.containsKey(key.charAt(i)) || i + 1 < map.get(key.charAt(i))) {
                    map.put(key.charAt(i), i + 1);
                }
            }
        }
        
        for (int i = 0; i < targets.length; i++) {
            int sum = 0;
            for (int j = 0; j < targets[i].length(); j++) {
                if (!map.containsKey(targets[i].charAt(j))) {
                    sum = -1;
                    break;
                } else {
                    sum += map.get(targets[i].charAt(j));
                }
            }
            answer[i] = sum;
        }
        
        return answer;
    }
}