package ch13.sec01;

public class GenericExample {

	public static void main(String[] args) {
		
		//박스 객체 생성
//		Box<String> box1 = new Box<String>();
		Box<String> box1 = new Box<>(); //간소화 코드
		box1.content = "안녕하세요";
		String str = box1.content;
		System.out.println(str);
		
		Box<Integer> box2 = new Box<>();
		box2.content = 100;
		int value = box2.content;
		System.out.println(value);
		
	}

}
