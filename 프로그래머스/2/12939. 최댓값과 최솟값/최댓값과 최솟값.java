class Solution {
    public String solution(String s) {
        String[] strs = s.split(" ");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (String str : strs) {
            int num = Integer.parseInt(str);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        return min + " " + max;
    }
}