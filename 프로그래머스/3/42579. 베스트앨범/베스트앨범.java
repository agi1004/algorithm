import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genrePlays = new HashMap<>();
        Map<String, Integer> genreCounts = new HashMap<>();
        
        for (int i = 0; i < plays.length; i++) {
            genrePlays.put(genres[i], genrePlays.getOrDefault(genres[i], 0) + plays[i]);
            genreCounts.put(genres[i], genreCounts.getOrDefault(genres[i], 0) + 1);
        }
        
        Set<Song> album = new TreeSet<>(new Comparator<>() {
            public int compare(Song s1, Song s2) {
                if (Integer.compare(genrePlays.get(s2.genre), genrePlays.get(s1.genre)) == 0) {
                    if (Integer.compare(s2.play, s1.play) == 0) {
                        return Integer.compare(s1.num, s2.num);
                    }
                    return Integer.compare(s2.play, s1.play);
                }
                return Integer.compare(genrePlays.get(s2.genre), genrePlays.get(s1.genre));
            }
        });
        
        for (int i = 0; i < plays.length; i++) {
            album.add(new Song(i, genres[i], plays[i]));
        }
        
        int count = 0;
        
        for (String genre : genreCounts.keySet()) {
            if (genreCounts.get(genre) == 1) {
                count += 1;
            } else {
                count += 2;
            }
        }
        
        int[] answer = new int[count];
        int answerIdx = 0;
        Map<String, Integer> inputCounts = new HashMap<>();
        
        for (String genre : genres) {
            inputCounts.put(genre, 0);
        }
        
        for (Song song : album) {
            if (answerIdx == answer.length) {
                break;
            }
            
            if (inputCounts.get(song.genre) == 2 ||
                inputCounts.get(song.genre) == genreCounts.get(song.genre)) {
                continue;
            }
            
            answer[answerIdx++] = song.num;
            inputCounts.put(song.genre, inputCounts.getOrDefault(song.genre, 0) + 1);
        }
        
        return answer;
    }
}

class Song {
    int num;
    String genre;
    int play;
    
    Song(int num, String genre, int play) {
        this.num = num;
        this.genre = genre;
        this.play = play;
    }
}