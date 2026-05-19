package day11;

public interface PhoneInterface {
	// 상수
	final int TIMEOUT = 10000;
	
	// 추상 메소드
	void sendCall();
	void receiveCall();
	
	// default 메소드
	default void printLogo() {
		System.out.println("** Phone");
	}
}
