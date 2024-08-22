import java.util.*;

class Solution {
    public int solution(int[][] jobs) {   
        // 작업이 요청되는 시점 기준으로 오름차순 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        // 작업의 소요시간 기준으로 오름차순 정렬
        // => 처리 대기중인 작업들을 소요시간이 낮은 순서대로 담은 우선순위 큐
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int count = 0;  // 처리한 작업의 개수
        int index = 0;  // 처리 대기 우선순위 큐에 들어가기를 기다리는 jobs 배열의 위치
        int end = 0;    // 작업 처리가 종료된 가장 최근 시간
        int sum = 0;    // 모든 작업들을 처리하는 데에 걸린 시간
        
        // 처리한 작업 개수(count)가 작업들의 총 개수(jobs 배열의 길이)보다 작을 때까지 반복
        while (count < jobs.length) {
            // 현재 대기 작업의 위치(index)가 작업들의 총 개수(jobs 배열의 총 길이)보다 작고,
            // 현재 대기 작업이 요청되는 시점(jobs[index][0])이 작업 처리 종료 시간(end) 이하라면
            while (index < jobs.length && jobs[index][0] <= end) {
                // 처리 대기 우선순위 큐(pq)에 현재 대기 작업(jobs[index])을 넣고,
                // 처리 대기 우선순위 큐에 들어가기를 기다리는 jobs 배열의 위치 1 증가(index++)
                pq.add(jobs[index++]);  
            }
            
            // 처리 대기 우선순위 큐(pq)가 비었다면 하드디스크가 아무 작업도 수행하고 있지 않는 중
            if (pq.isEmpty()) {  
                // 작업 처리 종료 시간(end)을 현재 대기 작업이 요청되는 시점(jobs[index][0])으로 세팅
                end = jobs[index][0];   
            } else {    // 처리 대기 우선순위 큐(pq)가 비어있지 않다면
                int[] now = pq.poll();  // 처리 대기 우선순위 큐에 있는 작업을 뽑아서(now) 처리하기
                end += now[1];  // 작업 처리 종료 시간(end)에 현재 처리 작업의 소요 시간(now[1])을 누적시키기
                // 작업 처리 총 소요 시간 = 작업 처리 종료 시간 - 현재 처리 작업이 요청되는 시점
                sum += end - now[0];  
                count++;    // 처리한 작업의 개수 1 증가
            }
        }
        
        return sum / jobs.length;   // 작업의 요청부터 종료까지 걸린 시간의 평균 리턴
    }
}