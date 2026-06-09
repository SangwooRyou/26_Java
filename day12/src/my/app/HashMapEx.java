package my.app;
import java.util.HashMap;

public class HashMapEx {
	public static void main(String[] args) {
		HashMap<String, Integer> hashm = new HashMap<String, Integer>();
		hashm.put("홍길동", 70);
		hashm.put("이길동", 100);
		hashm.put("강길동", 80);
		hashm.put("박길동", 60);
		
		System.out.println(hashm.get("이길동"));
	}

}
