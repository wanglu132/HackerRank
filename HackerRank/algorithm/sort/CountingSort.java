package sort;

public class CountingSort {

	public static int[] countSort(int[] a, int k) {
		int[] c = new int[k];
		int[] b = new int[a.length];
		
		for(int i = 0; i < a.length; i++) {
			c[a[i]]++;
		}
		
		for(int i = 1; i < k; i++) {
			c[i] += c[i - 1];
		}
		
		for(int i = a.length - 1; i >= 0; i--) {
			b[--c[a[i]]] = a[i];
		}
		
		return b;
	}
	
	public static void main(String[] args) {
		int[] b = countSort(new int[] {2, 5, 1, 2, 3, 2, 4, 5, 4}, 6);
		for(int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}
	}
}
