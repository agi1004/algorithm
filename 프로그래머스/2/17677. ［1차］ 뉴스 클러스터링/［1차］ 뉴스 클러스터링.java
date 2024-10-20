import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        List<String> list = new ArrayList<>();
        
        for (int i = 0; i < str1.length() - 1; i++) {
            String cutStr = cut(str1, i);
            
            if (isValid(cutStr)) {
                list.add(cutStr);
            }
        }
        
        int intersection = 0;
        int union = list.size();
        
        for (int i = 0; i < str2.length() - 1; i++) {
            String cutStr = cut(str2, i);
            
            if (isValid(cutStr)) {
                if (list.contains(cutStr)) {
                    list.remove(cutStr);
                    intersection++;
                } else {
                    union++;
                }
            }
        }
        
        double similarity;
        
        if (intersection == 0 && union == 0) {
            similarity = 1.0;
        } else {
            similarity = (double)intersection / union;
        }
        
        return (int)(similarity * 65536);
    }
    
    public String cut(String str, int index) {
        String cutStr;
            
        if (index == str.length() - 2) {
            cutStr = str.substring(index);
        } else {
            cutStr = str.substring(index, index + 2);
        }

        return cutStr.toUpperCase();
    }
    
    public boolean isValid(String str) {
        char ch1 = str.charAt(0);
        char ch2 = str.charAt(1);

        if ((ch1 >= 'A' && ch1 <= 'Z') && (ch2 >= 'A' && ch2 <= 'Z')) {
            return true;
        }
        
        return false;
    }
}