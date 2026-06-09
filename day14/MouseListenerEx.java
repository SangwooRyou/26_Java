package day14;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class MouseListenerEx extends JFrame{
	JLabel lb;
	
	public MouseListenerEx(){
		Container con = getContentPane();
		con.setLayout(new FlowLayout());
		lb = new JLabel("hi hello");
		lb.setLocation(30, 30);
		lb.setSize(50, 20);
		con.add(lb);
		
		// 이벤트 리스너 등록하기
		con.addMouseListener(new MyMouseListener());
		
		setSize(400, 400);
		setVisible(true);
		setTitle(lb.getText());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MouseListenerEx();
	}

	// 내부 클래스(Inner Class)로 마우스 리스너 객체 구현
	class MyMouseListener extends MouseAdapter{

		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			lb.setLocation(x, y);
		}
		
	}
}
