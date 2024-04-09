import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int result = 0;
        
        for (int i = 0; i < N; i++) {
        	pq.offer(sc.nextInt());
        }
        
        while (pq.size() > 1) {
        	int sum = pq.poll() + pq.poll();
        	result += sum;
        	pq.offer(sum);
        }
        
        System.out.println(result);
        sc.close();
    }
}