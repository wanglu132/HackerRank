package implementation;

import java.util.Scanner;

public class NonDivisibleSubset {

	public static void main(String[] args) {
		
		int n, k;
		int[] a = null;
		try (Scanner sc = new Scanner(System.in)) {
			n = sc.nextInt();
			k = sc.nextInt();
			
			a = new int[n];
			
			for(int i = 0; i < n; i++) {
				a[i] = sc.nextInt();
			}
		}
		
		int[] counts = new int[k + 1];
		for(int i = 0; i < n; i++) {
			counts[a[i] % k] += 1;
		}
		
		int total = Math.min(counts[0], 1);
		int upto = k/2;
	    if (upto * 2 == k) 
	    	upto -= 1;
	    
	    for (int i = 1; i <= upto; ++i) 
	    	total += Math.max(counts[i], counts[k-i]);
	    
	    if (k % 2 == 0)
	    	total += (counts[k/2] > 0) ? 1 : 0;
	    
		System.out.println(total);
		
	}
}
