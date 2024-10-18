import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        List<InOut> inouts = new ArrayList<>();
        
        for (String r : record) {
            String[] arr = r.split(" ");
            
            String state = arr[0];
            String uid = arr[1];
            
            if (state.equals("Enter") || state.equals("Change")) {
                String name = arr[2];
                map.put(uid, name);
            }
            
            if (state.equals("Enter")) {
                inouts.add(new InOut(true, uid));
            } else if (state.equals("Leave")) {
                inouts.add(new InOut(false, uid));
            }
        }
        
        String[] answer = new String[inouts.size()];
        
        for (int i = 0; i < answer.length; i++) {
            InOut inout = inouts.get(i);
            
            if (inout.in) {
                answer[i] = map.get(inout.uid) + "님이 들어왔습니다.";
            } else {
                answer[i] = map.get(inout.uid) + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
    
    static class InOut {
        boolean in;
        String uid;
        
        InOut(boolean in, String uid) {
            this.in = in;
            this.uid = uid;
        }
    }
}