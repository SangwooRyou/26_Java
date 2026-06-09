package my.app;

// import java.util.Scanner;
import java.awt.Frame;

public class MyClass {
	// Object obj = new Object();
	
	String a;
	
	@Override
	public String toString() {
		return a;
	}
	
	public static void main(String[] args) {
		MyClass my = new MyClass();
		my.a = "문자열1";
		String b = new String("문자열2");
		System.out.println(my.toString());
		System.out.println(b);
		
		Frame f = new Frame();
		f.setSize(500, 500);
		f.setVisible(true);
	}
	
	
}
