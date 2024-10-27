import java.util.*;

class Solution {
    public long solution(long n) {
        String str = String.valueOf(n);
        Integer[] nums = new Integer[str.length()];
        
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(str.charAt(i) + "");
        }
        
        Arrays.sort(nums, Collections.reverseOrder());
        
        StringBuilder sb = new StringBuilder();
        
        for (int num : nums) {
            sb.append(num);
        }
        
        return Long.parseLong(sb.toString());
    }
}