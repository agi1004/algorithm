class Solution {
    public int solution(String s) {
        int answer = 0;
        char first = '\0';
        int same = 0, diff = 0;
        boolean cut = true;
        
        for (int i = 0; i < s.length(); i++) {
            if (cut) {
                first = s.charAt(i);
                cut = false;
            }
            
            if (first == s.charAt(i)) {
                same++;
            } else {
                diff++;
            }
            
            if (same == diff) {
                answer++;
                same = diff = 0;
                cut = true;
            }
        }
        
        if (same != diff) {
            answer++;
        }
        
        return answer;
    }
}