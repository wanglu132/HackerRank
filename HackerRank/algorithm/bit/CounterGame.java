package bit;

import java.math.BigInteger;
import java.util.Scanner;

public class CounterGame {
	
	public static String getWin(BigInteger n) {
		int i = 0;
		for(; n.compareTo(BigInteger.ONE) != 0; i++) {
			if(1 == n.bitCount()) {
				n = n.shiftRight(1);
			}else {
				n = n.clearBit(n.bitLength() - 1);
			}
		}
		return i % 2 == 0 ? "Richard" : "Louise";
	}

	public static void main(String[] args) {
		BigInteger[] array;
		try (Scanner sc = new Scanner(System.in);){
			int T = sc.nextInt();
			array = new BigInteger[T];
			for(int i = 0; i < T; i++) {
				array[i] = sc.nextBigInteger();
			}
		}
		
		for(int i = 0; i < array.length; i++) {
			System.out.println(getWin(array[i]));
		}
	}
}
