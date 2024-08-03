import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer, Integer> out = new HashMap<>();    // K: 정점, V: 나가는 간선의 개수
        Map<Integer, Integer> in = new HashMap<>();     // K, 정점, V: 들어오는 간선의 개수
        
        for (int[] edge : edges) {
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
            in.put(edge[1], in.getOrDefault(edge[1], 0) + 1);
        }
        
        for (int node : out.keySet()) {
            if (out.get(node) > 1) {    // 나가는 간선이 2개 이상인 정점 중에서
                if (!in.containsKey(node)) {    // 들어오는 간선이 없으면 시작 정점
                    answer[0] = node;
                } else {    // 들어오는 간선이 있으면 8자 그래프
                    answer[3]++;
                }
            }
        }
        
        for (int node : in.keySet()) {  // 들어오는 간선들이 있는 정점 중에서
            if (!out.containsKey(node)) {   // 나가는 간선이 없으면 막대 그래프
                answer[2]++;
            }
        }
        
        // 도넛 그래프 개수 = 시작 정점의 나가는 간선 개수 - 막대 그래프 개수 - 8자 그래프 개수
        answer[1] = out.get(answer[0]) - answer[2] - answer[3];
        
        return answer;
    }
}
