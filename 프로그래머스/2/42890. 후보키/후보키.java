import java.util.*;

class Solution {
    int N;
    boolean[] visited;
    List<String> keys = new ArrayList<>();
    
    public int solution(String[][] relation) {
        N = relation[0].length;
        visited = new boolean[N];
        
        // 컬럼을 고르는 개수: 1개 ~ relation의 열 개수
        for (int i = 1; i <= N; i++) {
            dfs(0, 0, i, new ArrayList<>(), relation);
        }
        
        // 유일성을 만족하는 키 집합 중에서 최소성을 만족하지 않는 키 제거
        Set<String> removes = new HashSet<>();
        
        for (int i = 0; i < keys.size(); i++) {
            String now = keys.get(i);
            
            for (int j = i + 1; j < keys.size(); j++) {
                String next = keys.get(j);
                int count = 0;
                
                for (char ch : now.toCharArray()) {
                    if (next.contains(String.valueOf(ch))) {
                        count++;
                    }
                }
                
                if (count == now.length()) {
                    removes.add(next);
                }
            }
        }
        
        keys.removeAll(removes);
        
        return keys.size();
    }
    
    public void dfs(int depth, int index, int count, List<Integer> selects, String[][] relation) {
        if (depth == count) {
            // 선택한 컬럼의 속성들이 모두 유일성을 만족한다면 키 집합에 추가
            if (isUnique(selects, relation)) {
                StringBuilder sb = new StringBuilder();
                
                for (int select : selects) {
                    sb.append(select);
                }
                
                keys.add(sb.toString());
            }
            
            return;
        }
        
        // 컬럼을 매개변수의 count 개수만큼 선택
        for (int i = index; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selects.add(i);
                
                dfs(depth + 1, i + 1, count, selects, relation);
                
                visited[i] = false;
                selects.remove((Integer)i);
            }
        }
    }
    
    // 선택한 컬럼의 속성들이 모두 유일성을 만족하는지 확인
    private boolean isUnique(List<Integer> selects, String[][] relation) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < relation.length; i++) {
            StringBuilder sb = new StringBuilder();

            for (int select : selects) {
                sb.append(relation[i][select] + " ");
            }

            if (set.contains(sb.toString())) {
                return false;
            } else {
                set.add(sb.toString());
            }
        }
        
        return true;
    }
}