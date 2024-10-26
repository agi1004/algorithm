class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxPlaytime = 0;
        
        m = replaceMelody(m);
        
        for (int i = 0; i < musicinfos.length; i++) {
            String[] arr = musicinfos[i].split(",");
            
            int playtime = getPlaytime(arr[0], arr[1]);
            String title = arr[2];
            String melody = replaceMelody(arr[3]);
            
            String playedMelody = getPlayedMelody(playtime, melody);
            
            if (playedMelody.contains(m)) {
                if (playtime > maxPlaytime) {
                    maxPlaytime = playtime;
                    answer = title;
                }
            }
        }
        
        return answer;
    }
    
    public String getPlayedMelody(int playtime, String melody) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < playtime; i++) {
            sb.append(melody.charAt(i % melody.length()));
        }
        
        return sb.toString();
    }
    
    public int getPlaytime(String s, String e) {
        int start = Integer.parseInt(s.split(":")[0]) * 60 + Integer.parseInt(s.split(":")[1]);
        int end = Integer.parseInt(e.split(":")[0]) * 60 + Integer.parseInt(e.split(":")[1]);
        return end - start;
    }
    
    public String replaceMelody(String melody) {
        return melody.replace("C#", "c")
                     .replace("D#", "d")
                     .replace("F#", "f")
                     .replace("G#", "g")
                     .replace("A#", "a")
                     .replace("B#", "b");
    }
}