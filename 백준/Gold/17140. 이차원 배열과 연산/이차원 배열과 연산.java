import java.io.*;
import java.util.*;

public class Main {
	static int[][] A = new int[100][100];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if (A[r - 1][c - 1] == k) {
			System.out.println(0);
			return;
		}
		
		int time = 0, row = 3, col = 3;
		
		while (time <= 100) {
			time++;
			
			if (row >= col) {
				col = R(row, col);
			} else {
				row = C(row, col);
			}
			
			if (A[r - 1][c - 1] == k) {
				System.out.println(time);
				return;
			}
		}
		
		System.out.println(-1);
	}
	
	public static int R(int row, int col) {
		int maxCol = 0;
		
		for (int i = 0; i < row; i++) {
			Map<Integer, Integer> numCountMap = new TreeMap<>();
			
			for (int j = 0; j < col; j++) {
				if (A[i][j] != 0) {
					numCountMap.put(A[i][j], numCountMap.getOrDefault(A[i][j], 0) + 1);
				}
			}
			
			List<Integer> numList = keySetToSortedList(numCountMap);
			
			int newCol = Math.min(numList.size() * 2, 100);
			
			for (int j = 0; j < newCol; j++) {
				if (j % 2 == 0) {
					A[i][j] = numList.get(j / 2);
				} else {
					A[i][j] = numCountMap.get(numList.get(j / 2));
				}
			}
			
			for (int j = newCol; j < col; j++) {
				A[i][j] = 0;
			}
			
			maxCol = Math.max(maxCol, newCol);
		}
		
		return maxCol;
	}
	
	public static int C(int row, int col) {
		int maxRow = 0;
		
		for (int i = 0; i < col; i++) {
			Map<Integer, Integer> numCountMap = new HashMap<>();
			
			for (int j = 0; j < row; j++) {
				if (A[j][i] != 0) {
					numCountMap.put(A[j][i], numCountMap.getOrDefault(A[j][i], 0) + 1);
				}
			}
			
			List<Integer> numList = keySetToSortedList(numCountMap);
			
			int newRow = Math.min(numList.size() * 2, 100);			
			
			for (int j = 0; j < newRow; j++) {	
				if (j % 2 == 0) {
					A[j][i] = numList.get(j / 2);
				} else {
					A[j][i] = numCountMap.get(numList.get(j / 2));
				}
			}
			
			for (int j = newRow; j < row; j++) {
				A[j][i] = 0;
			}
			
			maxRow = Math.max(maxRow, newRow);
		}
		
		return maxRow;
	}
	
	private static List<Integer> keySetToSortedList(Map<Integer, Integer> numCountMap) {
		List<Integer> numList = new ArrayList<>(numCountMap.keySet());
		
		// 숫자(키) 리스트 정렬
		// => 개수를 기준으로 오름차순 정렬, 개수가 같다면 숫자를 기준으로 오름차순 정렬
		numList.sort((o1, o2) -> {
			if (Integer.compare(numCountMap.get(o1), numCountMap.get(o2)) == 0) {
				return Integer.compare(o1, o2);
			}
			return Integer.compare(numCountMap.get(o1), numCountMap.get(o2));
		});
		
		return numList;
	}
}