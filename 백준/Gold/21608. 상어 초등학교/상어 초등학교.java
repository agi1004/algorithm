import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N;
	static List<Integer>[] graph;
	static int[][] room;
	static int[][] visited;
	static int maxAdj;
	static List<Seat> emptySeats = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N * N + 1];
		room = new int[N][N];
		int[] students = new int[N * N];
		
		for (int i = 1; i <= N * N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			students[i] = student;
			
			for (int j = 0; j < 4; j++) {
				graph[student].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		if (N == 3) {
			room[N / 2][N / 2] = students[0];
		} else {
			room[1][1] = students[0];
		}
		
		for (int i = 1; i < N * N; i++) {
			visited = new int[N][N];
			maxAdj = 0;
			emptySeats.clear();
			
			findMaxEmptySeatsFromFavoriteStudent(students[i]);

			if (emptySeats.size() == 1) {
				Seat seat = emptySeats.get(0);
				room[seat.x][seat.y] = students[i];
			} else {
				for (Seat emptySeat : emptySeats) {
					findEmptySeatsFromEmptySeats(emptySeat);
				}
				Seat seat = getSeatAfterSort();
				room[seat.x][seat.y] = students[i];
			}
		}
		
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += getSatisfaction(i, j);
			}
		}
		
		System.out.println(sum);
	}
	
	public static void findMaxEmptySeatsFromFavoriteStudent(int student) {
		for (int favoriteStudent : graph[student]) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (room[i][j] == favoriteStudent) {
						findEmptySeatsFromFavoriteStudent(i, j);
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				maxAdj = Math.max(maxAdj, visited[i][j]);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == maxAdj && room[i][j] == 0) {
					emptySeats.add(new Seat(i, j, 0));
				}
			}
		}
	}

	public static void findEmptySeatsFromFavoriteStudent(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}
			
			if (room[nx][ny] == 0) {
				visited[nx][ny]++;
			}
		}
	}
	
	public static void findEmptySeatsFromEmptySeats(Seat emptySeat) {
		for (int i = 0; i < 4; i++) {
			int nx = emptySeat.x + dx[i];
			int ny = emptySeat.y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}
			
			if (room[nx][ny] == 0) {
				emptySeat.empty++;
			}
		}
	}
	
	public static Seat getSeatAfterSort() {
		emptySeats.sort((o1, o2) -> o2.empty - o1.empty);
		
		int maxEmpty = emptySeats.get(0).empty;
		int count = 1;
		
		for (Seat emptySeat : emptySeats) {
			if (emptySeat.empty > maxEmpty) {
				count++;
			}
		}
		
		if (count == 1) {
			return emptySeats.get(0);
		} else {
			emptySeats.sort((o1, o2) -> {
				if (o1.x == o2.x) {
					return o1.y - o2.y;
				}
				return o1.x - o2.x;
			});
			
			return emptySeats.get(0);
		}
	}
	
	public static int getSatisfaction(int x, int y) {
		int count = 0;
		int student = room[x][y];
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}
			
			for (int favoriteStudent : graph[student]) {
				if (favoriteStudent == room[nx][ny]) {
					count++;
					break;
				}
			}
		}
		
		return (int)Math.pow(10, count - 1);
	}
	
	static class Seat {
		int x, y, empty;
		
		Seat(int x, int y, int empty) {
			this.x = x;
			this.y = y;
			this.empty = empty;
		}
	}
}