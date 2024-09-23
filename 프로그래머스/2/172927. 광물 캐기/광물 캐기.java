import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    int[][] fatigue = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    int pickCount;
    int[] pickIndexes;
    
    public int solution(int[] picks, String[] minerals) {
        pickCount = minerals.length / 5;
        
        if (minerals.length % 5 != 0) {
            pickCount++;
        }
        
        pickIndexes = new int[pickCount];
        
        Arrays.fill(pickIndexes, -1);
            
        dfs(picks, minerals, 0);
        
        return answer;
    }
    
    public void dfs(int[] picks, String[] minerals, int depth) {
        if (depth == pickCount || !existed(picks)) {
            int sum = 0, startIndex = 0;
            
            for (int pickIndex : pickIndexes) {
                if (pickIndex == -1) {
                    break;
                }
                
                int mineralCount = 0;
                
                for (int i = startIndex; i < minerals.length; i++) {
                    int mineralIndex = findMineralIndex(minerals[i]);
                    
                    sum += fatigue[pickIndex][mineralIndex];
                    
                    mineralCount++;
                    
                    if (mineralCount == 5) {
                        startIndex = i + 1;
                        break;
                    }
                }
            }
            
            answer = Math.min(answer, sum);
            return;
        }
        
        for (int i = 0; i < 3; i++) {
            if (picks[i] != 0) {
                picks[i]--;
                pickIndexes[depth] = i;
                dfs(picks, minerals, depth + 1);
                picks[i]++;
            }
        }
    }
    
    public boolean existed(int[] picks) { 
        for (int i = 0; i < 3; i++) {
            if (picks[i] != 0) {
                return true;
            }
        }
        return false;
    }
    
    public int findMineralIndex(String mineral) {
        switch (mineral) {
            case "diamond":
                return 0;
            case "iron":
                return 1;
            case "stone":
                return 2;
        }
        return -1;
    }
}