package day13;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class MyActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton jbtn = (JButton) e.getSource();

		if (jbtn.getActionCommand().equals("add")) {
			System.out.println("더하기");
		} else if (jbtn.getActionCommand().equals("sub")) {
			System.out.println("빼기");
		} else if (jbtn.getActionCommand().equals("mul")) {
			System.out.println("곱하기");
		} else if (jbtn.getActionCommand().equals("div")) {
			System.out.println("나누기");
		} else if (jbtn.getActionCommand().equals("center")) {
			System.out.println("계산");
		}
	}
}

public class BorderLayoutTest extends JFrame {

	static JButton btn1, btn2, btn3, btn4, btn5;

	public BorderLayoutTest() {
		setSize(250, 250);
		setTitle("세번째 프레임");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btn1 = new JButton("add");
		btn1.addActionListener(new MyActionListener());
		btn2 = new JButton("sub");
		btn2.addActionListener(new MyActionListener());
		btn3 = new JButton("mul");
		btn3.addActionListener(new MyActionListener());
		btn4 = new JButton("div");
		btn4.addActionListener(new MyActionListener());
		btn5 = new JButton("center");
		btn5.addActionListener(new MyActionListener());

		// 5개 영역에 버튼 배치
		this.add(btn3, BorderLayout.EAST);
		this.add(btn4, BorderLayout.WEST);
		this.add(btn1, BorderLayout.NORTH);
		this.add(btn2, BorderLayout.SOUTH);
		this.add(btn5, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		// 익명 객체 생성
		new BorderLayoutTest();
	}
}
