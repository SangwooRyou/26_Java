package day14;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;

public class MouseListenerEx2 extends JFrame{
	JLabel lb;
	
	public MouseListenerEx2(){
		Container con = getContentPane();
		con.setLayout(new FlowLayout());
		lb = new JLabel("hi hello");
		lb.setLocation(30, 30);
		lb.setSize(50, 20);
		con.add(lb);
		
		// 이벤트 리스너 등록하기
		con.addMouseMotionListener(new MyMouseListener2());
		
		setSize(400, 400);
		setVisible(true);
		setTitle(lb.getText());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MouseListenerEx2();
	}

	// 내부 클래스(Inner Class)로 마우스 리스너 객체 구현
	class MyMouseListener2 extends MouseMotionAdapter{

		@Override
		public void mouseDragged(MouseEvent e) {
			lb.setText("MouseDragged ("+e.getX()+","+e.getY()+")");
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			lb.setText("MouseMoved ("+e.getX()+","+e.getY()+")");
		}
		
	}
}
