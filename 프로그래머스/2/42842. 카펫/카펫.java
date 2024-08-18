import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int area = brown + yellow;
        List<Rect> rects = new ArrayList<>();
        
        for (int i = 3; i <= Math.sqrt(area); i++) {
            if (area % i == 0) {
                int w = area / i;
                int h = i;
                rects.add(new Rect(w, h));
            }
        }
        
        for (Rect rect : rects) {
            int brownCount = 0;
            
            for (int i = 1; i <= rect.h; i++) {
                if (i == 1 || i == rect.h) {
                    brownCount += rect.w;
                } else {
                    brownCount += 2;
                }
            }
            
            if (brownCount == brown) {
                answer[0] = rect.w;
                answer[1] = rect.h;
                break;
            }
        }
        
        return answer;
    }
}

class Rect {
    int w, h;
    
    Rect(int w, int h) {
        this.w = w;
        this.h = h;
    }
}