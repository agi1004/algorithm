import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        Queue<File> pq = new PriorityQueue<>();
        
        for (int i = 0; i < files.length; i++) {
            String head = "";
            String number = "";
            String tail = "";
            int index = 0;
            
            DTO dto = getHead(files[i]);
            head = dto.part;
            index = dto.index;
            
            if (files[i].substring(index).matches("\\d+")) {
                number = files[i].substring(index);
                pq.add(new File(i, head, number, tail));
                continue;
            }
            
            dto = getNumber(files[i], index);
            number = dto.part;
            index = dto.index;
            
            tail = files[i].substring(index);
            pq.add(new File(i, head, number, tail));
        }
        
        int index = 0;
        
        while (!pq.isEmpty()) {
            File file = pq.poll();
            answer[index++] = file.head + file.number + file.tail;
        }
        
        return answer;
    }
    
    private DTO getHead(String file) {
        String head = null;
        int index = 0;
        
        for (int i = 0; i < file.length(); i++) {
            char ch = file.charAt(i);

            if (ch >= '0' && ch <= '9') {
                head = file.substring(0, i);
                index = i;
                break;
            }
        }
        
        return new DTO(head, index);
    }
    
    private DTO getNumber(String file, int index) {
        String number = null;
        
        for (int i = index; i < file.length(); i++) {
            char ch = file.charAt(i);

            if (ch < '0' || ch > '9') {
                number = file.substring(index, i);
                index = i;
                break;
            }
        }
        
        return new DTO(number, index);
    }
    
    static class File implements Comparable<File> {
        int id;
        String head, number, tail;
        
        File(int id, String head, String number, String tail) {
            this.id = id;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
        
        public int compareTo(File o) {
            String th = this.head.toUpperCase();
            String oh = o.head.toUpperCase();
            
            if (th.compareTo(oh) == 0) {
                int tn = Integer.parseInt(this.number);
                int on = Integer.parseInt(o.number);
                
                if (tn == on) {
                    return this.id - o.id;
                }
                
                return tn - on;
            }
            
            return th.compareTo(oh);
        }
    }
    
    static class DTO {
        String part;
        int index;
        
        DTO(String part, int index) {
            this.part = part;
            this.index = index;
        }
    }
}