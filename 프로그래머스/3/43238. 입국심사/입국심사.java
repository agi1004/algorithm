import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        long start = 0;
        long end = times[times.length - 1] * (long)n;
        
        while (start <= end) {
            long mid = (start + end) / 2;
            long count = 0;
            
            for (int time : times) {
                count += mid / time;
            }
            
            if (count < n) {
                start = mid + 1;
            } else {
                answer = mid;
                end = mid - 1;
            }
        }
        
        return answer;
    }
}