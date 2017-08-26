package search;

import java.util.Scanner;

public class SherlockAndArray {

	static String solve(int[] a) {

		if (a.length == 1) {
			return "YES";
		} else if(a.length == 2) {
			return "NO";
		}
		int ls = a[0], hs = 0;
		for (int i = 2; i < a.length; i++) {
			hs += a[i];
		}
		if (ls == hs) {
			return "YES";
		}

		for (int i = 2; i < a.length - 1; i++) {
			ls += a[i - 1];
			hs -= a[i];
			if (ls == hs) {
				return "YES";
			}
		}
		return "NO";
	}

	    public static void main(String[] args) {
	        Scanner in = new Scanner(System.in);
	        int T = in.nextInt();
	        for(int a0 = 0; a0 < T; a0++){
	            int n = in.nextInt();
	            int[] a = new int[n];
	            for(int a_i=0; a_i < n; a_i++){
	                a[a_i] = in.nextInt();
	            }
	            String result = solve(a);
	            System.out.println(result);
	        }
	    }
}
