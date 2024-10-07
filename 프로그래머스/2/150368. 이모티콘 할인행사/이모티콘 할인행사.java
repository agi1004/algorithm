import java.util.*;

class Solution {
    int[] ratios = {10, 20, 30, 40};
    int[] ratioIndex;
    Queue<EmoPlus> pq = new PriorityQueue<>();
    
    public int[] solution(int[][] users, int[] emoticons) {
        ratioIndex = new int[emoticons.length];
        
        dfs(0, users, emoticons);
        
        EmoPlus emoPlus = pq.poll();
        
        int[] answer = {emoPlus.joinCount, emoPlus.sales};
        
        return answer;
    }
    
    public void dfs(int depth, int[][] users, int[] emoticons) {
        if (depth == emoticons.length) {
            calc(users, emoticons);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            ratioIndex[depth] = i;
            dfs(depth + 1, users, emoticons);
        }
    }
    
    public void calc(int[][] users, int[] emoticons) {
        int joinCount = 0, sales = 0;
        
        for (int i = 0; i < users.length; i++) {
            int userRatio = users[i][0];
            int userPrice = users[i][1];
            int pay = 0;
            
            for (int j = 0; j < emoticons.length; j++) {
                int emoRatio = ratios[ratioIndex[j]];
                int emoPrice = emoticons[j] * (100 - emoRatio) / 100;
                
                if (userRatio <= emoRatio) {
                    pay += emoPrice;
                }
                
                if (pay >= userPrice) {
                    pay = 0;
                    joinCount++;
                    break;
                }
            }

            sales += pay;
        }
        
        pq.add(new EmoPlus(joinCount, sales));
    }
    
    static class EmoPlus implements Comparable<EmoPlus> {
        int joinCount, sales;
        
        EmoPlus(int joinCount, int sales) {
            this.joinCount = joinCount;
            this.sales = sales;
        }
        
        public int compareTo(EmoPlus e) {
            if (e.joinCount == this.joinCount) {
                return e.sales - this.sales;
            }
            return e.joinCount - this.joinCount;
        }
    }
}