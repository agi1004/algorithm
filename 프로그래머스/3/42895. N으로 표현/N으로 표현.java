import java.util.*;

class Solution {
    Set<Integer>[] dp = new HashSet[9];
    
    public int solution(int N, int number) {
        if (N == number) {
            return 1;
        }
        
        for (int i = 1; i <= 8; i++) {
            dp[i] = new HashSet<>();
        }
        
        dp[1].add(N);
        
        for (int i = 2; i <= 8; i++) {
            StringBuilder sb = new StringBuilder().append(N);
            
            for (int j = 1; j < i; j++) {
                sb.append(N);
            }
            
            dp[i].add(Integer.parseInt(sb.toString()));
            
            for (int j = 1; j < i; j++) {
                for (int num1 : dp[j]) {
                    for (int num2 : dp[i - j]) {
                        dp[i].add(num1 + num2);
                        dp[i].add(num1 - num2);
                        dp[i].add(num1 * num2);
                        if (num1 != 0 && num2 != 0) {
                            dp[i].add(num1 / num2);
                        }
                    }
                }
            }
            
            if (dp[i].contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
}