package ch12.sec04;

public class InExample {
	
	public static void main(String[] args) throws Exception {
		//변수 선언
		int speed = 0;
		int keyCode = 0;
		
		//while 반복문
		while(true) {
			if(keyCode!=13 && keyCode!=10) { //엔터값 무시
				if(keyCode==49) { //숫자 1의 키코드
					speed++;
				} else if(keyCode==50) { //숫자 2의 키코드
					speed--; 
				} else if(keyCode==51) { //숫자 3의 키코드
					break;
				}
				System.out.println("------------------------");
				System.out.println("1.증속 | 2.감속 | 3.중지");
				System.out.println("------------------------");
				System.out.println("현재 속도 = " + speed);
				System.out.println("선택> ");
			}
			keyCode = System.in.read(); //keyCode변수에 각 키에 부여된 번호를 입력!
		}
		System.out.println("프로그램 종료!");
		
	}
}
