package search;

import java.util.PriorityQueue;
import java.util.Scanner;

public class FindTheMedian {
	
	PriorityQueue<Integer> l = new PriorityQueue<Integer>();
	PriorityQueue<Integer> h = new PriorityQueue<Integer>();
	
	void offer(int d) {
		if(l.size() == 0) {
			l.offer(d);
		}
//		if(d < l)
	}

	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(System.in)) {
			int n = sc.nextInt();
			for(int i = 0; i < n; i++) {
				sc.nextInt();
			}
		}
	}
}
