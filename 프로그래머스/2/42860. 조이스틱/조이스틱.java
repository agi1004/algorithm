class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        int move = length - 1;
        
        for (int i = 0; i < length; i++) {
            int up = name.charAt(i) - 'A';
            int down = 'Z' - name.charAt(i) + 1;
            answer += Math.min(up, down);
            
            int right = i + 1;
            while (right < length && name.charAt(right) == 'A') {
                right++;
            }
            
            move = Math.min(move, i * 2 + length - right);
            move = Math.min(move, (length - right) * 2 + i);
        }
        
        answer += move;
        
        return answer;
    }
}