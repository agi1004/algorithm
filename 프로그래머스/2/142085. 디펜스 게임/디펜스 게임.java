import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        if (k == enemy.length) {
            return k;
        }
        
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int e : enemy) {
            if (k > 0) {
                pq.add(e);
                k--;
            } else {
                int now = pq.poll();
            
                if (now < e) {
                    pq.add(e);
                    n -= now;
                } else {
                    pq.add(now);
                    n -= e;
                }

                if (n < 0) {
                    break;
                }
            }
            
            answer++;
        }
        
        return answer;
    }
}