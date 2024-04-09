import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int parent[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 사람 수
		int M = Integer.parseInt(st.nextToken());	// 파티 수
		ArrayList<Integer>[] party = new ArrayList[M];
		parent = new int[N + 1];
		int result = 0;	// 거짓말을 할 수 있는 파티의 수
		
		for (int i = 0; i < M; i++) {
			party[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		int knowLiar = Integer.parseInt(st.nextToken());	// 거짓말인걸 아는 사람의 수
		int[] knowLiars = new int[knowLiar];
		for (int i = 0; i < knowLiar; i++) {
			knowLiars[i] = Integer.parseInt(st.nextToken());	// 거짓말인걸 아는 사람들 추가
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());	// 파티 참여 인원
			
			for (int j = 0; j < count; j++) {
				int n = Integer.parseInt(st.nextToken());
				party[i].add(n);	// 파티 참여자들 추가
			}
			
			for (int p : party[i]) {
				int partyGroup = party[i].get(0);	// 파티원중 한명을 대표로 선택해서
				union(p, partyGroup);	// 파티 참여자들은 모두 같은 부모 노드를 갖게 만들기
			}
		}
		
		for (int i = 0; i < M; i++) {
			// 해당 파티의 참여자들은 모두 부모 노드가 같으므로 파티원중 한명만 선택해도 됨
			int partyGroup = party[i].get(0);
			boolean fool = true;

			// 거짓말인걸 알고있는 사람들 중 한명이라도 파티원과 부모 노드가 같다면
			for (int j = 0; j < knowLiar; j++) {	
				if (find(partyGroup) == find(knowLiars[j])) {	
					fool = false;	// 해당 파티에서는 거짓말을 할 수 없음
					break;
				}
			}
			
			if (fool) {	// 해당 파티에서 거짓말을 할 수 있다면
				result++;	// 거짓말을 할 수 있는 수 1 증가
			}
		}
		
		System.out.println(result);
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