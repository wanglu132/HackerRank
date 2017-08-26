package string;

import java.util.Scanner;

public class HackerRankInAString {
	
	/**
	 * 比hackerrank方法稍差
	 */
	public static String hackerrank1(String s) {
		char[] H = new char[] {'h', 'a', 'c', 'k', 'e', 'r', 'r', 'a', 'n', 'k'};
		char[] a = s.toCharArray();
		for(int i = 0, j = 0; i < H.length; i++) {
			boolean f = false;
			for(; j < a.length; j++) {
				if(H[i] == a[j]) {
					f = true;
					j++;
					break;
				}
			}
			if(!f && j == a.length) {
				return "NO";
			}
		}
		
		return "YES";
	}
	
	public static String hackerrank(String s) {
		String H = "hackerrank";
		for(int i = 0, j = 0; i < H.length(); i++) {
			boolean f = false;
			for(; j < s.length(); j++) {
				if(H.charAt(i) == s.charAt(j)) {
					f = true;
					j++;
					break;
				}
			}
			if(!f && j == s.length()) {
				return "NO";
			}
		}
		
		return "YES";
	}

	public static void main(String[] args) {
       
		String[] as;
		try (Scanner in = new Scanner(System.in);) {
			int q = in.nextInt();
			as = new String[q];
	        for(int i = 0; i < q; i++){
	        	as[i] = in.next();
	        }
		}
		
		for(int i = 0; i < as.length; i++) {
			System.out.println(hackerrank(as[i]));
		}
        
    }
}
