import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        Queue<Integer> pq = new PriorityQueue<>();
        
        for (int s : scoville) {
            pq.add(s);
        }
        
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            
            if (first >= K) {
                return answer;
            }
            
            int mix = first + (second * 2);
            pq.add(mix);
            answer++;
        }
        
        if (pq.poll() >= K) {
            return answer;
        } else {
            return -1;
        }
    }
}