import java.util.*;

class Solution {
    int answer = 0;
    boolean[] visited;
    
    public int solution(int[] numbers, int target) {
        visited = new boolean[numbers.length];
        
        dfs(numbers, target, 0, 0);
        
        return answer;
    }
    
    public void dfs(int[] numbers, int target, int depth, int now) {
        if (depth == numbers.length) {
            if (target == now) {
                answer++;
            }
            return;
        }
    
        dfs(numbers, target, depth + 1, now + numbers[depth]);
        dfs(numbers, target, depth + 1, now - numbers[depth]);
    }
}