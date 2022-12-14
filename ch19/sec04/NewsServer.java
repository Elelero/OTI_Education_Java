package ch19.sec04;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.Scanner;

public class NewsServer {

	//Field-------------------------------------------------------------------
	private static DatagramSocket datagramSocket;
	
	
	//Main method-------------------------------------------------------------
	public static void main(String[] args) {
		System.out.println("----------------------------------------------");
		System.out.println("서버를 종료하려면 q 또는 Q를 입력하고, Enter키를 입력하세요.");
		System.out.println("----------------------------------------------");
		
		//UDP 서버 시작
		startServer();
		
		//키보드 입력 (스캐너 이용)
		Scanner sc = new Scanner(System.in);
		while(true) {
			String key = sc.nextLine();
			if(key.toLowerCase().equals("q")) {
				break;
			}
		}
		sc.close();
		
		//UDP 서버 종료
		stopServer();
		
	}
	
	
	//Library Method-----------------------------------------------------------
	public static void startServer() {
		//작업 스레드 정의
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					//DatagramSocket 생성 및 Port 바인딩
					datagramSocket = new DatagramSocket(50001);
					System.out.println("[서버] 시작됨");
					
					while(true) { //클라이언트의 요청을 계속 받아야하기 때문에 반복문 작성
						//클라이언트가 구독하고 싶은 뉴스 주제 얻기
						DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
						System.out.println("클라이언트의 희망 뉴스 종류를 얻기 위해 대기함");
						datagramSocket.receive(receivePacket); //데이터를 받기 전까지 블로킹 상태
						String newsKind = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
						
						//클라이언트의 IP와 Port 정보가 있는 SocketAddress 얻기
						SocketAddress socketAddress = receivePacket.getSocketAddress();
						
						//10개의 뉴스를 클라이언트로 전송
						for(int i=1; i<=10; i++) {
							String data = newsKind + ": 뉴스" +  i;
							byte[] bytes = data.getBytes("UTF-8");
							DatagramPacket sendPacket = new DatagramPacket(bytes, 0, bytes.length, socketAddress);
							datagramSocket.send(sendPacket);
							//속도 늦추기
							Thread.sleep(1000);
						}
					}
					
				} catch (Exception e) {
					//예외 메세지
					System.out.println("[서버] " + e.getMessage());
				}
			}
		};
		//작업 스레드 시작
		thread.start();
	}
	
	public static void stopServer() {
		//DatagramSocket을 닫고 Port 언바인딩
		datagramSocket.close();
		System.out.println("[서버] 종료됨");
	}

}
