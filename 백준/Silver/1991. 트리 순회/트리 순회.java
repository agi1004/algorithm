import java.util.Scanner;

class Node {
	int left, right;	// 왼쪽 자식 노드, 오른쪽 자식 노드
	
	Node(int left, int right) {
		this.left = left;
		this.right = right;
	}
}

public class Main {
	static Node[] binaryTree;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	// 이진 트리의 노드의 개수
		binaryTree = new Node[26];	// 각 노드의 왼쪽 자식 노드, 오른쪽 자식 노드가 담긴 배열
		
		sc.nextLine();	// 버퍼에 남은 공백을 지워주기
				
		for (int i = 0; i < N; i++) {
			String[] s = sc.nextLine().split(" ");
			int node = s[0].charAt(0) - 'A';	// 노드 인덱스
			char left = s[1].charAt(0);			// 왼쪽 자식 노드
			char right = s[2].charAt(0);		// 오른쪽 자식 노드
			
			// 왼쪽, 오른쪽 자식 노드가 둘 다 없다면
			if (left == '.' && right == '.') {
				// 왼쪽, 오른쪽 자식 노드에 -1 삽입
				binaryTree[node] = new Node(-1, -1);
			} else if (left == '.') {	// 왼쪽 자식 노드가 없다면
				// 왼쪽 자식 노드에 -1 삽입하고,
				// 오른쪽 자식 노드를 인덱스로 바꿔서 삽입
				binaryTree[node] = new Node(-1, right - 'A');
			} else if (right == '.') {	// 오른쪽 자식 노드가 없다면
				// 왼쪽 자식 노드를 인덱스로 바꿔서 삽입하고,
				// 오른쪽 자식 노드에 -1 삽입
				binaryTree[node] = new Node(left - 'A', -1);
			} else {	// 왼쪽, 오른쪽 자식 노드가 둘 다 있다면
				// 왼쪽 자식 노드와 오른쪽 자식 노드를 인덱스로 바꿔서 삽입
				binaryTree[node] = new Node(left - 'A', right - 'A');
			}
		}
		
		preorder(0);	// 전위 순회
		System.out.println();
		inorder(0);		// 중위 순회
		System.out.println();
		postorder(0);	// 후위 순회
		System.out.println();
	}
	
	// 전위 순회 (루트 -> 왼쪽 -> 오른쪽)
	public static void preorder(int now) {
		if (now == -1)	// 현재 노드가 -1이면 더이상 순회를 할 수 없으므로 종료
			return;
		System.out.print((char)(now + 'A'));	// 1. 현재 노드 출력 (전위는 루트노드가 맨 앞)
		preorder(binaryTree[now].left);			// 2. 왼쪽 탐색하기
		preorder(binaryTree[now].right);		// 3. 오른쪽 탐색하기
	}
	
	// 중위 순회 (왼쪽 -> 루트 -> 오른쪽)
	public static void inorder(int now) {
		if (now == -1)
			return;
		inorder(binaryTree[now].left);			// 1. 왼쪽 탐색하기
		System.out.print((char)(now + 'A'));	// 2. 현재 노드 출력 (중위는 루트노드가 중간)
		inorder(binaryTree[now].right);			// 3. 오른쪽 탐색하기
	}
	
	// 후위 순회 (왼쪽 -> 오른쪽 -> 루트)
	public static void postorder(int now) {
		if (now == -1)
			return;
		postorder(binaryTree[now].left);		// 1. 왼쪽 탐색하기
		postorder(binaryTree[now].right);		// 2. 오른쪽 탐색하기
		System.out.print((char)(now + 'A'));	// 3. 현재 노드 출력 (후위는 루트노드가 맨 뒤)
	}
}