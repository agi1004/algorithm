class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isEven = true;
        
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append(' ');
                isEven = true;
                continue;
            }
            
            String temp = String.valueOf(c);
            
            if (isEven) {
                sb.append(temp.toUpperCase());
                isEven = false;
            } else {
                sb.append(temp.toLowerCase());
                isEven = true;
            }
        }
        
        return sb.toString();
    }
}