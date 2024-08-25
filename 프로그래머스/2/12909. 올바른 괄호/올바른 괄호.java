import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            queue.add(c);
        }
        
        while (!queue.isEmpty()) {
            char now = queue.poll();
            
            if (!stack.isEmpty()) {
                char before = stack.peek();
                
                if ((String.valueOf(before) + String.valueOf(now)).equals("()")) {
                    stack.pop();
                } else {
                    stack.push(now);
                }
            } else {
                stack.push(now);
            }
        }
        
        if (!stack.isEmpty()) {
            answer = false;
        }

        return answer;
    }
}