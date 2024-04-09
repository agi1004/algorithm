import java.util.PriorityQueue;
import java.util.Scanner;

class LAN implements Comparable<LAN> {
	int com1, com2, length;
	
	LAN(int com1, int com2, int length) {
		this.com1 = com1;
		this.com2 = com2;
		this.length = length;
	}
	
	public int compareTo(LAN lan) {
		if (this.length < lan.length)
			return -1;
		else
			return 1;
	}
}

public class Main {
	static int[] parent;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	// 컴퓨터의 개수
		PriorityQueue<LAN> pq = new PriorityQueue<>();
		parent = new int[N + 1];
		// dasomLength - useLength가 최대가 나와야 함
		// 즉, useLength가 최소로 사용되어야 함
		int dasomLength = 0;	
		int useLength = 0;
		int lanCount = 0;
		
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			String str = sc.next();
			
			for (int j = 1; j <= N; j++) {
				char ch = str.charAt(j - 1);
				int length = 0;
				
				if (ch >= 'a' && ch <= 'z') {
					length = (int)(ch - 'a') + 1;
				} else if (ch >= 'A' && ch <= 'Z') {
					length = (int)(ch - 'A') + 27;
				}
				
				if (length != 0) {
					pq.add(new LAN(i, j, length));
				}
				
				dasomLength += length;
			}
			
		}
		
		// 모든 노드가 연결되지 않을 수도 있으므로
		// 우선순위 큐가 빌 때까지 반복
		while (!pq.isEmpty()) {
			LAN lan = pq.poll();
			
			if (find(lan.com1) != find(lan.com2)) {
				union(lan.com1, lan.com2);
				useLength += lan.length;
				lanCount++;
			}
			
			if (lanCount == N - 1)
				break;
		}
		
		for (int i = 2; i <= N; i++) {
			// 부모 노드가 다른 게 하나라도 존재한다면 모든 노드가 연결이 되지 않았다는 뜻임
			if (find(i) != find(1)) {
				System.out.println(-1);
				return;
			}
		}
		
		System.out.println(dasomLength - useLength);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) {
			parent[b] = a;
		}
	}
	
	public static int find(int x) {
		if (x == parent[x]) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	}
}