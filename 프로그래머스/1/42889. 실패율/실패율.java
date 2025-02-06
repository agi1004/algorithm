import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] counts = new int[N + 1];
        int sum = stages.length;
        PriorityQueue<Stage> pq = new PriorityQueue<>();
        
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
    
    static class Stage implements Comparable<Stage> {
        int num;
        double rate;
        
        Stage(int num, double rate) {
            this.num = num;
            this.rate = rate;
        }
        
        public int compareTo(Stage o) {
            if (Double.compare(o.rate, this.rate) == 0) {
                return Integer.compare(this.num, o.num);
            }
            return Double.compare(o.rate, this.rate);
        }
    }
}