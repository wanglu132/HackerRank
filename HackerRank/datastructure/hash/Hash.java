package hash;

import java.io.BufferedInputStream;
import java.util.Scanner;


public class Hash {
	
	static int NIL = -1;
	
	static int M = 1399;
	
	static int[] A = new int[M];
	static int[] I = new int[M];
	
	static {
		for(int i = 0; i < M; i++) {
			A[i] = NIL;
		}
	}

	/**
	 * 除法
	 */
	static int modHash(int k) {
		return k % M;
	}
	
	/**
	 * 线性探查
	 */
	static int linearOpenHash(int k) {
		int i = -1, idx = -1, h = modHash(k);
		do {
			i++;
			idx = (h + i) % M;
		}while(i < M && A[idx] != NIL);
		
		A[idx] = k;
		
		return idx;
	}
	
	/**
	 * 双重散列
	 */
	static int doubleOpenHash(int k) {
		int h1 = k % M;
		int h2 = 1 + (k % (M - 1));
		int i = -1, h = -1;
		
		do {
			i++;
			h = (h1 + i * h2) % M;
		}while(i < M && A[h] != NIL);
		
		if(i >= M)
			throw new RuntimeException(String.format("key: %d. can't find hash index", k));
		A[h] = k;
		
		return h;
	}
	
	public static void main(String[] args) {
		
		long before = System.currentTimeMillis();
		
		Scanner s = new Scanner(new BufferedInputStream(System.in));
		int n = s.nextInt();
		int m = s.nextInt();
		int k = s.nextInt();
		
		for(int i = 0; i < k; i++) {
			int r = s.nextInt();
			int c1 = s.nextInt();
			int c2 = s.nextInt();
			
			I[i] = doubleOpenHash(r); 
		}
		
		s.close();
		
		for(int i = 0; i < M; i++) {
			System.out.printf("%d\t%d%n", i, A[I[i]]);
		}
		

		long after = System.currentTimeMillis();
		
		System.out.printf("%nused: %sms", after - before);
		
	}
}
