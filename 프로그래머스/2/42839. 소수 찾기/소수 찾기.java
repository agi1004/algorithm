import java.util.*;

class Solution {
    Set<Integer> primes = new HashSet<>();
    boolean[] visited;
    
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        dfs(numbers.toCharArray(), 0, "");
        return primes.size();
    }
    
    public void dfs(char[] numArr, int depth, String now) {
        if (depth == numArr.length) {
            if (!now.equals("") && isPrime(now)) {
                primes.add(Integer.parseInt(now));
            }
            return;
        }

        for (int i = 0; i < numArr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                String next = now + numArr[i];
                
                if (isPrime(next)) {
                    primes.add(Integer.parseInt(next));
                } 
                
                dfs(numArr, depth + 1, next);
                visited[i] = false;
            }
        }
    }
    
    public boolean isPrime(String str) {
        int num = Integer.parseInt(str);
        
        if (num <= 1) {
            return false;
        }
        
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}