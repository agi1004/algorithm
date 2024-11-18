import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        Queue<String> queue1 = getQueue(cards1);
        Queue<String> queue2 = getQueue(cards2);
        Queue<String> goalQueue = getQueue(goal);
        
        while (!goalQueue.isEmpty()) {
           boolean canMake = false;
            
            while (!queue1.isEmpty() && queue1.peek().equals(goalQueue.peek())) {
                queue1.poll();
                goalQueue.poll();
                canMake = true;
            }
            
            while (!queue2.isEmpty() && queue2.peek().equals(goalQueue.peek())) {
                queue2.poll();
                goalQueue.poll();
                canMake = true;
            }
            
            if (!canMake) {
                answer = "No";
                break;
            }
        }
        
        return answer;
    }
    
    private Queue<String> getQueue(String[] words) {
        Queue<String> queue = new LinkedList<>();
        
        for (String word : words) {
            queue.add(word);
        }
        
        return queue;
    }
}