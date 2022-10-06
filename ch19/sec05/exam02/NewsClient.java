package ch19.sec05.exam02;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class NewsClient {

	public static void main(String[] args) {
		
		try {
			//DatagramSocket 생성
			DatagramSocket datagramSocket = new DatagramSocket();
			
			//구독하고 싶은 뉴스 주제 보내기
			String data = "연예";
			byte[] bytes = data.getBytes("UTF-8");
			
			//DatagramPacket 만들기
			DatagramPacket sendPacket = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress("localhost", 50001));
			datagramSocket.send(sendPacket); //요청 -> 주제가 서버로 넘어감!
			
			while(true) {
				//뉴스 받기
				DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
				datagramSocket.receive(receivePacket);
				
				//문자열로 변환
				String news = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
				System.out.println(news);
				
				//10번째 뉴스를 받으면 while 문을 종료
				if(news.contains("뉴스100")) {
					break;
				}
			}
			
			//DatagramSocket 닫기
			datagramSocket.close();
			
		} catch (Exception e) {
			System.out.println("[클라이언트]" + e.getMessage());
		}
	}

}
