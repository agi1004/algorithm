import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        
        Integer[] temp = getBoxedArray(B);
        Arrays.sort(temp, Collections.reverseOrder());
        
        for (int i = 0; i < A.length; i++) {
            answer += A[i] * temp[i];
        }
        
        return answer;
    }
    
    private Integer[] getBoxedArray(int[] B) {
        Integer[] arr = new Integer[B.length];
        
        for (int i = 0; i < B.length; i++) {
            arr[i] = B[i];
        }
        
        return arr;
    }
}