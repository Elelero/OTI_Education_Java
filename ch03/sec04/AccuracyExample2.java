package ch03.sec04;

public class AccuracyExample2 {

	public static void main(String[] args) {
		int apple = 1;
		int totalPiece = apple * 10;
		int number = 7;
		
		int result = totalPiece - number;
		System.out.println("10조각에서 남은 조각: " + result);
		System.out.println("사과 1개에서 남은 양 " + result/10.0); //정수 연산이기에 제대로 나누어떨어짐!
		
	}
}
