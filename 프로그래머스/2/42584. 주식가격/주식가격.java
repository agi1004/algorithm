import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < prices.length; i++) {
            queue.add(i);
        }
        
        while (!queue.isEmpty()) {
            int index = queue.poll();
            
            for (int i = index + 1; i < prices.length; i++) {
                answer[index]++;
                
                if (prices[i] < prices[index]) {
                    break;
                }
            }
        }
        
        return answer;
    }
}