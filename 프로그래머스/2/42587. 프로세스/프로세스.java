import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Process> ready = new LinkedList<>();
        List<Process> running = new ArrayList<>();
        
        for (int i = 0; i < priorities.length; i++) {
            ready.add(new Process(i, priorities[i]));
        }
        
        while (!ready.isEmpty()) {
            Process now = ready.poll();
            boolean isMaxPriority = true;
            
            for (Process next : ready) {
                if (next.priority > now.priority) {
                    ready.add(now);
                    isMaxPriority = false;
                    break;
                }
            }
            
            if (isMaxPriority) {
                running.add(now);
            }
        }
        
        for (int i = 0; i < running.size(); i++) {
            Process process = running.get(i);
            if (process.location == location) {
                answer = i + 1;
                break;
            }
        }
        
        return answer;
    }
}

class Process {
    int location, priority;
    
    Process(int location, int priority) {
        this.location = location;
        this.priority = priority;
    }
}