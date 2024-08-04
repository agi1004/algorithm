import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};   // 상(-1, 0), 하(1, 0),
    int[] dy = {0, 0, -1, 1};   // 좌(0, -1), 우(0, 1) 이동 
    int n, m;
    boolean[][] visited;
    int[][] area;
    
    public int solution(int[][] land) {
        int answer = 0;                 // 시추관 하나로 뽑을 수 있는 가장 많은 석유량
        n = land.length;                // 땅의 세로 길이
        m = land[0].length;             // 땅의 가로 길이
        visited = new boolean[n][m];    // 방문 체크 배열
        area = new int[n][m];           // 각 구역마다 번호 부여하는 배열
        int areaNum = 1;                // 구역 번호
        
        // 모든 칸을 색
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                // 해당 칸을 방문하지 않았고, 석유가 있는 칸이라면
                if (!visited[x][y] && land[x][y] == 1) {
                    // 석유 덩어리의 크기를 구하고, 구역 번호를 부여하기
                    findOilLump(land, x, y, areaNum++);
                }
            }
        }
        
        // 열을 기준으로 탐색
        for (int y = 0; y < m; y++) {
            int sum = 0;    // 해당 열에서 뽑은 석유량
            // 해당 열에서 뽑을 수 있는 석유 덩어리의 구역 번호들의 집합
            Set<Integer> areaNums = new HashSet<>();
            
            // 해당 열의 모든 행을 탐색
            for (int x = 0; x < n; x++) {
                // 해당 칸에 석유가 존재하고, 
                // 구역 번호 집합에 해당 석유의 구역 번호가 없다면
                // 새로운 석유 덩어리를 발견한 것
                if (land[x][y] != 0 && !areaNums.contains(area[x][y])) {
                    sum += land[x][y];      // 뽑은 석유 덩어리의 크기를 누적
                    areaNums.add(area[x][y]);   // 구역 번호 집합에 해당 석유의 구역 번호 추가
                }
            }
            
            answer = Math.max(answer, sum); // 하나의 열에서 뽑을 수 있는 석유량의 최댓값 갱신
        }
        
        return answer;  // 시추관 하나로 뽑을 수 있는 가장 많은 석유량 리턴
    }
    
    // 석유 덩어리의 크기를 구하고, 구역 번호를 부여하는 메서드
    public void findOilLump(int[][] land, int startX, int startY, int areaNum) {
        Queue<Oil> queue = new LinkedList<>();  // bfs 수행을 위한 큐
        List<Oil> oils = new ArrayList<>();     // 석유 덩어리에 해당하는 석유 칸들의 리스트
        int count = 1;      // 석유 덩어리에 해당하는 석유 칸들의 개수
        Oil start = new Oil(startX, startY);    // 시작 석유 생성
        
        queue.add(start);                   // 시작 석유를 큐에 삽입
        visited[startX][startY] = true;     // 시작 석유를 방문 체크
        
        oils.add(start);    // 석유 리스트에 시작 석유 추가
        land[startX][startY] = count++;     // 현재 석유 칸의 개수를 시작 칸에 저장하고 석유 칸의 개수 1 증가
        area[startX][startY] = areaNum;     // 현재 구역 번호를 시작 칸에 부여
        
        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Oil now = queue.poll();     // 현재 석유를 큐에서 뽑기
            
            // 현재 석유를 기준으로 상, 하, 좌, 우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];     // 인접 칸의 x좌표
                int ny = now.y + dy[i];     // 인접 칸의 y좌표
                
                // 인접 칸의 인덱스가 유효하지 않다면 건너뛰기
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                
                // 인접 칸을 이미 방문했거나, 석유가 없는 칸이라면 건너뛰기
                if (visited[nx][ny] || land[nx][ny] == 0) {
                    continue;
                }
                
                Oil next = new Oil(nx, ny);     // 인접 석유 생성
                
                queue.add(next);            // 인접 석유를 큐에 삽입
                visited[nx][ny] = true;     // 인접 석유를 방문 체크   
                
                oils.add(next);             // 석유 리스트에 인접 석유 추가
                land[nx][ny] = count++;     // 현재 석유 칸의 개수를 인접 칸에 저장하고 석유 칸의 개수 1 증가
                area[nx][ny] = areaNum;     // 현재 구역 번호를 인접 칸에 부여
                
                startX = nx;    // 가장 최근에 방문한 x좌표 갱신
                startY = ny;    // 가장 최근에 방문한 y좌표 갱신
            }
        }
        
        // 이 석유 덩어리에 해당하는 모든 석유를 탐색
        for (Oil oil : oils) {
            // 리스트에 있는 석유 칸들을 이 석유 덩어리의 최종 크기로 모두 바꿔주기
            land[oil.x][oil.y] = land[startX][startY];
        }
    }
    
    static class Oil {
        int x, y;   // 석유의 x좌표, y좌표
        
        Oil(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
