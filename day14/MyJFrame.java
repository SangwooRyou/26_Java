package day14;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyJFrame extends JFrame{
	public MyJFrame() {
		this.setTitle("나만의 프레임");
		Container con = getContentPane();
		setLayout(new FlowLayout()); // 익명 클래스
		
		// 이름 O
		JButton btn = new JButton("소개");
		con.add(btn);
		JTextField jf;
		con.add(jf = new JTextField(20));
		JButton btn2 = new JButton("확인");
		con.add(btn2);
		
		// 익명 클래스로 리스너 구현
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				
				if(obj == btn) System.out.println("소개 버튼 클릭");
				// if(obj == jf) System.out.println(jf.getText());
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(jf.getText());
				// 텍스트 필드 초기화
				jf.setText("");
			}
		});
		
		setSize(300, 200);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MyJFrame();
		
	}
}
