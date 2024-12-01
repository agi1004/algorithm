class Solution {
    boolean solution(String s) {
        int pCount = 0, yCount = 0;
        s = s.toLowerCase();
        
        for (char ch : s.toCharArray()) {
            if (ch == 'p') {
                pCount++;
            } else if (ch == 'y') {
                yCount++;
            }
        }
        
        if (pCount == yCount) {
            return true;
        }

        return false;
    }
}