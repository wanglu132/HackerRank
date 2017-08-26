package stack;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

public class MaximumElement {

	public static void main(String[] args) {
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		
		try( Scanner s = new Scanner(System.in); ){
			int n = s.nextInt();
			
			for(int i = 0; i < n; i++){
				switch (s.nextInt()) {
				case 1:
					pq.offer(s.nextInt());
					break;
				case 2:
					pq.remove(s.nextInt());
					break;
				case 3:
					queue.offer(pq.peek());
					break;
				default:
					break;
				}
			}
		}
		
		for(Integer max = 0; (max = queue.poll()) != null; ) {
			System.out.println(max);
		}
		
	}
}
