package day13;
import javax.swing.*;

public class MyFrame {
	public static void main(String[] args) {
		// 1. 컨테이너 생성 - JFrame 객체
		JFrame jf = new JFrame();
		jf.setSize(900, 600);
		jf.setTitle("title");
		jf.setVisible(true);

		
		// 2. 컴포넌트 생성 - JButton 객체 두 개
		JButton jbtn1 = new JButton("확인");
		JButton jbtn2 = new JButton();
		jbtn2.setText("취소");
		
		// 3. 컨테이너 컴포넌트 부착 (add)
		// jf.add(jbtn1);
		// jf.add(jbtn2);
		
		// 3.1 판넬 컨테이너 생성
		JPanel jp = new JPanel();
		jp.add(jbtn1);
		jp.add(jbtn2);
		
		jf.add(jp);
		
		// 4. 컨테이너 닫기 동작 추가
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jf.pack();
	}
}
