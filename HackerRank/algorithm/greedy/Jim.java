package greedy;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Jim {
	
	static class Pair{
		int d;
		int idx;
		Pair(int d, int idx) {
			this.d = d;
			this.idx = idx;
		}
	}
	
	static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			
			int[] a = new int[n];
			PriorityQueue<Pair> q = new PriorityQueue<Pair>(new Comparator<Pair>() {

				@Override
				public int compare(Pair o1, Pair o2) {
					return o2.d - o1.d;
				}
			});
			
			int[] idx = new int[n];
			for(int i = 0; i < n; i++) {
				idx[i] = i;
				a[i] = sc.nextInt();
				q.offer(new Pair(a[i], i));
			}
			
			Pair p;
			for(int i = 0; i < k && (p = q.poll()) != null; i++) {
				swap(a, i, idx[p.idx]);
				idx[i] = p.idx;
				idx[p.idx] = i;
			}
			
			for(int i = 0; i < a.length; i++) {
				System.out.printf("%d ", a[i]);
			}
		}
	}
}
