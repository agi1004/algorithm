import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        String[] strings = new String[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            strings[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(strings, new Comparator<>() {
            public int compare(String s1, String s2) {
                int a = Integer.parseInt(s1 + s2);
                int b = Integer.parseInt(s2 + s1);
                return b - a;
            }
        });
        
        if (strings[0].equals("0")) {
            return "0";
        }
          
        for (String string : strings) {
            answer.append(string);
        }
        
        return answer.toString();
    }
}