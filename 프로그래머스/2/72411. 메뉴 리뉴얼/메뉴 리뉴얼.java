import java.util.*;

class Solution {
    int maxCourse;
    Map<Integer, Map<String, Integer>> map = new HashMap<>();
    Set<Integer> courses = new HashSet<>();
    
    public String[] solution(String[] orders, int[] course) {
        maxCourse = course[course.length - 1];
        
        for (int count : course) {
            map.put(count, new HashMap<>());
            courses.add(count);
        }
        
        for (String order : orders) {
            order = sort(order);
            dfs(0, order, new boolean[order.length()], new StringBuilder());
        }
        
        List<String> list = new ArrayList<>();
        
        for (int count : course) {
            int maxOrderCount = 0;
            Map<String, Integer> temp = map.get(count);
            
            for (int orderCount : temp.values()) {
                maxOrderCount = Math.max(maxOrderCount, orderCount);
            }
            
            if (maxOrderCount < 2) {
                continue;
            }
            
            for (String food : temp.keySet()) {
                if (temp.get(food) == maxOrderCount) {
                    list.add(food);
                }
            }
        }
        
        Collections.sort(list);
        
        return list.toArray(new String[0]);
    }
    
    public void dfs(int index, String order, boolean[] visited, StringBuilder sb) {
        int depth = sb.length();
        
        if (courses.contains(depth)) {
            Map<String, Integer> temp = map.get(depth);
            String food = sb.toString();
            
            temp.put(food, temp.getOrDefault(food, 0) + 1);
            map.put(depth, temp);
            
            if (depth == maxCourse) {
                return;
            }
        }
        
        for (int i = index; i < order.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                sb.append(order.charAt(i));
                dfs(i + 1, order, visited, sb);
                visited[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    
    public String sort(String order) {
        char[] arr = order.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}