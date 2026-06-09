package my.app;

import java.util.Scanner;
import java.util.ArrayList;

public class ArrayListEx {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<String> alist = new ArrayList<String>();

		// 이름 4번 입력 → 컬렉션 저장
		for (int i = 0; i < 4; i++) {
			System.out.print("이름을 입력하세요 : ");
			alist.add(scan.nextLine());
		}

		// 저장된 모든 이름 모두 출력
		for (int i = 0; i < 4; i++) {
			System.out.println(alist.get(i));
		}

	}

}
