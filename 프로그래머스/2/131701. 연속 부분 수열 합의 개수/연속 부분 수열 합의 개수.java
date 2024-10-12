import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        
        for (int n = 1; n <= elements.length; n++) {
            for (int i = 0; i < elements.length; i++) {
                int sum = 0, count = 0, index = i;
                
                while (count < n) {
                    if (index == elements.length) {
                        index = 0;
                    }
                    
                    sum += elements[index++];
                    count++;
                }

                set.add(sum);
            }
        }
        
        return set.size();
    }
}