import java.util.*;

class Solution {
    public String solution(String p) {
        if (isCorrect(p)) {
            return p;
        }
        return dfs(p);
    }
    
    public String dfs(String w) {
        if (w.equals("")) {
            return w;
        }
        
        StringBuilder u = new StringBuilder(), v = new StringBuilder();
        int index = getDivideIndex(w);
        
        if (index == w.length() - 1) {
            u.append(w);
        } else {
            // u는 w의 앞에서부터 균형잡힌 괄호 문자열까지 자른 문자열
            u.append(w.substring(0, index + 1));
            // v는 u 뒤의 나머지 문자열
            v.append(w.substring(index + 1));
        }
        
        if (isCorrect(u.toString())) {
            return u.append(dfs(v.toString())).toString();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(dfs(v.toString())).append(")");
            u.deleteCharAt(0).deleteCharAt(u.length() - 1);
            reverse(sb, u.toString());
            return sb.toString();
        }
    }
    
    public boolean isCorrect(String str) {
        if (str.equals("")) {
            return true;
        }
        
        Stack<Character> stack = new Stack<>();
        int count = 0;
        
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    count++;
                }
            }
        }
        
        if (!stack.isEmpty() || count > 0) {
            return false;
        }
        
        return true;
    }
    
    private int getDivideIndex(String str) {
        int open = 0, close = 0;
        
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            
            if (open == close) {
                return i;
            }
        }
        
        return -1;
    }
    
    private void reverse(StringBuilder sb, String str) {
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                sb.append(")");
            } else {
                sb.append("(");
            }
        }
    }
}