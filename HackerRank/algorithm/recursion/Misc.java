package recursion;

public class Misc {

	/**
	 * 1+2+3+...+n
	 */
	public static int sum(int n){
		if(n == 1){
			return n;
		}
		return n + sum(n-1);
	}
	
	/**
	 * n!
	 */
	public static int fact(int n){
		if(n == 1){
			return n;
		}
		return n * fact(n-1);
	}
	
	/**
	 * a,b两数最大公约数(a<b)
	 */
	public static int gcd(int a, int b) {
		if(a == 0){
			return b;
		}else return gcd(b % a, a);
	}
	
	/**
	 * 在数组a里查找x
	 */
	public static int search(final int[] a, int i, final int j, final int x) {
		if(a[i] == x){
			return i;
		}
		if(i == j)
			return -1;
		return search(a, ++i, j, x);
	}
	
	/**
	 * 二分搜索
	 */
	public static int binarySearch(final int[] a, final int x, int i, int j) {
		 if(i > j){
			 return -1;
		 }
		 int m = (i + j) / 2;
		 if(x == a[m]) {
			 return m;
		 }else if(x < a[m]) {
			 return binarySearch(a, x, i, m - 1);
		 }else{
			 return binarySearch(a, x, m + 1, j);
		 }
	}
	
	/**
	 * 二叉树。
	 * 递（生成）时为前序遍历，归（销毁）时为后序遍历
	 */
	public static int fibonacci(int n) {
		if(n == 1 || n == 0)
			return n;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}
	
	/**
	 * 合并两个有序数组
	 */
	private static int[] merge(int[] a, int[] b) {
		
		int[] c = new int[a.length + b.length];
		
		int i = 0, j = 0;
		for(; i < a.length; i++){
			for(; j < b.length; j++){
				if(a[i] < b[j]) {
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
	public static int[] mergeSort(final int[] a, int i, int j) {
		if(i == j)
			return new int[]{a[i]};
		int m = (i + j) / 2;
		return merge(mergeSort(a, i, m), mergeSort(a, m + 1, j)); 
	}
	
	public static void main(String[] args) {
		
//		int[] a = merge(new int[]{4,7,9,10}, new int[]{6,8});
		
//		int[] a = mergeSort(new int[]{4,7,2,3,8,1,9,5,6}, 0, 8);
//		
//		for(int i = 0; i < a.length; i++) {
//			System.out.print(a[i] + "\t");
//		}
		
//		System.out.println(merge(new int[]{1,2,3,4}, new int[]{6}));
		
		System.out.println('加');
	}
}
