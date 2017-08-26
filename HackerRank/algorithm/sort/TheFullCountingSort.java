package sort;

import java.util.Scanner;

public class TheFullCountingSort {
	
	static class Pair {
		int x;
		String s;
		public Pair(int x, String s) {
			this.x = x;
			this.s = s;
		}
	}

	public static Pair[] countSort(Pair[] a, int k) {
		int[] c = new int[k];
		Pair[] b = new Pair[a.length];
		
		for(int i = 0; i < a.length; i++) {
			c[a[i].x]++;
		}
		
		for(int i = 1; i < k; i++) {
			c[i] += c[i - 1];
		}
		
		for(int i = a.length - 1; i >= 0; i--) {
			if(i < a.length / 2) {
				a[i].s = "-";
			}
			b[--c[a[i].x]] = a[i];
		}
		
		return b;
	}
	
	public static void main(String[] args) {
		
		Pair[] ps = null;
		try(Scanner sc = new Scanner(System.in)){
			int n = sc.nextInt();
			ps = new Pair[n];
			for(int i = 0; i < n; i++) {
				ps[i] = new Pair(sc.nextInt(), sc.next());
			}
		}
		
		Pair[] r = countSort(ps, 100);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < r.length; i++) {
			sb.append(r[i].s).append(' ');
		}
		
		System.out.println(sb.toString());
	}
}
