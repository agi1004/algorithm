import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        if ((int)c >= 48 && (int)c <= 57)
            System.out.println((int)c);
        else if (c >= 'A' && c <= 'Z')
            System.out.println((int)c);
        else if (c >= 'a' && c <= 'z')
            System.out.println((int)c);
    }
}