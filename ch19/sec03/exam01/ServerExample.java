package ch19.sec03.exam01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerExample {

	//field------------------------------------------------------------------
	private static ServerSocket serverSocket;
	
	//main method (메인 스레드)-------------------------------------------------
	public static void main(String[] args) {
		System.out.println("----------------------------------------------");
		System.out.println("서버를 종료하려면 q 또는 Q를 입력하고, Enter키를 입력하세요.");
		System.out.println("----------------------------------------------");
		
		//TCP 서버 시작
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
		
		//TCP 서버 종료
		stopServer();
		
	
	}
	
	//library method (작업 스레드)----------------------------------------------
	public static void startServer() {
		//작업 스레드 정의
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					//ServerSocket 생성 및 Port 바인딩
					serverSocket = new ServerSocket(50001);
					System.out.println("[서버] 시작됨");
					
					while(true) { //여러 클라이언트의 연결 요청을 해주기 위해서!
						System.out.println("\n[서버] 연결 요청을 기다림\n");
						//연결 수락
						Socket socket = serverSocket.accept(); //소켓은 생성되었으나, 현재 블로킹 상태!
						//위의 소켓을 통해 클라이언트의 IP 주소 등을 알 수 있음
						//연결된 클라이언트의 정보 얻기
						InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress(); //강제타입변환
						String clientIP = isa.getHostString();
						System.out.println("[서버] " + clientIP + "의 연결 요청을 수락함");
						//연결 끊기
						socket.close();
						System.out.println("[서버] " + clientIP + "의 연결을 끊음");
					}
					
				} catch (IOException e) {
					System.out.println("[서버] " + e.getMessage());
				}
			}
		};
		//스레드 시작
		thread.start();
	}
	
	public static void stopServer() {
		try {
			//ServerSocket을 닫고 Port 언바인딩
			serverSocket.close();
		} catch (IOException e) { }
	}
		
}
