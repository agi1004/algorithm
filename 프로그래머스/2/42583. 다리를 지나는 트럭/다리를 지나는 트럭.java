import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> ready = new LinkedList<>();
        List<Truck> crossing = new ArrayList<>();
         
        for (int truck_weight : truck_weights) {
            ready.add(truck_weight);
        }
        
        int crossingWeight = 0;
        int crossedCount = 0;
        
        while (crossedCount < truck_weights.length) {
            answer++;
            int crossedIdx = -1;
            
            for (int i = 0; i < crossing.size(); i++) {
                Truck truck = crossing.get(i);
                truck.time++;
                
                if (truck.time == bridge_length) {
                    crossingWeight -= truck.weight;
                    crossedCount++;
                    crossedIdx = i;
                }
            }
            
            if (crossedIdx != -1) {
                crossing.remove(crossedIdx);
            }
            
            if (!ready.isEmpty()
                && crossing.size() < bridge_length 
                && crossingWeight + ready.peek() <= weight) {
                int nowWeight = ready.poll();
                crossing.add(new Truck(nowWeight, 0));
                crossingWeight += nowWeight;
            }
        }
        
        return answer;
    }
}

class Truck {
    int weight;
    int time;
    
    Truck(int weight, int time) {
        this.weight = weight;
        this.time = time;
    }
}