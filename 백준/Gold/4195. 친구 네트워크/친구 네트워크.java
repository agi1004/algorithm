import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {	
	static int parent[];
	static int count[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스의 개수
		
		for (int t = 0; t < T; t++) {
			int F = Integer.parseInt(br.readLine());	// 친구 관계 수
			parent = new int[F * 2];	// 부모 노드 배열 (F의 1줄 당 2명씩 있으므로 F * 2)
			count = new int[F * 2];		// 친구 네트워크 인원 배열
			HashMap<String, Integer> map = new HashMap<>();	// <이름, 인덱스>
			int index = 0;	// map의 value에 사용할 인덱스
							// -> 이름(String) 대신 숫자로 편하게 조작하기 위해
			
			for (int i = 0; i < F * 2; i++) {
				parent[i] = i;	// 모두 인덱스 값으로 초기화
				count[i] = 1;	// 처음에는 아무 관계도 맺지 않으므로 모두 1로 초기화
			}
			
			for (int f = 0; f < F; f++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String p1 = st.nextToken();		// 1번째 사람
				String p2 = st.nextToken();		// 2번째 사람
				
				// map에 1번째 사람이 없다면 (처음 들어오는 사람일 경우)
				if (!map.containsKey(p1)) {	
					map.put(p1, index++);	// map에 key: 1번째 사람, value: 인덱스++ 로 추가
				}
				// map에 2번째 사람이 없다면 (처음 들어오는 사람일 경우)
				if (!map.containsKey(p2)) {	
					map.put(p2, index++);	// map에 key: 2번째 사람, value: 인덱스++ 로 추가
				}
				
				// map에서 p1의 value(인덱스)와 p2의 value(인덱스)를 union한 결과 출력 
				System.out.println(union(map.get(p1), map.get(p2)));
			}
		}
	}
	
	public static int union(int a, int b) {	// 일반적인 union 함수와 다른 점: *로 표시
		a = find(a);	// a의 부모 노드 찾기
		b = find(b);	// b의 부모 노드 찾기
		
		if (a != b) {	// a와 b의 부모 노드가 다르다면
			parent[b] = a;			// b의 부모 노드를 a로 만들기 (두 부모 노드를 같게 만들기)
			count[a] += count[b];	// *a의 친구 네트워크 인원 값에 b의 친구 네트워크 인원 값 더하기 
		}
		
		return count[a];			// *a의 친구 네트워크 인원 값 리턴
	}
	
	public static int find(int x) {	// 일반적인 find 함수와 동일
		if (x == parent[x]) {		
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	}
}