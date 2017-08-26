package heap;

import java.util.Scanner;

public class JesseAndCookies {
	
	static int recursion(final BinaryHeap heap, final int k) {
		if(heap.peek() >= k)
			return 0;
		else if(heap.getHeap_size() == 1) {
			return -1;
		}
		int s = (heap.poll() + (heap.poll() << 1));
		heap.offer(s);
		int ret = recursion(heap, k);
		return ret == -1 ? ret : ret + 1;
	}

	public static void main(String[] args) {
		
		BinaryHeap heap = new BinaryHeap(false);
		int k;
		try(Scanner s = new Scanner(System.in);) {
			
			int n = s.nextInt();
			k = s.nextInt();
			
			for(int i = 0; i < n; i++) {
				heap.offer(s.nextInt());
			}
		}
		
		System.out.println(recursion(heap, k));
	}
}
