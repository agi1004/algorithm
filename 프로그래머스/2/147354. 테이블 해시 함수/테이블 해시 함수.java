import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) {
                return o2[0] - o1[0];
            }
            return o1[col - 1] - o2[col - 1];
        });
        
        int[] S = new int[row_end - row_begin + 1];
        int index = 0;
        
        for (int i = row_begin - 1; i <= row_end - 1; i++) {
            for (int j = 0; j < data[i].length; j++) {
                S[index] += data[i][j] % (i + 1);
            }
            index++;
        }
        
        for (int i = 0; i < S.length; i++) {
            answer ^= S[i];
        }
        
        return answer;
    }
}