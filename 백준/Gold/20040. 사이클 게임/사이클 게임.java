import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());	// 점의 개수 (0 ~ n - 1)
		int m = Integer.parseInt(st.nextToken());	// 진행된 차례의 수
		parent = new int[n];	// 부모 노드 배열
		
		// 부모 노드 배열을 인덱스 값으로 초기화
		for (int i = 0; i < n; i++) {
			parent[i] = i;	
		}
		
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (union(a, b)) {	// a와 b의 부모 노드가 같다면 사이클 형성 O
				System.out.println(i);	// 해당 라인 출력
				return;					// 함수 종료
			}
		}
		
		System.out.println(0);	// 사이클이 형성되지 않았으면 마지막에 0 출력
	}
	
	public static boolean union(int a, int b) {
		a = find(a);	// a의 부모 노드
		b = find(b);	// b의 부모 노드
		
		if (a != b) {		// a와 b의 부모 노드가 다르면 사이클 형성 X. 
			parent[b] = a;	// a와 b의 부모 노드를 같게 만들어주기
			return false;	// false 리턴
		} else {			// a와 b의 부모 노드가 같으면 사이클 형성 O
			return true;	// true 리턴
		}
	}
	
	public static int find(int x) {
		if (x == parent[x]) {	// 자식 노드와 부모 노드가 같으면
			return x;	// 자식 노드 리턴
		} else {				// 자식 노드와 부모 노드가 다르면
			return parent[x] = find(parent[x]);	// 재귀함수로 부모 노드 찾아서 부모 노드 값 갱신
		}
	}
}