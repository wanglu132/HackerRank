package bit;

import java.util.Scanner;

public class CounterGame2 {
	
	public static String getWin(long n) {
		int i = 0;
		for(; n != 1; i++) {
			if(1 == Long.bitCount(n)) {
				n = n >>> 1;
			}else {
				n = n - Long.highestOneBit(n);
			}
		}
		return i % 2 == 0 ? "Richard" : "Louise";
	}

	public static void main(String[] args) {
		long[] array;
		try (Scanner sc = new Scanner(System.in);){
			int T = sc.nextInt();
			array = new long[T];
			for(int i = 0; i < T; i++) {
				array[i] = Long.parseUnsignedLong(sc.next());
			}
		}
		
		for(int i = 0; i < array.length; i++) {
			System.out.println(getWin(array[i]));
		}
	}
}
