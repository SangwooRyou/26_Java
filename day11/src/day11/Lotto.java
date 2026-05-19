package day11;

import java.util.Random;

public class Lotto {
	// 난수 (1~45까지 임의의 정수 6개 생성)
	public static void main(String[] args) {
		Random random = new Random();
		int n [] = new int[6];
		
		System.out.print("이번 주 로또 번호 > ");
		for (int i = 0; i < 6; i++) {
			n[i] = random.nextInt(45) + 1;
			System.out.print(n[i] + " ");
		}
		
		
		
	}

}
