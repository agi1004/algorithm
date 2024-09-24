import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        Queue<Book> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.start[0] == o2.start[0]) {
                if (o1.start[1] == o2.start[1]) {
                    if (o1.end[0] == o2.end[0]) {
                        return o1.end[1] - o2.end[1];
                    }
                    return o1.end[0] - o1.end[0];
                }
                return o1.start[1] - o2.start[1];
            }
            return o1.start[0] - o2.start[0];
        });
        
        for (String[] bt : book_time) {           
            String[] s = bt[0].split(":");
            String[] e = bt[1].split(":");
            
            int[] start = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
            int[] end = {Integer.parseInt(e[0]), Integer.parseInt(e[1])};
            
            pq.add(new Book(start, end));
        }
        
        Map<Integer, Room> rooms = new HashMap<>();
        Book book = pq.poll();
        
        int nowHour = book.start[0];
        int nowMinute = book.start[1];
        int count = 1;
        
        rooms.put(count++, new Room(book.end, false, 0));
        
        while (!pq.isEmpty()) {    
            while (!pq.isEmpty() && pq.peek().start[0] == nowHour && pq.peek().start[1] == nowMinute) {
                book = pq.poll();
                boolean recycling = false;
                
                for (int id : rooms.keySet()) {
                    Room room = rooms.get(id);
                    
                    if (room.checkout && room.cleanTime == 10) {
                        rooms.put(id, new Room(book.end, false, 0));
                        recycling = true;
                        break;
                    }
                }
                
                if (!recycling) {
                    rooms.put(count++, new Room(book.end, false, 0));
                }
            }
            
            nowMinute++;
            
            if (nowMinute == 60) {
                nowHour++;
                nowMinute = 0;
            }
            
            for (Room room : rooms.values()) {
                if (room.checkout && room.cleanTime < 10) {
                    room.cleanTime++;
                }
                
                if (nowHour == room.end[0] && nowMinute == room.end[1]) {
                    room.checkout = true;
                }
            }
        }
        
        return rooms.size();
    }
    
    static class Book {
        int[] start, end;
        
        Book(int[] start, int[] end) {
            this.start = start;
            this.end = end;
        }
    }
    
    static class Room {
        int[] end;
        boolean checkout;
        int cleanTime;
        
        Room(int[] end, boolean checkout, int cleanTime) {
            this.end = end;
            this.checkout = checkout;
            this.cleanTime = cleanTime;
        }
    }
}