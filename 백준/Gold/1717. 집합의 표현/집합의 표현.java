import java.io.*;

public class Main {
	static int[] parent;	// 부모 노드들의 집합
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		parent = new int[n + 1];		// 0 ~ n (n + 1개의 부모 노드 집합)
		
		for (int i = 0; i <= n; i++) {
			parent[i] = i;	// 현재 노드의 부모 노드의 값은 현재 인덱스(노드)로 초기화
		}
		
		for (int i = 0; i < m; i++) {
			s = br.readLine().split(" ");
			int question = Integer.parseInt(s[0]);
			int a = Integer.parseInt(s[1]);
			int b = Integer.parseInt(s[2]);
			
			// a가 포함되어 있는 집합과, b가 포함되어 있는 집합을 합침
			if (question == 0) {	
				union(a, b);	// 부모 노드를 같게 만드는 연산 수행
			} 
			// a와 b가 같은 집합에 포함되어 있는지 확인 (출력: YES or NO)
			else if (question == 1) {
				if (find(a) == find(b)) {	// 부모 노드가 같으면 같은 집합
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
		
		br.close();
	}
	
	// 부모 노드를 같게 만드는 연산
	public static void union(int a, int b) {
		a = find(a);	// a의 부모 노드 찾기
		b = find(b);	// b의 부모 노드 찾기
		
		// 부모 노드가 다르면
		if (a != b) {
			parent[b] = a;	// 같게 만들기
		}
	}
	
	// 부모 노드 찾아가는 연산
	public static int find(int x) {
		if (x == parent[x]) {	// 현재 노드와 부모 노드가 같으면
			return x;	// 그대로 리턴
		} else {	// 현재 노드와 부모 노드가 다르면
			// 재귀 함수로 확실하게 부모 노드 찾아가기
			// 이 과정에서 이 집합에 속한 노드들도 모두 다 올바른 부모 노드로 설정이 됨
			return parent[x] = find(parent[x]);		
		}
	}
}