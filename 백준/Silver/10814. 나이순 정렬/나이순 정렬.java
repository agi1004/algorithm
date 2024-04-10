import java.util.*;
import java.io.*;

class Member implements Comparable<Member> {
    public int age;
    public String name;
    public Member(int age, String name) {
        this.age = age;
        this.name = name;
    }
    @Override
    public int compareTo(Member other) {
        if (this.age != other.age)
            return this.age - other.age;
        else return 0;
    }
    @Override
    public String toString() {
        return age + " " + name;
    }
}
public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader(); // 문제 해결 소스 코드
        int N = fr.nextInt();
        ArrayList<Member> member = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int age = fr.nextInt();
            String name = fr.next();
            member.add(new Member(age, name));
        }
        Collections.sort(member);
        for (Member m : member)
            System.out.println(m);
    }
    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() { 
	        br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastReader(String s) throws FileNotFoundException { br = new BufferedReader(new FileReader(new File(s))); }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try { str = br.readLine(); }
            catch (IOException e) { e.printStackTrace(); }
            return str;
        }
    }
}