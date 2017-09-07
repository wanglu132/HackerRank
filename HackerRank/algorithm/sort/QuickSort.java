package sort;

import java.util.Random;

public class QuickSort {
	
	static void exchange(int[] a, int x, int y) {
		int tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}
    
    static int partition(int[] a, int p, int r) {
		int x = a[r], i = p - 1;
		for(int j = p; j < r; j++) {
			if(a[j] <= x) {
				i++;
				exchange(a, i, j);
			}
		}
		exchange(a, ++i, r);
		return i;
	}
    
    static int randomPartition(int[] a, int p, int r) {
		//产生随机数，取值[p, r]
		int i = new Random().nextInt(r - p + 1) + p;
		exchange(a, r, i);
		return partition(a, p, r);
	}
    
    static int randomSelect(int[] a, int p, int r, int i) {
		if(p == r)
			return a[p];
		int q = randomPartition(a, p, r);
		int k = q - p + 1;
		if(i == k)
			return a[q];
		else if(i < k)
			return randomSelect(a, p, q - 1, i);
		else
			return randomSelect(a, q + 1, r, i - k);
	}
	
	/**
	 * 返回数组第i小的元素
	 */
	static int randomSelect(int[] a, int i) {
		return randomSelect(a, 0, a.length - 1, i);
	}

}
