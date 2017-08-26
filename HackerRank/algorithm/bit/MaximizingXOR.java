package bit;

import java.util.Scanner;

public class MaximizingXOR {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			System.out.println(allOne(l ^ r));
		}
	}
	
	public static int allOne(int i) {
        i |= (i >>  1);
        i |= (i >>  2);
        i |= (i >>  4);
        i |= (i >>  8);
        i |= (i >> 16);
        return i;
    }
}
