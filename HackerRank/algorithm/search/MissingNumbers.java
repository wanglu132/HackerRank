package search;

import java.util.Scanner;

public class MissingNumbers {

	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(System.in)){
			int na = sc.nextInt();
			int[] aa = new int[na]; 
			for(int i = 0; i < na; i++) {
				aa[i] = sc.nextInt();
			}
			
			int nb = sc.nextInt();
			int[] x = new int[10000 + 1];
			for(int i = 0; i < nb; i++) {
				x[sc.nextInt()]++ ;
			}
			
			for(int i = 0; i < na; i++) {
				x[aa[i]]--;
			}
			
			for(int i = 0; i < x.length; i++) {
				if(x[i] > 0) {
					System.out.printf("%d ", i);
				}
			}
		}
	}
}
