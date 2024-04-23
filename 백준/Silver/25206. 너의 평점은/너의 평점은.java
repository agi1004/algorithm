import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double sumOfGrade = 0.0;		// 학점의 총합
		double sumOfGradeMulAvg = 0.0;	// (학점 * 과목평점)의 합
		
		for (int i = 0; i < 20; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String subject = st.nextToken();	// 과목명
			double grade = Float.parseFloat(st.nextToken());	// 학점
			String avg = st.nextToken();		// 과목평점
			
			sumOfGradeMulAvg +=	getGradeMulAvg(grade, avg);
			if (!avg.equals("P")) {	// 과목평점이 P가 아닐 경우만 계산
				sumOfGrade += grade;
			}
		}
		
		// 전공평점 = (학점 * 과목평점)의 합 / 학점의 총합
		System.out.printf("%.6f", sumOfGradeMulAvg / sumOfGrade);
	}
	
	// 학점 * 과목평점 구하는 메서드
	public static double getGradeMulAvg(double grade, String avg) {
		double gradeMulAvg = 0.0;	
		
		switch (avg) {
			case "A+":
				gradeMulAvg = grade * 4.5;
				break;
			
			case "A0":
				gradeMulAvg = grade * 4.0;
				break;
			
			case "B+":
				gradeMulAvg = grade * 3.5;
				break;
			
			case "B0":
				gradeMulAvg = grade * 3.0;
				break;
			
			case "C+":
				gradeMulAvg = grade * 2.5;
				break;
			
			case "C0":
				gradeMulAvg = grade * 2.0;
				break;
			
			case "D+":
				gradeMulAvg = grade * 1.5;
				break;
			
			case "D0":
				gradeMulAvg = grade * 1.0;
				break;
			
			case "F":
				gradeMulAvg = grade * 0.0;
				break;
		}
		
		return gradeMulAvg;
	}
}