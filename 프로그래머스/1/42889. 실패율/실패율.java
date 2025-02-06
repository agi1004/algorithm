import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] counts = new int[N + 1];
        int sum = stages.length;
        PriorityQueue<Stage> pq = new PriorityQueue<>((o1, o2) -> {
            if (Double.compare(o2.rate, o1.rate) == 0) {
                return Integer.compare(o1.num, o2.num);
            }
            return Double.compare(o2.rate, o1.rate);
        });
        
        for (int num : stages) {
            if (num > N) continue;
            counts[num]++;
        }

        for (int num = 1; num <= N; num++) {
            double rate = (double) counts[num] / sum;
            
            if (counts[num] == 0) {
                rate = 0;
            }
            
            pq.add(new Stage(num, rate));
            sum -= counts[num];
        }
        
        for (int i = 0; i < N; i++) {
            answer[i] = pq.poll().num;
        }
        
        return answer;
    }
    
    static class Stage {
        int num;
        double rate;
        
        Stage(int num, double rate) {
            this.num = num;
            this.rate = rate;
        }
    }
}