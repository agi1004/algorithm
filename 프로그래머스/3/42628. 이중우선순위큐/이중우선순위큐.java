import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        
        for (String operation : operations) {
            String[] line = operation.split(" ");
            String op = line[0];
            int n = Integer.parseInt(line[1]);
            
            if (op.equals("I")) {
                pq.add(n);
            } else if (!pq.isEmpty()) {
                if (n == 1) {
                    pq.poll();
                } else {
                    Queue<Integer> queue = new LinkedList<>();
                    
                    while (pq.size() > 1) {
                        queue.add(pq.poll());
                    }
                    
                    pq.poll();
                    
                    while (!queue.isEmpty()) {
                        pq.add(queue.poll());
                    }
                }
            }
        }
        
        if (pq.isEmpty()) {
            answer = new int[] {0, 0};
        } else {
            answer[0] = pq.poll();
            
            if (pq.isEmpty()) {
                answer[1] = answer[0];
            } else {
                while (pq.size() > 1) {
                    pq.poll();
                }
                answer[1] = pq.poll();
            }
        }
        
        return answer;
    }
}