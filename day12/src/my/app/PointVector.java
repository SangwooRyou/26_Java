package my.app;

import java.util.Vector;

class Point {
	private int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}

public class PointVector {

	public static void main(String[] args) {
		// Vector 객체
		Vector<Point> pvectors = new Vector<Point>();
		// 객체 저장 (원소(요소) 삽입)
		pvectors.add(new Point(3, 5));
		pvectors.add(new Point(30, 70));
		// 자료 검색
		for (int i = 0; i < pvectors.size(); i++) {
			System.out.println("X : " + pvectors.elementAt(i).getX() + " Y : " + pvectors.elementAt(i).getY());
		}
//		System.out.println(pvectors.elementAt(0).getX());
//		System.out.println(pvectors.elementAt(1).getX());

		for (Point p : pvectors) {
			System.out.println(p.getX());
			System.out.println(p.getY());
		}
	}

}
