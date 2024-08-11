import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE; // target으로 변환할 수 있는 최소 단계
    boolean[] visited;  // words 배열의 원소를 방문 체크할 배열
    
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];    // 방문 배열 크기 초기화
        List<String> wordList = Arrays.asList(words);   // 단어 배열을 리스트로 변환
        
        // 단어 리스트에 target이 없다면 바로 0 리턴
        if (!wordList.contains(target)) {
            return 0;
        }
        
        // begin부터 target을 찾으러 가기
        // 다음 실행할 단계: 0번째 단계
        dfs(words, begin, target, 0);
        
        return answer;  // target으로 변환할 수 있는 최소 단계 리턴
    }
    
    public void dfs(String[] words, String now, String target, int depth) {
        if (now.equals(target)) {   // 변환한 단어가 target과 같다면
            // target으로 변환할 수 있는 최소 단계 갱신
            answer = Math.min(answer, depth);
            return;     // 메서드 종료 후 다시 돌아가서 최소 단계 찾으러 가기
        }

        // 모든 단어들을 탐색
        for (int i = 0; i < words.length; i++) {
            if (!visited[i]) {  // i번째 단어를 방문하지 않았다면
                // i번째 단어(words[i])와 현재 단어(now) 중에서 다른 알파벳의 개수
                int diff = 0;   
                
                // i번째 단어의 알파벳들을 모두 탐색
                for (int j = 0; j < words[i].length(); j++) {
                    // i번째 단어의 j번째 알파벳과 현재 단어의 j번째 알파벳이 다르다면
                    if (words[i].charAt(j) != now.charAt(j)) {
                        diff++; // i번째 단어와 현재 단어 중에서 다른 알파벳의 개수 1 증가
                    }
                }
               
                // i번째 단어와 현재 단어 중에서 다른 알파벳의 개수가 1개라면
                if (diff == 1) {
                    visited[i] = true;  // i번째 단어를 방문 체크
                    // i번째 단어부터 target을 찾으러 가기
                    // 다음 실행할 단계: depth + 1번째 단계
                    dfs(words, words[i], target, depth + 1);
                    visited[i] = false; // i번째 단어를 방문 해제
                }
            }
        }
    }
}