class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        boolean[] checked = new boolean[10];
        
        for (int number : numbers) {
            checked[number] = true;
        }
        
        for (int i = 0; i < 10; i++) {
            if (!checked[i]) {
                answer += i;
            }
        }
        
        return answer;
    }
}