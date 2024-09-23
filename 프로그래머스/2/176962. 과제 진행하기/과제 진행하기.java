import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        Queue<Homework> startQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.hour == o2.hour) {
                return o1.minute - o2.minute;
            }
            return o1.hour - o2.hour;
        });
        Stack<Homework> stopStack = new Stack<>();
        
        for (String[] plan : plans) {
            String name = plan[0];
            
            String[] start = plan[1].split(":");
            int hour = Integer.parseInt(start[0]);
            int minute = Integer.parseInt(start[1]);
            
            int playtime = Integer.parseInt(plan[2]);
            
            startQueue.add(new Homework(name, hour, minute, playtime));
        }
        
        Homework now = startQueue.poll();
        int nowHour = now.hour;
        int nowMinute = now.minute;
        int index = 0;
        
        while (!startQueue.isEmpty() || !stopStack.isEmpty() || now != null) {
            nowMinute++;

            if (now != null) {
                now.playtime--;
            
                if (now.playtime == 0) {
                    answer[index++] = now.name;
                    now = null;
                }
            }
            
            if (nowMinute == 60) {
                nowHour++;
                nowMinute = 0;
            }
            
            if (nowHour == 24) {
                nowHour = 0;
            }
            
            if (!startQueue.isEmpty()
                && startQueue.peek().hour == nowHour 
                && startQueue.peek().minute == nowMinute) {
                
                if (now != null) {
                    stopStack.push(now);
                }
                
                now = startQueue.poll();
                
            } else if (!stopStack.isEmpty() && now == null) {
                now = stopStack.pop();
            }
        }
        
        return answer;
    }
    
    static class Homework {
        String name;
        int hour, minute, playtime;
        
        Homework(String name, int hour, int minute, int playtime) {
            this.name = name;
            this.hour = hour;
            this.minute = minute;
            this.playtime = playtime;
        }
    }
}