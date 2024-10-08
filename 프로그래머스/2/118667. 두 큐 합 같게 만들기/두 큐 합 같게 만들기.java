import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0, sum2 = 0;
        
        for (int num : queue1) {
            q1.add(num);
            sum1 += num;
        }
        
        for (int num : queue2) {
            q2.add(num);
            sum2 += num;
        }
        
        if (sum1 == sum2) {
            return 0;
        }
        
        while (answer < queue1.length * 4) {
            long case1 = Integer.MAX_VALUE;
            long case2 = Integer.MAX_VALUE;
            int num = -1;
            
            if (!q2.isEmpty()) {
                case1 = Math.abs((sum1 + q2.peek()) - (sum2 - q2.peek()));
            } 
            
            if (!q1.isEmpty()) {
                case2 = Math.abs((sum2 + q1.peek()) - (sum1 - q1.peek()));
            }

            if (case1 < case2) {
                num = q2.poll();
                q1.add(num);
                sum1 += num;
                sum2 -= num;
            } else {
                num = q1.poll();
                q2.add(num);
                sum2 += num;
                sum1 -= num;
            }

            answer++;

            if (case1 == 0 || case2 == 0) {
                return answer;
            }
        }
        
        return -1;
    }
}