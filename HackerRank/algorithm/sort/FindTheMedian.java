package sort;

import java.util.Scanner;

public class FindTheMedian {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = s.nextInt();
		}
		
		s.close();
		
		System.out.println(QuickSort.randomSelect(a, (n >> 1) + 1));
	}
}
