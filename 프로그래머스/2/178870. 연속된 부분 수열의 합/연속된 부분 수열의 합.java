class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {1001, 1001};
        int start = 0, end = 0;
        int sum = sequence[start];
        
        while (start <= end) {
            if (sum < k) {
                if (end + 1 >= sequence.length) {
                    break;
                } else {
                    sum += sequence[++end];
                }
            } else {
                if (sum == k) {
                    if (end - start == answer[1] - answer[0]) {
                        answer = new int[] {Math.min(answer[0], start), Math.min(answer[1], end)};
                    } else {
                        answer = new int[] {start, end};
                    }
                }
                sum -= sequence[start++];
            }
        }
        
        return answer;
    }
}