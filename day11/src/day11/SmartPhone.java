package day11;

public class SmartPhone extends Calc implements PhoneInterface {

	public String toString() {
		return "스마트폰입니다";
	}

	public static void main(String[] args) {
		SmartPhone sp = new SmartPhone();
		sp.sendCall();
		sp.receiveCall();
		System.out.println("3 + 5 = " + sp.calculate(3, 5));
		System.out.println(sp.toString());

	}

	public void sendCall() {
		System.out.println("스마트폰 벨소리");
	}

	public void receiveCall() {
		System.out.println("스마트폰 전화 옴");
	}

}
