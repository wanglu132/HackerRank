package heap;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QHEAP {

public static void main(String[] args) {
		
		BinaryHeap heap = new BinaryHeap(false);
		Queue<Integer> fifo = new LinkedList<Integer>();
		
		try( Scanner s = new Scanner(System.in); ){
			int n = s.nextInt();
			
			for(int i = 0; i < n; i++){
				switch (s.nextInt()) {
				case 1:
					heap.offer(s.nextInt());
					break;
				case 2:
					heap.remove(s.nextInt());
					break;
				case 3:
					fifo.offer(heap.peek());
					break;
				default:
					break;
				}
			}
		}
		
		for(Integer max = 0; (max = fifo.poll()) != null; ) {
			System.out.println(max);
		}
		
	}
}
