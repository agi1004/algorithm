import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];  // {최댓값, 최솟값} 배열
        // 내림차순 정렬한 우선순위 큐 => 맨 앞은 최댓값, 맨 뒤는 최솟값
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        
        for (String operation : operations) {
            String[] line = operation.split(" ");
            String op = line[0];    // 연산 코드
            int n = Integer.parseInt(line[1]);  // 숫자
            
            if (op.equals("I")) {       // 연산 코드가 I일 때
                pq.add(n);  // 큐에 주어진 숫자 삽입
            } else if (!pq.isEmpty()) { // 연산 코드가 D이고, 우선순위 큐가 비어있지 않을 때
                if (n == 1) {   // 숫자가 1일 때
                    pq.poll();  // 우선순위 큐에서 가장 먼저 뽑은 숫자(최댓값) 삭제
                } else {        // 숫자가 -1일 때
                    Queue<Integer> queue = new LinkedList<>();  // 임시 큐
                    
                    // 우선순위 큐에 남아있는 숫자가 1개가 될 때까지 
                    // 우선순위 큐에 있는 값들을 뽑아서 모두 임시 큐에 삽입
                    while (pq.size() > 1) {
                        queue.add(pq.poll());
                    }
                    
                    pq.poll();  // 우선순위 큐에서 가장 마지막에 뽑은 숫자(최솟값) 삭제
                    
                    // 임시 큐가 빌 때까지
                    while (!queue.isEmpty()) {
                        // 임시 큐에 있던 값들을 뽑아서 모두 우선순위 큐에 삽입
                        pq.add(queue.poll());
                    }
                }
            }
        }
        
        if (pq.size() == 1) {   // 우선순위 큐에 남아있는 숫자가 1개라면
            answer[0] = answer[1] = pq.poll();  // 남아있는 숫자 1개는 최댓값이자 최솟값
        } else if (pq.size() > 1) { // 우선순위 큐에 남아있는 숫자가 2개 이상이라면
            answer[0] = pq.poll();  // 우선순위 큐에서 가장 먼저 뽑은 숫자: 최댓값
            
            // 우선순위 큐에 남아있는 숫자가 1개가 될 때까지 우선순위 큐에 있는 값들을 뽑기
            while (pq.size() > 1) {
                pq.poll();
            }
            
            answer[1] = pq.poll();  // 우선순위 큐에서 가장 마지막에 뽑은 숫자: 최솟값
        }
        
        return answer;  // {최댓값, 최솟값} 배열 리턴
    }
}