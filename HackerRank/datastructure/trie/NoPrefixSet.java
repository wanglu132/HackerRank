package trie;

import java.util.Scanner;

public class NoPrefixSet {
	
	static class L {
		boolean f = false;
		L[] childs = new L[10];
	}
	
	static byte idx(char c) {
		return (byte)(c - 97);
	}
	
	static L[] R = new L[10];
	
	static boolean put(final String str, final int len,int i, L[] l) {
		
		byte idx = idx(str.charAt(i));
		if(l[idx] == null) 
			l[idx] = new L();
		else if(l[idx].f || i == len)
			return false;
		if(i == len) 
			return l[idx].f = true;
		return put(str, len, ++i, l[idx].childs);
	}
	

	public static void main(String[] args) {
		
		
		try (Scanner sc = new Scanner(System.in)) {
			
			int n = sc.nextInt();
			for(int i = 0; i < n; i++) {
				String str = sc.next();
				if(!put(str, str.length() - 1, 0, R)) {
					System.out.printf("BAD SET%n%s%n", str);
					return;
				};
			}
			System.out.println("GOOD SET");
		}
	}
}
