import java.util.*;

class Solution {
    List<String> allRoute = new ArrayList<>();
    boolean[] visited;
    
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        
        dfs(tickets, 0, "ICN", "ICN");
        
        Collections.sort(allRoute);
        
        return allRoute.get(0).split(" ");
    }
    
    public void dfs(String[][] tickets, int depth, String start, String route) {
        if (depth == tickets.length) {
            allRoute.add(route);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && start.equals(tickets[i][0])) {
                visited[i] = true;
                dfs(tickets, depth + 1, tickets[i][1], route + " " + tickets[i][1]);
                visited[i] = false;
            }
        }
    }
}