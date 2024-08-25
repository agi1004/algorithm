import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Work> queue = new LinkedList<>();
        List<Integer> counts = new ArrayList<>();
        
        for (int i = 0; i < progresses.length; i++) {
            queue.add(new Work(progresses[i], speeds[i]));
        }
        
         while (!queue.isEmpty()) {
             for (Work work : queue) {
                 if (work.progress < 100) {
                     work.progress += work.speed;
                 }
             }
            
             int count = 0;
             
             while (!queue.isEmpty()) {
                 if (queue.peek().progress < 100) {
                     break;
                 } else {
                     queue.poll();
                     count++;
                 }
             }
        
             if (count != 0) {
                 counts.add(count);
             }
        }
        
        int[] answer = new int[counts.size()]; 

        for (int i = 0; i < counts.size(); i++) {
            answer[i] = counts.get(i);
        }

        return answer;
    }
}

class Work {
    int progress;
    int speed;
    
    Work(int progress, int speed) {
        this.progress = progress;
        this.speed = speed;
    }
}