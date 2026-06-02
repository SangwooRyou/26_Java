package day13;
import java.awt.*;
import javax.swing.*;

public class MyFrame2 extends JFrame{
	
	public MyFrame2(){
		this.setTitle("나만의 프레임 생성");
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contain = getContentPane();
		contain.setBackground(Color.ORANGE); // 배경색 변경
		contain.setLayout(new FlowLayout()); // 배치관리자 설정
		
		JButton btn1 = new JButton("확인");
		JButton btn2 = new JButton("취소");
		JButton btn3 = new JButton("무시");
		
		// 컴포넌트 부착
		contain.add(btn1);
		contain.add(btn2);
		contain.add(btn3);
	}
	
	public static void main(String[] args) {
		// 1. 컨테이너 생성 - JFrame 객체
		MyFrame2 mf2 = new MyFrame2();
	}
}
