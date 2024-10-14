import java.util.*;

class Solution {
    long answer = 0;
    List<Long> nums = new ArrayList<>();
    List<Character> ops = new ArrayList<>();
    char[][] orders = {{'+', '-', '*'}, {'+', '*', '-'},
                       {'-', '+', '*'}, {'-', '*', '+'},
                       {'*', '+', '-'}, {'*', '-', '+'}};
    
    public long solution(String expression) {
        int index = 0;
        
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            
            if (ch == '+' || ch == '-' || ch == '*') {
                nums.add(Long.parseLong(expression.substring(index, i)));
                ops.add(ch);
                index = i + 1;
            }
        }
        
        nums.add(Long.parseLong(expression.substring(index)));
        
        for (char[] order : orders) {
            findMax(order);
        }
        
        return answer;
    }
    
    public void findMax(char[] order) {
        List<Long> tempNums = new ArrayList<>(nums);
        List<Character> tempOps = new ArrayList<>(ops);
        long result = 0;
        
        while (!tempOps.isEmpty()) {
            int index = -1;
            
            if (tempOps.contains(order[0])) {
                index = tempOps.indexOf(order[0]);
            } else if (tempOps.contains(order[1])) {
                index = tempOps.indexOf(order[1]);
            } else {
                index = tempOps.indexOf(order[2]);
            }
            
            char op = tempOps.remove(index);
            long num1 = tempNums.remove(index);
            long num2 = tempNums.remove(index);
            
            result = calc(op, num1, num2);
            tempNums.add(index, result);
        }
        
        answer = Math.max(answer, Math.abs(result));
    }
    
    private long calc(char op, long num1, long num2) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
        }
        return -1;
    }
}