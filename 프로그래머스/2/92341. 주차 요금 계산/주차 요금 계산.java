import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Queue<Car> pq = new PriorityQueue<>();
        
        for (String record : records) {
            String[] r = record.split(" ");
            
            String[] time = r[0].split(":");
            String idStr = r[1];
            String stateStr = r[2];
            
            String hStr = time[0];
            String mStr = time[1];
            
            int id = Integer.parseInt(idStr);
            int h = Integer.parseInt(hStr);
            int m = Integer.parseInt(mStr);
            boolean in = false;
            
            if (stateStr.equals("IN")) {
                in = true;
            }
            
            pq.add(new Car(id, h, m, in));
        }
        
        Map<Integer, Integer> map = new TreeMap<>();
        
        while (!pq.isEmpty()) {
            Car inCar = pq.poll();
            Car outCar = null;
            
            if (pq.isEmpty() || inCar.in == pq.peek().in) {
                outCar = new Car(inCar.id, 23, 59, !inCar.in);
            } else {
                outCar = pq.poll();
            }
            
            int nowH = inCar.h;
            int nowM = inCar.m;
            int time = 0;

            while (nowH < outCar.h || nowM < outCar.m) {
                nowM++;
                time++;
                
                if (nowM == 60) {
                    nowM = 0;
                    nowH++;
                }
                
            }
            
            map.put(inCar.id, map.getOrDefault(inCar.id, 0) + time);
        }
        
        int[] answer = new int[map.size()];
        int index = 0;
        
        for (int time : map.values()) {
            System.out.println(time);
            if (time <= fees[0]) {
                answer[index++] = fees[1];
            } else {
                int divide = (time - fees[0]) / fees[2];
                
                if ((time - fees[0]) % fees[2] != 0) {
                    divide++;
                } 
                
                answer[index++] = fees[1] + divide * fees[3];
            }
        }
        
        return answer;
    }
    
    static class Car implements Comparable<Car> {
        int id;
        int h, m;
        boolean in;
        
        Car(int id, int h, int m, boolean in) {
            this.id = id;
            this.h = h;
            this.m = m;
            this.in = in;
        }
        
        public int compareTo(Car car) {
            if (this.id == car.id) {
                if (this.h == car.h) {
                    return this.m - car.m;
                }
                return this.h - car.h;
            }
            return this.id - car.id;
        }
    }
}