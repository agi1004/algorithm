import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N;
	static int[][] fireballCount;
	static List<Fireball> fireballs = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		fireballCount = new int[N + 1][N + 1];
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireballs.add(new Fireball(r, c, m, s, d));
			fireballCount[r][c] = 1;
		}
		
		for (int i = 0; i < K; i++) {
			move();
			mergeAndDivide();
		}
		
		int sum = 0;
		
		for (Fireball fb : fireballs) {
			sum += fb.m;
		}
		
		System.out.println(sum);
	}
	
	public static void move() {
		for (Fireball fb : fireballs) {
			fireballCount[fb.r][fb.c]--;

			for (int j = 0; j < fb.s; j++) {
				fb.r = fb.r + dx[fb.d];
				fb.c = fb.c + dy[fb.d];
				
				if (fb.r < 1) {
					fb.r = N;
				} else if (fb.r > N) {
					fb.r = 1;
				}
				
				if (fb.c < 1) {
					fb.c = N;
				} else if (fb.c > N) {
					fb.c = 1;
				}
			}
			
			fireballCount[fb.r][fb.c]++;
		}
	}
	
	public static void mergeAndDivide() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (fireballCount[i][j] >= 2) {
					int mSum = 0, sSum = 0, count = 0;
					List<Integer> oddEvens = new ArrayList<>();
					List<Fireball> rmFireballs = new ArrayList<>();
					
					for (Fireball fb : fireballs) {
						if (fb.r == i && fb.c == j) {
							mSum += fb.m;
							sSum += fb.s;
							count++;
							
							if (fb.d % 2 == 0) {
								oddEvens.add(0);
							} else {
								oddEvens.add(1);
							}
							
							fireballCount[fb.r][fb.c]--;
							rmFireballs.add(fb);
						}
					}
					
					for (Fireball fb : rmFireballs) {
						fireballs.remove(fb);
					}
					
					int m = mSum / 5;
					int s = sSum / count;
					
					if (m == 0) {
						fireballCount[i][j] = 0;
						continue;
					} 
					
					boolean same = true;
					
					for (int oddEven : oddEvens) {
						if (oddEven != oddEvens.get(0)) {
							same = false;
							break;
						}
					}
					
					if (same) {
						for (int d = 0; d <= 6; d += 2) {
							fireballs.add(new Fireball(i, j, m, s, d));
						}
					} else {
						for (int d = 1; d <= 7; d += 2) {
							fireballs.add(new Fireball(i, j, m, s, d));
						}
					}
					
					fireballCount[i][j] += 4;
				}
			}
		}
	}
	
	static class Fireball {
		int r, c, m, s, d;
		
		Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}