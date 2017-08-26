import java.util.Scanner;

public class Solution {
	
	private static class Pair{
		private int k;
		private String s;
		
		public void setK(int k) {
			this.k = k;
		}
		public void setS(String s) {
			this.s = s;
		}
		public int getK() {
			return k;
		}
		@Override
		public String toString() {
			return k + " " + s;
		}
		
	}
	
	/**
	 * 合并两个有序数组
	 */
	private static Pair[] merge(Pair[] a, Pair[] b) {
		
		Pair[] c = new Pair[a.length + b.length];
		
		int i = 0, j = 0;
		for(; i < a.length; i++){
			for(; j < b.length; j++){
				if(a[i].getK() <= b[j].getK()) {
					c[i + j] = a[i];
					break;
				}else{
					c[i + j] = b[j];
				}
			}
			if(j == b.length) {
				c[i + j] = a[i];
			}
		}
		
		for(; j < b.length; j++) {
			c[i + j] = b[j];
		}
		
		return c;
	}
	
	/** 
	 * 归并排序
	 * 分治法
	 */
	public static Pair[] mergeSort(final Pair[] a, int i, int j) {
		if(i == j)
			return new Pair[]{a[i]};
		int m = (i + j) / 2;
		return merge(mergeSort(a, i, m), mergeSort(a, m + 1, j)); 
	}

	
	public static void main(String[] args) {
		
		Pair[] p = null;
		try (Scanner s = new Scanner(System.in);){
			int n = Integer.parseInt(s.nextLine());
			p = new Pair[n];
			for(int i = 0; i < n; i++) {
				String l = s.nextLine();
				int idx = l.indexOf(' ');
				Pair pa = new Pair();
				pa.setK(Integer.parseInt(l.substring(0,idx)));
				pa.setS(l.substring(idx + 1));
				p[i] = pa;
			}
		}
		
		Pair[] np = mergeSort(p, 0, p.length - 1);
		
		for(int i = 0; i < np.length; i++) {
			System.out.println(np[i].toString());
		}
		
	}
}
