class Solution {
    int MOD = 1234567;
    
    public int solution(int n) {
        int[] fibo = new int[100001];
        
        fibo[1] = fibo[2] = 1;
        
        for (int i = 3; i <= n; i++) {
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % MOD;
        }
        
        return fibo[n];
    }
}