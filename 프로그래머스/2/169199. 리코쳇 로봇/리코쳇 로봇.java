import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;     // 목표 위치에 도달할 수 있는 최소 이동 횟수
    // 상(x - 1, y), 하(x + 1, y), 좌(x, y - 1), 우(x, y + 1) 이동
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int N, M;
    
    public int solution(String[] board) {
        N = board.length;       // 보드의 세로 길이
        M = board[0].length();  // 보드의 가로 길이
        
        Point R = getR(board);  // 처음 위치
        bfs(board, R);  // 처음 위치부터 목표 위치에 도달할 수 있는 최소 이동 횟수 얻기
        
        // answer의 값이 여전히 최댓값이라면
        if (answer == Integer.MAX_VALUE) {
            answer = -1;    // 목표 위치에 도달 불가능
        }
        
        return answer;  // 목표 위치에 도달할 수 있는 최소 이동 횟수 리턴
    }
    
    // 목표 위치에 도달할 수 있는 최소 이동 횟수를 bfs를 사용하여 얻는 메서드
    public void bfs(String[] board, Point R) {
        Queue<Point> queue = new LinkedList<>();    // bfs에 사용할 큐
        boolean[][] visited = new boolean[N][M];    // 방문 체크 배열
        
        queue.add(R);   // 처음 위치 큐에 삽입
        visited[R.x][R.y] = true;   // 처음 위치 방문 체크
        
        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Point now = queue.poll();   // 현재 좌표를 큐에서 뽑기
            
            // 현재 좌표가 목표 위치라면
            if (board[now.x].charAt(now.y) == 'G') {
                // 목표 위치에 도달할 수 있는 최소 이동 횟수 갱신
                answer = Math.min(answer, now.count);
            }
            
            // 상, 하, 좌, 우 4가지 방향으로 모두 이동시켜보기
            for (int i = 0; i < 4; i++) {
                // 장애물이나 가장자리에 부딪치기 바로 전 위치로 이동한 좌표
                Point next = move(board, now, i);
                
                // 이동 좌표를 방문하지 않았다면
                if (!visited[next.x][next.y]) {
                    queue.add(next);    // 이동 좌표 큐에 삽입
                    visited[next.x][next.y] = true;     // 이동 좌표 방문 체크
                }
            }
        }
    }
    
    // 현재 좌표를 장애물이나 가장자리에 부딪치기 전까지 한 가지 방향으로 이동한 좌표를 얻는 메서드
    public Point move(String[] board, Point now, int d) {
        int nx = now.x, ny = now.y;
        
        while (true) {
            // x좌표와 y좌표를 각각 d 방향으로 이동
            nx = nx + dx[d];
            ny = ny + dy[d];
            
            // 유효한 인덱스가 아니라면 가장자리에 부딪친 것
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                break;
            }

            // 장애물에 부딪친 경우
            if (board[nx].charAt(ny) == 'D') {
                // 장애물에 부딪치기 바로 전 좌표 리턴
                return new Point(nx - dx[d], ny - dy[d], now.count + 1);
            }
        }
        
        // 장애물 없이 가장자리에 부딪친 경우
        switch (d) {
            case 0:     // 위 가장자리에 부딪치기 바로 전 좌표 리턴
                return new Point(0, ny, now.count + 1);
            case 1:     // 아래 가장자리에 부딪치기 바로 전 좌표 리턴
                return new Point(N - 1, ny, now.count + 1);
            case 2:     // 왼쪽 가장자리에 부딪치기 바로 전 좌표 리턴
                return new Point(nx, 0, now.count + 1);
            case 3:     // 오른쪽 가장자리에 부딪치기 바로 전 좌표 리턴
                return new Point(nx, M - 1, now.count + 1);
        }
        
        return null;
    }
    
    // 처음 위치의 좌표를 얻는 메서드
    public Point getR(String[] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i].charAt(j) == 'R') {
                    return new Point(i, j, 0);
                }
            }
        }
        
        return null;
    }
    
    static class Point {
        int x, y, count;

        Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}