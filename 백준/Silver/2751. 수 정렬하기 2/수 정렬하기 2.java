import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[] A, tmp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());	// 배열 수
		A = new int[N + 1];		// 주어진 값 저장할 배열
		tmp = new int[N + 1];	// 정렬에 사용할 임시 배열
		
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		// 병합 정렬 수행
		mergeSort(1, N);
		
		// 병합 정렬된 배열 출력
		for (int i = 1; i <= N; i++) {
			bw.write(A[i] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	// 병합 정렬
	public static void mergeSort(int s, int e) {
		// 끝값(e)에서 시작값(s)을 뺀 값이 0이라면 종료
		if (e - s < 1)
			return;
		
		// 중간값(m) 찾기
		int m = s + (e - s) / 2;
		
		// 병합 정렬 수행 (재귀)
		mergeSort(s, m);		// 첫번째 방
		mergeSort(m + 1, e);	// 두번째 방
		
		// 임시 배열에 값 복사
		for (int i = s; i <= e; i++) {
			tmp[i] = A[i];
		}
		
		int k = s;				// 임시 배열(tmp) index에 사용
		int index1 = s;			// 첫번째 방 index에 사용
		int index2 = m + 1;		// 두번째 방 index에 사용
		
		// 첫번째 방 인덱스가 m보다 작고(m + 1부터는 두번째 방의 첫번째 인덱스이기 때문), 
		// 두번째 방 인덱스가 e보다 작을 때까지 반복
		while (index1 <= m && index2 <= e) {
			// 첫번째 방에 가리키는 포인터의 값이 두번째 방에 가리키는 포인터보다 작다면
			if (tmp[index1] < tmp[index2]) {	
				A[k] = tmp[index1];		// A(정렬값을 저장해야 할 배열)에 그 값 저장
				k++;					// A 인덱스 값 증가
				index1++;				// 첫번째 방 인덱스 값 증가
			} else {	// 두번째 방에 가리키는 포인터가 더 작다면
				A[k] = tmp[index2];		// A(정렬값을 저장해야 할 배열)에 그 값 저장
				k++;					// A 인덱스 값 증가
				index2++;				// 두번째 방 인덱스 값 증가
			}
		}
		// 첫번째 방에 만약 남은 값이 있다면 tmp에 저장 (두번째 방은 정렬 완료된 경우)
		while (index1 <= m) {			
			A[k] = tmp[index1];
			k++;
			index1++;
		}
		// 두번째 방에 만약 남은 값이 있다면 tmp에 저장 (첫번째 방은 정렬 완료된 경우)
		while (index2 <= e) {			
			A[k] = tmp[index2];
			k++;
			index2++;
		}
	}
}