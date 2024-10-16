class Solution {
    public int solution(String s) {
        int answer = 1001;
        
        if (s.length() == 1) {
            return 1;
        }
        
        // 문자열을 i개씩 자르기
        for (int i = 1; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder(); // 압축한 문자열
            String temp = s.substring(0, i);    // 문자열 s를 맨 앞에서 i개 자른 비교 대상 문자열
            int count = 1;  // 비교 대상 문자열(temp)의 중복 횟수
            
            // 인덱스 j부터 i개씩 자른 문자열(now)과 temp를 비교
            for (int j = i; j < s.length(); j += i) {
                String now;     // 현재 자른 문자열
                
                // 문자열 s를 인덱스 j부터 i개씩 자르기
                if (j + i >= s.length()) {
                    now = s.substring(j);
                } else {
                    now = s.substring(j, j + i);
                }
                
                if (temp.equals(now)) { // 비교 대상 문자열(temp)와 현재 자른 문자열(now)이 같다면
                    count++;    // 중복 횟수 1 증가
                } else {    // 비교 대상 문자열(temp)와 자른 문자열(now)이 다르다면
                    if (count >= 2) {   // 중복 횟수가 2 이상일 때
                        sb.append(count + temp);    // 압축 문자열에 중복 횟수와 비교 대상 문자열(temp) 추가
                    } else {    // 중복 횟수가 1일 때
                        sb.append(temp);    // 압축 문자열에 비교 대상 문자열(temp)만 추가
                    }
                    
                    temp = new String(now); // 현재 자른 문자열을 비교 대상 문자열로 갱신
                    count = 1;  // 비교 대상 문자열의 중복 횟수를 다시 계산해야 하므로 1로 만들기
                }
            }
            
            // 남은 문자열 처리
            if (count >= 2) {   // 중복 횟수가 2 이상일 때
                sb.append(count + temp);    // 압축 문자열에 중복 횟수와 비교 대상 문자열(temp) 추가
            } else {    // 중복 횟수가 1일 때
                sb.append(temp);    // 압축 문자열에 비교 대상 문자열(temp)만 추가
            }
            
            answer = Math.min(answer, sb.length()); // 압축한 문자열의 최소 길이 갱신
        }
        
        return answer;
    }
}