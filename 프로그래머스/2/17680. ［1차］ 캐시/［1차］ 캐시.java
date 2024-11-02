import java.util.*;

class Solution {
    static final int CACHE_HIT = 1;
    static final int CACHE_MISS = 5;
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> cache = new ArrayList<>();
        
        if (cacheSize == 0) {
            return cities.length * CACHE_MISS;
        }
        
        for (String city : cities) {
            String upperCity = city.toUpperCase();
            int index = cache.indexOf(upperCity);
            
            if (index != -1) {
                cache.add(cache.remove(index));
                answer += CACHE_HIT;
                continue;
            }
            
            if (cache.size() >= cacheSize) {
                cache.remove(0);
            }
            
            cache.add(upperCity);
            answer += CACHE_MISS;
        }
        
        return answer;
    }
}