package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class PermutingTwoArrays {
	
	static String sum(int[] a, int[] b, int n, int k) {
		for(int p = 0, q = n - 1; p < n && q >= 0; p++, q--) {
			if(a[p] + b[q] < k) {
				return "NO";
			}
		}
		return "YES";
	}

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			int q = sc.nextInt();
			for(int i = 0; i < q; i++) {
				int n = sc.nextInt();
				int k = sc.nextInt();
				int[] a = new int[n], b = new int[n];
				for(int j = 0; j < n; j++) {
					a[j] = sc.nextInt();
				}
				for(int j = 0; j < n; j++) {
					b[j] = sc.nextInt();
				}
				
				Arrays.sort(a);
				Arrays.sort(b);
				
				System.out.println(sum(a, b, n, k));
			}
		}
	}
}
