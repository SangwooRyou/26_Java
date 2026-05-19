package day11;

public class SamsungPhone implements PhoneInterface {

	public static void main(String[] args) {
		SamsungPhone sp = new SamsungPhone();
		sp.sendCall();
		sp.receiveCall();
		
		// PhoneInterface pi = new PhoneInterface(); - 인터페이스는 객체 생성 불가.
		PhoneInterface pi = new SamsungPhone(); // 업캐스팅 - 부모 타입으로 사용 가능.
	}

	public void sendCall() {
		System.out.println("띠리리리링");
	}

	public void receiveCall() {
		System.out.println("전화가 왔습니다.");
	}

}
