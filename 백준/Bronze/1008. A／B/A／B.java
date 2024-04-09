import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b;
        
        do {
            a = sc.nextInt();
        } while (a <= 0);
        do {
            b = sc.nextInt();
        } while (b >= 10);
        
        System.out.println((double)a / b);
    }
}