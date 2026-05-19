package day11;

class Point {
	// 정보 은닉
	private int x, y;

	// 인자 생성자
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// getter
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	// setter
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	// 메소드 오버라이딩 toString()
	@Override
	public String toString() {
		return "Point(" + x + ", "+ y + ")";
	}

}

public class PointTest {

	public static void main(String[] args) {
		Point point = new Point(300, 500);
		System.out.println(point.toString());
	}

}
