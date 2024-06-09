import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N;	// 수빈이가 있는 위치
	static int K;	// 동생이 있는 위치
	static ArrayList<Node>[] graph;
	static Stack<Node> position = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());	
		graph = new ArrayList[100001];
		
		for (int i = 0; i <= 100000; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int minTime = bfs();
		
		Node node = position.pop();
		
		while (true) {
			position.push(node);
			if (node.parent == null) {
				break;
			}
			node = node.parent;
		}
		
		bw.write(minTime + "\n");
		
		while (!position.isEmpty()) {
			node = position.pop();
			bw.write(node.num + " ");
		}
		
		bw.flush();
	}
	
	public static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[100001];
		
		Node node = new Node(N, 0, null);
		queue.add(node);
		visited[N] = true;
		addGraph(node);
		
		while (!queue.isEmpty()) {
			Node now = queue.poll();
			
			if (now.num == K) {
				position.push(now);
				return now.depth;
			}
			
			for (Node next : graph[now.num]) {			
				if (!visited[next.num]) {
					queue.add(next);
					visited[next.num] = true;
					addGraph(next);
				}
			}
		}
		
		return -1;
	}
	
	public static void addGraph(Node head) {
		if (head.num - 1 >= 0) {
			Node tail = new Node(head.num - 1, head.depth + 1, head);
			graph[head.num].add(tail);
		}
		if (head.num + 1 <= 100000) {
			Node tail = new Node(head.num + 1, head.depth + 1, head);
			graph[head.num].add(tail);	
		}
		if (head.num * 2 <= 100000) {
			Node tail = new Node(head.num * 2, head.depth + 1, head);
			graph[head.num].add(tail);
		}
	}
	
	static class Node {
		int num, depth;
		Node parent;
		
		Node(int num, int depth, Node parent) {
			this.num = num;
			this.depth = depth;
			this.parent = parent;
		}
	}
}