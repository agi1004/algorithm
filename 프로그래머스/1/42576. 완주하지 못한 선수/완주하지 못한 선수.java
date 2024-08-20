import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> pset = new HashMap<>();
        
        for (String p : participant) {
            pset.put(p, pset.getOrDefault(p, 0) + 1);
        }
        
        for (String c : completion) {
            if (pset.containsKey(c)) {
                pset.put(c, pset.get(c) - 1);
            }
        }
        
        for (String p : pset.keySet()) {
            if (pset.get(p) != 0) {
                answer = p;
                break;
            }
        }
        
        return answer;
    }
}