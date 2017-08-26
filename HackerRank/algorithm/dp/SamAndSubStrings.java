package dp;

import java.util.Scanner;

public class SamAndSubStrings {

	public static int[][] dp(int[] a) {
		
		int n = a.length;
		int[][] m = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			m[i][i] = a[i];
			for(int k = i + 1; k < n; k++){
				int s = 0;
				for(int j = i; j <= k; j++){
					s += (Math.pow(10, k - j) * a[j]);
				}
				m[i][k] = s;
			}
		}
		
		return m;
	}
	
	public static void main(String[] args) {
		
		String l = null;
		try (Scanner s = new Scanner(System.in);){
			l = s.nextLine();
		}
		
		if(l == null)
			return;
		
		char[] c = l.toCharArray();
		int n = c.length;
		int[] a = new int[n];
		for(int i = 0; i < n; i++){
			a[i] = ((int)c[i]) - 48;
		}
		
		int[][] m = dp(a);
		
		int s = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.print(m[i][j] + "\t");
				s += m[i][j];
				s = s % 1000000007;
			}
			System.out.println();
		}
		
		System.out.println(s);
		
//		System.out.println(2147483647 % 1000000007);
	}
}
