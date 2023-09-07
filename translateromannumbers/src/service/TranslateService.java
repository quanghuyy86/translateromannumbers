package service;

public class TranslateService {
	
	public int value(char r) {
		if (r == 'I')
			return 1;
		if (r == 'V')
			return 5;
		if (r == 'X')
			return 10;
		if (r == 'L')
			return 50;
		if (r == 'C')
			return 100;
		if (r == 'D')
			return 500;
		if (r == 'M')
			return 1000;
		return 1;
	}


	public int convertRomanToInt(String s) {
		int total = 0; 
		for (int i = 0; i < s.length(); i++) {
			int s1 = value(s.charAt(i));
			// lấy giá trị của ký hiệu s2[i+1]  
			if (i + 1 < s.length()) {
				int s2 = value(s.charAt(i + 1));
				//so sánh ký tự hiện tại với ký tự bên phải của nó   
				if (s1 >= s2) {
					// nếu giá trị của ký tự hiện tại lớn hơn hoặc bằng ký tự tiếp theo  
					total = total + s1;
				} else {
					// nếu giá trị của ký tự hiện tại nhỏ hơn ký tự tiếp theo  
					total = total - s1;
				}
			} else {
				total = total + s1;
			}
		}
		// trả về giá trị số nguyên tương ứng  
		return total;
	}
}
